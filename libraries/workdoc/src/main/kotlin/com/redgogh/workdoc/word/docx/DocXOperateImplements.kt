package com.redgogh.workdoc.word.docx

import com.redgogh.jdkext.AnyObjects.atos
import com.redgogh.jdkext.collection.Collects
import com.redgogh.jdkext.io.File
import com.redgogh.workdoc.word.CompatibleDocumentOperate
import com.redgogh.workdoc.word.Paragraph
import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.apache.poi.xwpf.usermodel.XWPFStyles

/**
 * #brief: 实现文档操作的具体类
 *
 * 该类继承自 {@code CompatibleDocumentOperate} 并实现对 .docx 文档的具体操作。
 * 通过构造函数传入的文件对象来初始化，并确保文件类型为 .doc 或 .docx。
 *
 * @param file
 *        需要操作的文档文件，要求为 .doc 或 .docx 格式
 */
class DocXOperateImplements(file: File) : CompatibleDocumentOperate(file) {

    private val document: XWPFDocument

    private val styles: XWPFStyles

    private val extractor: XWPFWordExtractor

    init {
        file.openByteReader().use {
            document = XWPFDocument(it)
            styles = document.styles
            extractor = XWPFWordExtractor(document)
        }
    }

    private fun extractRun(run: XWPFRun) {

    }

    private fun replaceParagraphText(table: Map<String, String>) {
        for (paragraph in document.paragraphs)
            paragraph.runs.forEach { extractRun(it) }
    }

    override fun readText(): String {
        return atos(extractor.text)
    }

    override fun replace(table: Map<String, String>) {
        replaceParagraphText(table)
    }

}