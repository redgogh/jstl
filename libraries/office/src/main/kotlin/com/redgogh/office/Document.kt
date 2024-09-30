package com.redgogh.office

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2019-2024 RedGogh All rights reserved.                          *|
|*                                                                                  *|
|*    Licensed under the Apache License, Version 2.0 (the "License");               *|
|*    you may not use this file except in compliance with the License.              *|
|*    You may obtain a copy of the License at                                       *|
|*                                                                                  *|
|*        http://www.apache.org/licenses/LICENSE-2.0                                *|
|*                                                                                  *|
|*    Unless required by applicable law or agreed to in writing, software           *|
|*    distributed under the License is distributed on an "AS IS" BASIS,             *|
|*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.      *|
|*    See the License for the specific language governing permissions and           *|
|*    limitations under the License.                                                *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.redgogh.tools.io.File

/**
 * #brief: 文档接口，用于定义基本的文档操作
 *
 * 该接口定义了读取、替换文本和保存文档的基本操作方法。
 * 实现此接口的类需提供相应的文档处理逻辑。
 */
interface Document {

    /**
     * #brief: 获取文档中的文本内容
     *
     * 该方法用于读取并返回文档中的所有文本内容。
     *
     * @return 返回文档中的纯文本内容
     */
    fun getText(): String

    /**
     * #brief: 替换文档中的文本
     *
     * 在文档中查找指定的关键字 {@code k1} 并替换为对应的值 {@code v1}。
     *
     * @param k1
     *        需要替换的关键字
     * @param v1
     *        替换关键字的值
     */
    fun replace(k1: String, v1: String)

    /**
     * #brief: 替换文档中的多个文本
     *
     * 在文档中查找指定的关键字 {@code k1} 和 {@code k2} 并替换为对应的值 {@code v1}
     * 和 {@code v2}。
     *
     * @param k1
     *        需要替换的第一个关键字
     * @param v1
     *        替换第一个关键字的值
     * @param k2
     *        需要替换的第二个关键字
     * @param v2
     *        替换第二个关键字的值
     */
    fun replace(k1: String, v1: String, k2: String, v2: String)

    /**
     * #brief: 替换文档中的多个文本
     *
     * 在文档中查找指定的关键字 {@code k1}, {@code k2} 和 {@code k3} 并替换为对应的值
     * {@code v1}, {@code v2} 和 {@code v3}。
     *
     * @param k1
     *        需要替换的第一个关键字
     * @param v1
     *        替换第一个关键字的值
     * @param k2
     *        需要替换的第二个关键字
     * @param v2
     *        替换第二个关键字的值
     * @param k3
     *        需要替换的第三个关键字
     * @param v3
     *        替换第三个关键字的值
     */
    fun replace(k1: String, v1: String, k2: String, v2: String, k3: String, v3: String)

    /**
     * #brief: 使用键值对替换文档中的文本
     *
     * 使用传入的键值对 {@code table} 在文档中查找关键字并替换为对应的值。
     *
     * @param table
     *        包含替换文本的键值对集合
     */
    fun replace(table: Map<String, String>)

    /**
     * #brief: 保存当前文档
     *
     * 该方法用于保存当前文档的修改。
     */
    fun save()

    /**
     * #brief: 保存当前文档到指定文件
     *
     * 该方法将当前文档保存到指定的文件对象 {@code file} 中。
     *
     * @param file
     *        保存文档的目标文件对象
     */
    fun save(file: File)

}