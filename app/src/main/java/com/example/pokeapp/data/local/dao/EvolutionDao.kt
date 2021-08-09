package com.example.pokeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokeapp.data.local.entity.EvolutionEntities
import com.example.pokeapp.data.local.entity.EvolutionEntity
import com.example.pokeapp.data.local.entity.EvolutionWithPokemonEntities
import com.example.pokeapp.data.local.entity.EvolutionWithPokemonEntity

@Dao
interface EvolutionDao : BaseDao<EvolutionEntity> {
    @Transaction
    suspend fun replaceAll(bannerEntities: EvolutionEntities) {
        deleteAll()
        insertAll(bannerEntities)
    }
    @Query("SELECT * FROM EvolutionEntity")
    suspend fun getAllPokemon(): EvolutionEntities

    @Transaction
    @Query("SELECT * FROM EvolutionEntity WHERE idEvolution = :idEvolution")
    suspend fun getAllEvolutionById(idEvolution: Int): EvolutionWithPokemonEntities

    @Query("DELETE FROM EvolutionEntity")
    suspend fun deleteAll(): Int
}
