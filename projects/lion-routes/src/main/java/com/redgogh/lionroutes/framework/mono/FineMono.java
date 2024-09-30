package com.redgogh.lionroutes.framework.mono;

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
