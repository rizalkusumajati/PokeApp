package com.example.pokeapp.data.local.entity

import androidx.room.Entity
import com.example.pokeapp.domain.models.Ability

@Entity(primaryKeys = ["idPokemon", "idAbility"])
data class AbilityEntity(
    val idPokemon: Int,
    val idAbility: String,
    val descAbility: String
)

fun AbilityEntity.toDomain() =
    Ability(
        nameAbility = idAbility,
        descAbility = descAbility
    )

typealias AbilityEntities = List<AbilityEntity>
