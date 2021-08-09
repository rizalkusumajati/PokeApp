package com.example.pokeapp.data.repository

import com.example.pokeapp.data.api.ApiService
import com.example.pokeapp.data.api.models.ApiResult
import com.example.pokeapp.data.api.models.EvolutionResponse
import com.example.pokeapp.data.api.models.PokemonDetailResponse
import com.example.pokeapp.data.api.models.toEntity
import com.example.pokeapp.data.local.dao.EvolutionDao
import com.example.pokeapp.data.local.dao.PokemonDao
import com.example.pokeapp.data.local.entity.toDomain
import com.example.pokeapp.domain.models.*
import com.example.pokeapp.util.performGetRemoteDataSource
import com.example.pokeapp.util.performGetSingleDataSource
import com.example.pokeapp.util.safeApiCall
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    val apiService: ApiService,
    val pokemonDao: PokemonDao,
    val evolutionDao: EvolutionDao
){

    suspend fun getPokemon(): Flow<UIState<Pokemons>>{
        return performGetSingleDataSource(
            databaseQuery = {pokemonDao.getAllPokemon()},
            networkCall = { safeApiCall { apiService.getPokemon() }},
            converterToEntity = {it.toEntity()},
            saveCallResult = {pokemonDao.insertAll(it)},
            converterToDomain = {it.map { it.toDomain() }}
        )
    }

    suspend fun getPokemonSpecies(name: String): Flow<UIState<Pokemon>>{
        return performGetSingleDataSource(
            databaseQuery = {pokemonDao.getPokemonWithDetail(name)},
            networkCall = { safeApiCall { apiService.getPokemonSpecies(name) }},
            converterToEntity = {it.toEntity()},
            saveCallResult = {pokemonDao.updateDetail(it)},
            converterToDomain = {it.toDomain()}
        )
    }

    suspend fun getPokemonEvolution(idEvolution: String, name: String): Flow<UIState<Pokemon>>{
        return performGetSingleDataSource(
            databaseQuery = {pokemonDao.getPokemonWithDetail(name)},
            networkCall = { safeApiCall { apiService.getPokemonEvolution(idEvolution) }},
            converterToEntity = {it.toEntity()},
            saveCallResult = {pokemonDao.updateEvolution(it)},
            converterToDomain = {it.toDomain()}
        )
    }

    suspend fun getPokemonDetail(name: String): ApiResult<PokemonDetailResponse>{
        return performGetRemoteDataSource(
            networkCall = { safeApiCall { apiService.getPokemonDetail(name) }},
            converterToEntity = {it.toEntity()},
            saveCallResult = {pokemonDao.insertNewDetail(it)}
        )
    }

    suspend fun getPokemonDatabse(): Pokemons{
        return(pokemonDao.getAllPokemonWithDetail().map { it.toDomain() })
    }

    suspend fun getPokemonWithDetail(name: String): Pokemon{
        return pokemonDao.getPokemonWithDetail(name).toDomain()
    }

    suspend fun getEvolutionPokemon(idEvolution: Int): Evolutions{
        return evolutionDao.getAllEvolutionById(idEvolution).map { it.toDomain() }
    }


//    suspend fun getPokemonDetail(pokemonId: String, name: String): Flow<UIState<Pokemon>>{
//        return performGetSingleDataSource(
//            databaseQuery = {pokemonDao.getPokemonById(pokemonId)},
//            networkCall = { safeApiCall { apiService.getPokemonDetail(name) }},
//            converterToEntity = {it.toEntity()},
//            saveCallResult = {pokemonDao.insertAll(it)},
//            converterToDomain = {it.map { it.toDomain() }}
//        )
//    }


}
