package com.redgogh.workdoc

import com.redgogh.jdkext.io.File
import com.redgogh.workdoc.word.Paragraph
import com.redgogh.workdoc.word.docx.DocXOperateImplements


object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("Desktop://档案管理系统OA公文数据接口上报文档v1.1.5.docx")
//        println(DocXOperateImplements(file).readText())
        println(DocXOperateImplements(file).replace("author", "罗添胜"))

    }

}