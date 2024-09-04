package com.redgogh.workdoc.word

interface Document {

    fun readText(): String

    fun replace(k1: String, v1: String)

    fun replace(k1: String, v1: String, k2: String, v2: String)

    fun replace(k1: String, v1: String, k2: String, v2: String, k3: String, v3: String)

    fun replace(table: Map<String, String>)

}