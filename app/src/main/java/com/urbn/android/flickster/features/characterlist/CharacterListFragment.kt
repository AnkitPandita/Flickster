package com.urbn.android.flickster.features.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.urbn.android.flickster.R
import com.urbn.android.flickster.databinding.FragmentCharacterListBinding
import com.urbn.android.flickster.domain.util.Response
import com.urbn.android.flickster.features.characterlist.CharacterListViewModel.SortingMethod.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var adapter: CharacterAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var fabSort: FloatingActionButton
    private val characterListViewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.itemList
        progressBar = binding.progressBar

        adapter = CharacterAdapter()
        recyclerView.adapter = adapter

        fabSort = binding.fabSort
        fabSort.setOnClickListener { fabView ->
            showSortMenu(fabView)
        }

        bindCollector(view)
    }

    private fun bindCollector(view: View) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                characterListViewModel.characterList.collect {
                    when (it) {
                        is Response.Error -> {
                            Snackbar.make(view, it.errorMessage.toString(), Snackbar.LENGTH_SHORT)
                                .show()
                            progressBar.visibility = View.GONE
                        }

                        is Response.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }

                        is Response.Success -> {
                            it.data?.let { characterList ->
                                adapter.submitList(characterList)
                                fabSort.visibility = View.VISIBLE
                            }
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun showSortMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)

        val sortAlphabetical = popupMenu.menu.findItem(R.id.sort_alphabetical)
        val sortReverseAlphabetical = popupMenu.menu.findItem(R.id.sort_reverse_alphabetical)
        when (characterListViewModel.currentSortingMethod) {
            ALPHABETICAL -> {
                sortAlphabetical.isChecked = true
            }

            REVERSE_ALPHABETICAL -> {
                sortReverseAlphabetical.isChecked = true
            }
        }

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.sort_alphabetical -> {
                    characterListViewModel.sortAlphabetically()
                    item.isChecked = true
                    sortReverseAlphabetical.isChecked = false
                    true
                }

                R.id.sort_reverse_alphabetical -> {
                    characterListViewModel.sortReverseAlphabetically()
                    item.isChecked = true
                    sortAlphabetical.isChecked = false
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }

}
