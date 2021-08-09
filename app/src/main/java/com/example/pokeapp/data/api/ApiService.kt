package com.example.pokeapp.data.api

import com.example.pokeapp.data.api.models.EvolutionResponse
import com.example.pokeapp.data.api.models.PokemonDetailResponse
import com.example.pokeapp.data.api.models.PokemonResponse
import com.example.pokeapp.data.api.models.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=50")
    suspend fun getPokemon(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name:String): PokemonDetailResponse

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name:String): PokemonSpeciesResponse

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolution(@Path("id") id:String): EvolutionResponse
}
