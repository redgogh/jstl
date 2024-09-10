package com.redgogh.tools.http;

import java.util.LinkedHashMap;

/**
 * `MultipartBody` 是一个继承自 `LinkedHashMap<String, Object>` 的类，用于构建 multipart/form-data 请求体，
 * 主要用于处理文件上传和复杂数据提交。
 *
 * <p>该类存储键值对形式的请求体，其中键为字段名，值为字段值或文件对象。
 *
 * <p>主要功能包括：
 * <ul>
 *     <li>存储和管理 multipart 请求体中的各个部分。</li>
 *     <li>支持存储文件和其他数据类型。</li>
 * </ul>
 *
 * <p>使用场景：
 * <ul>
 *     <li>构建 multipart/form-data 请求体，特别是在需要上传文件或发送复杂数据时。</li>
 *     <li>简化 multipart 请求体的构建过程。</li>
 * </ul>
 *
 * @see LinkedHashMap
 * @since 1.0
 * @author RedGogh
 */
public class MultipartBody extends LinkedHashMap<String, Object> {

}
