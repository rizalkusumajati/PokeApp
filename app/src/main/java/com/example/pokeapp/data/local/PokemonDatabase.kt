package com.example.pokeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokeapp.data.local.dao.AbilityDao
import com.example.pokeapp.data.local.dao.EvolutionDao
import com.example.pokeapp.data.local.dao.PokemonDao
import com.example.pokeapp.data.local.entity.*

@Database(
    entities = [
        PokemonEntity::class,
        PokemonStatEntity::class,
        TypeEntity::class,
        EvolutionEntity::class,
        AbilityEntity::class
    ], version = 1, exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun abilityDao(): AbilityDao
    abstract fun evolutionDao(): EvolutionDao
}
