package com.example.pokeapp.data.local.entity

import androidx.room.Entity
import com.example.pokeapp.domain.models.Type

@Entity(primaryKeys = ["idPokemon", "idType"])
data class TypeEntity(
    val idPokemon: Int,
    val idType: String,
)

fun TypeEntity.toDomain() =
    Type(
        nameType = idType
    )

typealias TypeEntities = List<TypeEntity>
