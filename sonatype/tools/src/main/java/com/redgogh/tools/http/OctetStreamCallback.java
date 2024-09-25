package com.redgogh.tools.http;

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

/**
 * 接口 {@link OctetStreamCallback} 定义了处理八位字节流响应的回调方法。
 *
 * <p>该接口包含两个方法：
 * <ul>
 *   <li>{@link #onFailure(Throwable)}: 当处理响应时发生错误时调用。</li>
 *   <li>{@link #onResponse(OctetStreamResponse)}: 当成功接收到响应时调用。</li>
 * </ul>
 *
 * <p>实现此接口的类可以提供自定义的处理逻辑，以便在接收到八位字节流
 * 或发生错误时采取相应的措施。
 *
 * @author RedGogh
 */
public interface OctetStreamCallback {

    /**
     * 当处理字节流响应时发生错误时调用。
     *
     * @param e 发生的异常，包含错误信息。
     */
    void onFailure(Throwable e);

    /**
     * 当成功接收到字节流响应时调用。
     *
     * @param response 成功的 {@link OctetStreamResponse} 实例。
     */
    void onResponse(OctetStreamResponse response);
}
