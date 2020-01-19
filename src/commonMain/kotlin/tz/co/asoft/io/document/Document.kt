package tz.co.asoft.io.document

import kotlinx.serialization.Serializable

@Serializable
open class Document {
    var id: Long? = null
    var uid = ""
    open var name = ""
    open var url = ""
    open var permits = mutableListOf<String>()
    open var metadata = mutableMapOf<String, String>()
}