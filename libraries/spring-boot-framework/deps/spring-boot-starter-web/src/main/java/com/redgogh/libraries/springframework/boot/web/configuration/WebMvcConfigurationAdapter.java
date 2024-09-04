package com.redgogh.libraries.springframework.boot.web.configuration;

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

/* Create on 2023/8/24 */

import com.redgogh.libraries.springframework.boot.web.configuration.interceptors.AfterRequestInterceptor;
import com.redgogh.libraries.springframework.boot.web.configuration.interceptors.BeforeRequestInterceptor;
import com.redgogh.libext.collection.Collects;
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
        private List<HandlerInterceptor> interceptors = Collects.asList();

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
