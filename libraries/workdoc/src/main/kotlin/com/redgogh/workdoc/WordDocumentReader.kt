package com.redgogh.workdoc

interface WordDocumentReader {

    /**
     * #brief: 读取 Word 文档中的纯文本
     *
     * 该函数用于从 Word 文档中提取并返回纯文本内容，不包含任何格式信息。
     * 注意，该方法仅处理文本内容，忽略文档中的图片、表格和其他非文本元素。
     *
     * @return 返回 Word 文档中的纯文本内容
     */
    fun readText(): String

}