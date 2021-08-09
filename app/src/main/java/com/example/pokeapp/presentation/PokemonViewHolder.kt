package com.example.pokeapp.presentation

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.ViewHolder
import com.example.pokeapp.R
import com.google.android.material.progressindicator.LinearProgressIndicator

class MainMenuViewHolder(view: View) : ViewHolder(view) {
    val tvPokemon: AppCompatTextView = view.findViewById(R.id.tvPokemonName)
    val ivPhoto: AppCompatImageView = view.findViewById(R.id.ivPhoto)
    val layoutBadge: RecyclerView = view.findViewById(R.id.layoutBadge)
}

class TypeViewHolder(view: View) : ViewHolder(view) {
    val tvType: AppCompatTextView = view.findViewById(R.id.tvPokemonBadge)
}

class StatBasicViewHolder(view: View) : ViewHolder(view) {
    val tvStatName: AppCompatTextView = view.findViewById(R.id.tvTitleStat)
    val progress: LinearProgressIndicator = view.findViewById(R.id.progress)
}

class AbilityViewHolder(view: View) : ViewHolder(view) {
    val tvTitleAbility: AppCompatTextView = view.findViewById(R.id.tvTitleAbility)
    val tvDescAbility: AppCompatTextView = view.findViewById(R.id.tvDescAbility)
}

class EvolutionViewHolder(view: View) : ViewHolder(view) {
    val ivPhoto1: AppCompatImageView = view.findViewById(R.id.ivPhoto1)
    val ivPhoto2: AppCompatImageView = view.findViewById(R.id.ivPhoto2)
    val tvMinLevel: AppCompatTextView = view.findViewById(R.id.tvMinLevel)
    val tvPokemonName1: AppCompatTextView = view.findViewById(R.id.tvPokemonName1)
    val tvPokemonName2: AppCompatTextView = view.findViewById(R.id.tvPokemonName2)
}

