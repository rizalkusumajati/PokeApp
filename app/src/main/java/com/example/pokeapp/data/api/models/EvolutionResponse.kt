package com.example.pokeapp.data.api.models

import com.example.pokeapp.data.local.entity.EvolutionEntities
import com.example.pokeapp.data.local.entity.EvolutionEntity
import com.example.pokeapp.data.local.entity.PokemonEntity
import com.example.pokeapp.data.local.entity.PokemonWithDetailEntity
import com.squareup.moshi.Json

data class EvolutionResponse(

	@field:Json(name="chain")
	val chain: Chain? = null,

	@field:Json(name="baby_trigger_item")
	val babyTriggerItem: String? = null,

	@field:Json(name="id")
	val id: Int? = null
)

fun EvolutionResponse.toEntity() =
	PokemonWithDetailEntity(
		PokemonEntity(
			id =  0,
			name =  "",
			urlImage = "",
			frontDefault =  "",
			frontShinny = "",
			idEvolution = id ?: 0
		),
		statEntity = emptyList(),
		typeEntity = emptyList(),
		abilityEntity = emptyList(),
		evolutionEntities = convertChain(chain, id?: 0)
	)

fun convertChain(chain: Chain?, idEvolutionParam: Int): EvolutionEntities{
	val listEvolution = arrayListOf<EvolutionEntity>()
	chain?.let { item ->
		var minLevel = 0
		var evolutionName = ""
		val idEvolution = idEvolutionParam
		if (item.evolutionDetails.isNotEmpty()){
			minLevel = item.evolutionDetails[0].minLevel ?: 0
		}

		item.species?.let {
			evolutionName = it.name ?: ""
		}

		listEvolution.add(
			EvolutionEntity(
				minLevel = minLevel,
				idEvolution = idEvolution,
				nameEvolution = evolutionName
			)
		)

		if (item.evolvesTo.isNotEmpty()){
			listEvolution.addAll(convertEvolveTo(item.evolvesTo, idEvolutionParam))
		}
	}

	return listEvolution
}

fun convertEvolveTo(evolvesToItem: List<EvolvesToItem>, idEvolutionParam: Int): EvolutionEntities{
	val listEvolution = arrayListOf<EvolutionEntity>()
	evolvesToItem.map { item ->
		var minLevel = 0
		var evolutionName = ""
		val idEvolution = idEvolutionParam
		if (item.evolutionDetails.isNotEmpty()){
			minLevel = item.evolutionDetails[0].minLevel ?: 0
		}

		item.species?.let {
			evolutionName = it.name ?: ""
		}

		listEvolution.add(
			EvolutionEntity(
				minLevel = minLevel,
				idEvolution = idEvolution,
				nameEvolution = evolutionName
			)
		)

		if (item.evolvesTo.isNotEmpty()){
			listEvolution.addAll(convertEvolveTo(item.evolvesTo, idEvolutionParam))
		}
	}
	return listEvolution
}

data class EvolvesToItem(

	@field:Json(name="evolution_details")
	val evolutionDetails: List<EvolutionDetailsItem> = emptyList(),

	@field:Json(name="species")
	val species: Species? = null,

	@field:Json(name="evolves_to")
	val evolvesTo: List<EvolvesToItem> = emptyList(),

	@field:Json(name="is_baby")
	val isBaby: Boolean? = null
)

data class EvolutionDetailsItem(

	@field:Json(name="item")
	val item: String? = null,

	@field:Json(name="relative_physical_stats")
	val relativePhysicalStats: String? = null,

	@field:Json(name="turn_upside_down")
	val turnUpsideDown: Boolean? = null,

	@field:Json(name="gender")
	val gender: String? = null,

	@field:Json(name="min_happiness")
	val minHappiness: String? = null,

	@field:Json(name="party_type")
	val partyType: String? = null,

	@field:Json(name="held_item")
	val heldItem: String? = null,

	@field:Json(name="known_move")
	val knownMove: String? = null,

	@field:Json(name="min_beauty")
	val minBeauty: String? = null,

	@field:Json(name="trade_species")
	val tradeSpecies: String? = null,

	@field:Json(name="trigger")
	val trigger: Trigger? = null,

	@field:Json(name="needs_overworld_rain")
	val needsOverworldRain: Boolean? = null,

	@field:Json(name="party_species")
	val partySpecies: String? = null,

	@field:Json(name="min_affection")
	val minAffection: String? = null,

	@field:Json(name="known_move_type")
	val knownMoveType: String? = null,

	@field:Json(name="time_of_day")
	val timeOfDay: String? = null,

	@field:Json(name="location")
	val location: String? = null,

	@field:Json(name="min_level")
	val minLevel: Int? = null
)

data class Chain(

	@field:Json(name="evolution_details")
	val evolutionDetails: List<EvolutionDetailsItem> = emptyList(),

	@field:Json(name="species")
	val species: Species? = null,

	@field:Json(name="evolves_to")
	val evolvesTo: List<EvolvesToItem> = emptyList(),

	@field:Json(name="is_baby")
	val isBaby: Boolean? = null
)

data class Trigger(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)
