package com.redgogh.libraries.springframework.boot.web;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 RedGogh                                                    *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2023/5/13. */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redgogh.libraries.springframework.boot.web.utils.WebRequests;
import com.redgogh.libext.time.DateFormatter;
import lombok.Data;

import java.io.Serializable;

import static com.redgogh.libext.Assert.xassert;
import static com.redgogh.libext.StringUtils.streq;
import static com.redgogh.libext.StringUtils.strwfmt;

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

    public void throwIfError() {
        xassert(!isError(), "%x[code: %s] %s", code, message);
    }

    public T as() {
        return data;
    }

}
