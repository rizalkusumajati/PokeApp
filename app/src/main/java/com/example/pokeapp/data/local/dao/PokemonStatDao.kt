package com.example.pokeapp.data.local.dao

import androidx.room.Query
import androidx.room.Transaction
import com.example.pokeapp.data.local.entity.PokemonStatEntities
import com.example.pokeapp.data.local.entity.PokemonStatEntity

interface PokemonStatDao : BaseDao<PokemonStatEntity> {
    @Transaction
    suspend fun replaceAll(bannerEntities: PokemonStatEntities) {
        deleteAll()
        insertAll(bannerEntities)
    }
    @Query("SELECT * FROM PokemonStatEntity")
    suspend fun getAllPokemon(): PokemonStatEntities

    @Query("SELECT * FROM PokemonStatEntity WHERE idPokemon= :pokemonId")
    suspend fun getPokemonById(pokemonId: Int): PokemonStatEntity

    @Query("DELETE FROM PokemonStatEntity")
    suspend fun deleteAll(): Int
}
