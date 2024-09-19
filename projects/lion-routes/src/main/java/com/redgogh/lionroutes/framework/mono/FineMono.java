package com.redgogh.lionroutes.framework.mono;

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

/* Creates on 2023/7/4. */

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

/**
 * #brief：表示没发生任何错误点 Mono<Void> 对象<p>
 *
 * 如果一个网关返回的 Mono 对象实例 instanceof 结果等于 {@link FineMono}
 * 的，那么就表示这个函数执行正常，没有出现任何错误。反之，需要开发者自行判断函数执行是否正常、
 * 或需要特殊处理。
 *
 * @author RedGogh
 */
public class FineMono<T> extends Mono<T> {

    @Override
    public void subscribe(CoreSubscriber<? super T> actual) {
        // ignore...
    }

}
