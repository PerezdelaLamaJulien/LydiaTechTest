package com.jperez.lydia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jperez.lydia.data.model.entity.ContactEntity
import com.jperez.lydia.data.model.entity.PaginationInfoEntity

@Database(entities = [PaginationInfoEntity::class, ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paginationInfoDao(): PaginationInfoDao
    abstract fun contactDao(): ContactDao
}