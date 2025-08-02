package com.jperez.lydia.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jperez.lydia.data.model.entity.ContactEntity

@Dao
interface ContactDao {

    /**
     * Retrieves a list of ContactEntity objects based on the provided pagination info key.
     *
     * @param paginationInfoKey The key used to filter the contacts based on pagination information.
     * @return A list of ContactEntity objects that match the pagination info key.
     */
    @Query("SELECT * FROM contactentity WHERE pagination_info LIKE :paginationInfoKey")
    suspend fun findByPaginationInfo(paginationInfoKey : String): List<ContactEntity>

    /**
     * Inserts a ContactEntity object into the database.
     *
     * @param contactEntity The ContactEntity object to be inserted.
     */
    @Insert
    suspend fun insertAll(vararg contactEntity: ContactEntity)
}