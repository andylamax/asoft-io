package tz.co.asoft.io.tools

import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.formData
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.content.PartData
import tz.co.asoft.io.file.File

inline fun formData(block: FormBuilder.() -> Unit): List<PartData> =
        formData(*FormBuilder().apply(block).build().toTypedArray())

/**
 * Form builder type used in [formData] builder function.
 */
class FormBuilder {
    val parts = mutableListOf<FormPart<*>>()

    /**
     * Append a pair [key]:[value] with optional [headers].
     */
    fun <T : Any> append(key: String, value: T, headers: Headers = Headers.Empty) {
        parts += FormPart(key, value, headers)
    }

    /**
     * Append a pair [key]:[value] with optional [headers].
     */
    fun append(key: String, value: String, headers: Headers = Headers.Empty) {
        parts += FormPart(key, value, headers)
    }

    suspend fun appendFile(filename: String, file: File) {
        val headersBuilder = HeadersBuilder()
        headersBuilder[HttpHeaders.ContentDisposition] = "filename=${file.name}"
        val headers = headersBuilder.build()
        parts += FormPart(filename, file.readBytes(), headers)
    }

    /**
     * Append a pair [key]:[value] with optional [headers].
     */
    fun append(key: String, value: Number, headers: Headers = Headers.Empty) {
        parts += FormPart(key, value, headers)
    }

    /**
     * Append a pair [key]:[value] with optional [headers].
     */
    fun append(key: String, value: ByteArray, headers: Headers = Headers.Empty) {
        parts += FormPart(key, value, headers)
    }

    fun <T : Any> append(part: FormPart<T>) {
        parts += part
    }

    fun build(): List<FormPart<*>> = parts
}