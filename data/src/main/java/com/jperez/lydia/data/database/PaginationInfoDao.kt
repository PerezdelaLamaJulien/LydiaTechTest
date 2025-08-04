package com.jperez.lydia.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jperez.lydia.data.model.entity.PaginationInfoEntity

@Dao
interface PaginationInfoDao {

    @Query("SELECT * FROM paginationinfoentity WHERE seed LIKE :seed AND " +
            "page = :page AND page_size = :pageSize LIMIT 1")
    suspend fun findByInfo(seed: String, page: Int, pageSize : Int): PaginationInfoEntity?

    @Insert
    suspend fun insertAll(vararg paginationInfoEntity: PaginationInfoEntity)

    @Query("SELECT DISTINCT seed FROM paginationinfoentity")
    suspend fun getsSavedSeeds(): List<String>

    @Query("DELETE FROM paginationinfoentity WHERE seed = :seed")
    suspend fun deleteBySeed(seed: String)
}