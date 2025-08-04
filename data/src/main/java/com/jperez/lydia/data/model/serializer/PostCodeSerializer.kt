package com.jperez.lydia.data.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


/**
 * Custom serializer for postcodes that can handle both integer and string formats.
 *
 * This serializer attempts to decode an integer first, and if that fails, it falls back to decoding a string.
 * This is useful for APIs that may return postcodes in different formats.
 */
object PostCodeSerializer : KSerializer<String> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("postcode", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(value)
    }

    override fun deserialize(decoder: Decoder): String {
        return try {
            decoder.decodeInt().toString()
        } catch (e: Exception) {
            decoder.decodeString()
        }
    }
}