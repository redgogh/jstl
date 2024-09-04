package com.redgogh.office

import com.redgogh.tools.collection.Collects
import com.redgogh.tools.io.File

abstract class AbstractCompatibleDocument(protected val file: File) : Document {

    override fun replace(k1: String, v1: String) =
        replace(Collects.asMap(k1, v1))

    override fun replace(k1: String, v1: String, k2: String, v2: String) =
        replace(Collects.asMap(k1, v1, k2, v2))

    override fun replace(k1: String, v1: String, k2: String, v2: String, k3: String, v3: String) =
        replace(Collects.asMap(k1, v1, k2, v2, k3, v3))

}