package com.example.pokeapp.data.local.entity

import androidx.room.Entity
import com.example.pokeapp.domain.models.Stat

@Entity(primaryKeys = ["idPokemon", "idStat"])
data class PokemonStatEntity(
    val idPokemon: Int,
    val idStat: String,
    val baseState: Int,
    val effor: Int
)

fun PokemonStatEntity.toDomain() =
    Stat(
        nameStat = idStat,
        baseStat = baseState
    )

typealias PokemonStatEntities = List<PokemonStatEntity>
