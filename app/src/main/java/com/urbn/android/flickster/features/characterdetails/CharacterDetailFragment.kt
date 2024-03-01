package com.urbn.android.flickster.features.characterdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.urbn.android.flickster.Constants
import com.urbn.android.flickster.HostViewModel
import com.urbn.android.flickster.R
import com.urbn.android.flickster.domain.model.Character
import com.urbn.android.flickster.databinding.FragmentCharacterDetailBinding
import java.lang.StringBuilder

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val character: Character by lazy {
        requireArguments().getParcelable(ARG_CHARACTER)!!
    }
    private lateinit var hostViewModel: HostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().run {
            hostViewModel = ViewModelProvider(this)[HostViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        bindUI(binding)
        return binding.root
    }

    private fun bindUI(binding: FragmentCharacterDetailBinding) {
        val characterImage = binding.characterImage
        val characterDetails = binding.characterDetails

        hostViewModel.updateAppBarTitle(character.name)
        characterDetails.text = character.details
        val url = StringBuilder()
        url.append(Constants.BASE_URL, character.imageUrl)
        Glide.with(binding.root)
            .load(url.toString())
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .fallback(R.drawable.placeholder)
            .into(characterImage)
    }


    companion object {
        const val ARG_CHARACTER = "character"
    }
}
