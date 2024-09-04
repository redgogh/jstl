package com.redgogh.workdoc.word

import com.redgogh.jdkext.Assert.xassert
import com.redgogh.jdkext.collection.Collects
import com.redgogh.jdkext.io.File

/**
 * #brief: 文档操作的抽象类，用于处理兼容性文档
 *
 * 该抽象类用于定义针对不同文档类型的兼容性操作。
 * 通过在初始化时检查文件类型来确保文件是支持的文档格式（.doc 或 .docx）。
 *
 * 注意事项：
 * - 仅支持后缀为 .doc 或 .docx 的文件，如果文件类型不匹配，
 *   将在初始化时抛出异常。
 *
 * @param file
 *        需要操作的文件对象，要求为 .doc 或 .docx 格式
 */
abstract class CompatibleDocumentOperate(private val file: File) : Document {

    init {
        xassert(file.typeMatch(".doc", ".docx"), "文件不是文档，后缀要求：.docx || .doc")
    }

    /**
     * #brief: 检查文件是否为 .docx 格式
     *
     * 该方法用于判断当前文件是否为 .docx 格式的文档。如果
     * 不是 '.docx' 后缀的话就是 '.doc' 后缀。
     *
     * @return 如果文件为 .docx 格式，返回 {@code true}；
     *         否则返回 {@code false}
     */
    fun isDocx(): Boolean = file.typeEquals(".docx")

    override fun replace(k1: String, v1: String) {
        replace(Collects.asMap(k1, v1))
    }

    override fun replace(k1: String, v1: String, k2: String, v2: String) {
        replace(Collects.asMap(k1, v1, k2, v2))
    }

    override fun replace(k1: String, v1: String, k2: String, v2: String, k3: String, v3: String) {
        replace(Collects.asMap(k1, v1, k2, v2, k3, v3))
    }

}