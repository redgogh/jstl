package com.redgogh.libraries.springframework.boot.web.configuration;

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

/* Create on 2023/8/24 */

import com.redgogh.libraries.springframework.boot.web.configuration.interceptors.AfterRequestInterceptor;
import com.redgogh.libraries.springframework.boot.web.configuration.interceptors.BeforeRequestInterceptor;
import com.redgogh.tools.collection.Lists;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * WebMvcConfigurationSupports 类增强
 *
 * @author RedGogh
 */
public abstract class WebMvcConfigurationAdapter extends WebMvcConfigurationSupport
        implements InitializingBean {

    private final Configuration configuration = new Configuration();

    /**
     * MVC 全局配置
     */
    @Data
    @SuppressWarnings("UnusedReturnValue")
    protected static class Configuration {
        /** 拦截器列表 */
        private List<HandlerInterceptor> interceptors = Lists.of();

        /**
         * 添加自定义拦截器
         */
        public Configuration addInterceptor(HandlerInterceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }
    }

    @Override
    public void afterPropertiesSet() {
        /* 内置拦截器 - 预处理拦截器 */
        configuration.addInterceptor(new BeforeRequestInterceptor());
        /* 用户自定义配置 */
        configure(configuration);
        /* 内置拦截器 - 请求结束后处理 */
        configuration.addInterceptor(new AfterRequestInterceptor());
    }

    public abstract void configure(Configuration configuration);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// Override
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void addInterceptors(@NotNull InterceptorRegistry registry) {
        configuration.interceptors.forEach(registry::addInterceptor);
    }

}
