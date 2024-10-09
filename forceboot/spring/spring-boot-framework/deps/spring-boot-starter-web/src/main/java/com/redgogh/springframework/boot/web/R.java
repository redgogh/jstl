package com.redgogh.springframework.boot.web;

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

/* Creates on 2023/5/13. */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redgogh.springframework.boot.web.utils.WebRequests;
import com.redgogh.tools.time.DateFormatter;
import lombok.Data;

import java.io.Serializable;

import static com.redgogh.tools.Assert.xassert;
import static com.redgogh.tools.StringUtils.streq;
import static com.redgogh.tools.StringUtils.strwfmt;

/**
 * @author RedGogh
 */
@Data
public class R<T> implements Serializable {

    /**
     * 请求id
     */
    private String request = WebRequests.getRequestId();
    /**
     * API 接口状态码
     */
    private String code;
    /**
     * 携带的数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 时间戳
     */
    private Long timestamp = DateFormatter.currentTimestamp();

    public R() {
    }

    public R(String code, T data, String message, Object... args) {
        this.code = code;
        this.data = data;
        this.message = strwfmt(message, args);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// success
    /////////////////////////////////////////////////////////////////////////////////

    public static <T> R<T> ok() {
        return ok((T) null);
    }

    public static <T> R<T> ok(T data) {
        return ok("200", data, "请求成功");
    }

    public static <T> R<T> ok(String code, T data, String message, Object... args) {
        return new R<>(code, data, message, args);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// failed
    /////////////////////////////////////////////////////////////////////////////////

    public static <T> R<T> failed() {
        return failed("请求失败");
    }

    public static <T> R<T> failed(String message) {
        return failed("500", null, message);
    }

    public static <T> R<T> failed(String code, T data, String message, Object... args) {
        return new R<>(code, data, message, args);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// not static
    /////////////////////////////////////////////////////////////////////////////////

    @JsonIgnore
    public boolean isError() {
        return !streq("200", code);
    }

    public void ifError() {
        xassert(!isError(), "%x[code: %s] %s", code, message);
    }

    public T as() {
        return data;
    }

}
