package com.example.pokeapp.data.api.models

import com.example.pokeapp.data.local.entity.PokemonEntity
import com.example.pokeapp.domain.models.Pokemon
import com.squareup.moshi.Json

data class PokemonResponse(

	@Json(name="next")
	val next: String? = null,

	@Json(name="previous")
	val previous: Any? = null,

	@Json(name="count")
	val count: Int? = null,

	@Json(name="results")
	val results: List<ResultsItem> = emptyList()
)

fun PokemonResponse.toEntity() = results.map { it.toEntity() }

data class ResultsItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)
fun ResultsItem.toEntity() =
	PokemonEntity(
		id = url?.removeSuffix("/")?.split("/")?.last()?.toIntOrNull() ?: 0,
		name = name ?: "",
	)
