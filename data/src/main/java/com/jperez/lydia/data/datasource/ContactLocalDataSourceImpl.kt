package com.jperez.lydia.data.datasource

import com.jperez.lydia.data.database.ContactDao
import com.jperez.lydia.data.database.PaginationInfoDao
import com.jperez.lydia.data.mapper.ContactAtoEntityMapper
import com.jperez.lydia.data.model.ContactATO
import com.jperez.lydia.data.model.entity.PaginationInfoEntity
import org.koin.java.KoinJavaComponent.inject
import java.util.UUID

class ContactLocalDataSourceImpl : ContactLocalDataSource {
    private val paginationInfoDao: PaginationInfoDao by inject(PaginationInfoDao::class.java)
    private val contactDao: ContactDao by inject(ContactDao::class.java)
    private val mapper: ContactAtoEntityMapper by inject(ContactAtoEntityMapper::class.java)

    override suspend fun getRequestedContactFromDatabase(
        seed: String,
        page: Int,
        pageSize: Int
    ): List<ContactATO>? {
        try {
            val paginationInfoEntity = paginationInfoDao.findByInfo(seed, page, pageSize)
            if (paginationInfoEntity == null) {
                return null
            } else {
                val entities = contactDao.findByPaginationInfo(paginationInfoEntity.uid)
                return mapper.mapEntityListToATOList(entities)
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun saveContactsToDatabase(
        seed: String,
        page: Int,
        pageSize: Int,
        contacts: List<ContactATO>
    ) {
        val paginationId = UUID.randomUUID().toString()
        val paginationEntity = PaginationInfoEntity(
            uid = paginationId,
            seed = seed,
            page = page,
            pageSize = pageSize
        )

        paginationInfoDao.insertAll(paginationEntity)
        contactDao.insertAll(
            *mapper.mapATOListToEntityList(
                contacts,
                paginationInfoId = paginationId
            ).toTypedArray()
        )
    }

}
