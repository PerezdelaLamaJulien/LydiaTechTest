package com.jperez.lydia.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = PaginationInfoEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("pagination_info"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )]
)
data class ContactEntity(
    @PrimaryKey @ColumnInfo(name = "id") val uid: String,
    @ColumnInfo(name = "pagination_info") val paginationInfo: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "post_code") val postCode: String,
    @ColumnInfo(name = "street_name") val streetName: String,
    @ColumnInfo(name = "street_number") val streetNumber: Int,
    @ColumnInfo(name = "age") val age : Int,
    @ColumnInfo(name = "registration_age") val registrationAge : Int,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String,
    @ColumnInfo(name = "date_of_registration") val dateOfRegistration: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "cell") val cell: String,
    @ColumnInfo(name = "largePicture") val largePicture: String,
    @ColumnInfo(name = "mediumPicture") val mediumPicture: String,
    @ColumnInfo(name = "thumbPicture") val thumbPicture: String,
    @ColumnInfo(name = "coordinates_latitude") val coordinatesLatitude: String,
    @ColumnInfo(name = "coordinates_longitude") val coordinatesLongitude: String,
    @ColumnInfo(name = "timezone_offset") val timeZoneOffset: String,
    @ColumnInfo(name = "timezone_description") val timeZoneDescription: String,
    @ColumnInfo(name = "login_username") val loginUsername: String,
    @ColumnInfo(name = "login_password") val loginPassword: String,
    @ColumnInfo(name = "login_salt") val loginSalt: String,
    @ColumnInfo(name = "login_md5") val loginMd5: String,
    @ColumnInfo(name = "login_sha1") val loginSha1: String,
    @ColumnInfo(name = "login_sha256") val loginSha256: String,
    @ColumnInfo(name = "contact_name") val contactName: String,
    @ColumnInfo(name = "contact_value") val contactValue: String?,
    @ColumnInfo(name = "nationality") val nationality: String,

    )