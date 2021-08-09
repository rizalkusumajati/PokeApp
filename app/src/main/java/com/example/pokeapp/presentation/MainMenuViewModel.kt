package com.example.pokeapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.repository.PokemonRepository
import com.example.pokeapp.domain.models.Pokemons
import com.example.pokeapp.domain.models.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    val repository: PokemonRepository
) : ViewModel() {
    private val _listPokemon: MutableLiveData<UIState<Pokemons>> = MutableLiveData()
    val listPokemon: LiveData<UIState<Pokemons>> = _listPokemon

    private val _listDetailPokemon: MutableLiveData<Pokemons> = MutableLiveData()
    val listDetailPokemon: LiveData<Pokemons> = _listDetailPokemon

    init {
        getPokemon()
    }

    internal fun getPokemon() {
        viewModelScope.launch {
            repository.getPokemon()
                .collect {
                    _listPokemon.value = it
                }
        }
    }

    internal fun getPokemonDetail(pokemons: Pokemons) {
        viewModelScope.launch {
            Log.d("TAG", "LOADING")
            withContext(Dispatchers.Unconfined) {
                val runningTasks = pokemons.map {
                    async {
                        val getDetail = repository.getPokemonDetail(it.name)
                        getDetail
                    }
                }

                runningTasks.awaitAll()
                _listDetailPokemon.postValue(repository.getPokemonDatabse())
            }
        }
    }
}
