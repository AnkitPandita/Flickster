package com.urbn.android.flickster.features.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.urbn.android.flickster.Constants
import com.urbn.android.flickster.R
import com.urbn.android.flickster.databinding.CharacterItemContentBinding
import com.urbn.android.flickster.domain.model.Character
import com.urbn.android.flickster.features.characterdetails.CharacterDetailFragment
import java.lang.StringBuilder

class CharacterAdapter: ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharactersDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    inner class CharacterViewHolder(binding: CharacterItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val charName: TextView = binding.characterName
        private val charPic: ImageView = binding.characterPicture

        fun onBind(character: Character) {
            charName.text = character.name
            val url = StringBuilder()
            url.append(Constants.BASE_URL, character.imageUrl)
            Glide.with(itemView)
                .load(url.toString())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .into(charPic)

            itemView.setOnClickListener { itemView ->
                val bundle = Bundle()

                bundle.putParcelable(
                    CharacterDetailFragment.ARG_CHARACTER,
                    character
                )

                itemView.findNavController().navigate(R.id.show_character_detail, bundle)
            }
        }
    }


    object CharactersDiffUtil: DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.details == newItem.details &&
                    oldItem.imageUrl == newItem.imageUrl &&
                    oldItem.isFavorite == newItem.isFavorite
        }
    }
}
