package com.example.pokeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokeapp.data.local.entity.AbilityEntities
import com.example.pokeapp.data.local.entity.AbilityEntity

@Dao
interface AbilityDao: BaseDao<AbilityEntity> {
    @Transaction
    suspend fun replaceAll(bannerEntities: AbilityEntities) {
        deleteAll()
        insertAll(bannerEntities)
    }
    @Query("UPDATE AbilityEntity SET descAbility = :desc WHERE idAbility = :idAbility")
    suspend fun updateDescAbility(idAbility : String, desc: String)

    @Query("DELETE FROM AbilityEntity")
    suspend fun deleteAll(): Int
}

