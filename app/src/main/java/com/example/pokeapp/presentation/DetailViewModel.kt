package com.example.pokeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.repository.PokemonRepository
import com.example.pokeapp.domain.models.Evolutions
import com.example.pokeapp.domain.models.Pokemon
import com.example.pokeapp.domain.models.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val repository: PokemonRepository
) : ViewModel() {

    private val _detailPokemon: MutableLiveData<UIState<Pokemon>> = MutableLiveData()
    val detailPokemon: LiveData<UIState<Pokemon>> get() = _detailPokemon

    private val _detailPokemonEvolution: MutableLiveData<UIState<Pokemon>> = MutableLiveData()
    val detailPokemonEvolution: LiveData<UIState<Pokemon>> get() = _detailPokemonEvolution

    private val _pokemon: MutableLiveData<Pokemon> = MutableLiveData()
    val pokemon: LiveData<Pokemon> get() = _pokemon

    private val _evolution: MutableLiveData<Evolutions> = MutableLiveData()
    val evolution: LiveData<Evolutions> get() = _evolution

    fun getPokemonByName(name: String) {
        viewModelScope.launch {
            _pokemon.value = repository.getPokemonWithDetail(name)
        }
    }

    fun getEvolutionById(idEvolution: Int) {
        viewModelScope.launch {
            _evolution.value = repository.getEvolutionPokemon(idEvolution = idEvolution)
        }
    }

    fun getPokemonSpecies(name: String) {
        viewModelScope.launch {
            repository.getPokemonSpecies(name)
                .collect {
                    _detailPokemon.value = it
                }
        }
    }

    fun getPokemonEvolution(idEvolution: Int, name: String) {
        viewModelScope.launch {
            repository.getPokemonEvolution(idEvolution = idEvolution.toString(), name)
                .collect {
                    _detailPokemonEvolution.value = it
                }
        }
    }


}
