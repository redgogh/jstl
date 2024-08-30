package com.redgogh.libraries.springframework.boot.web.configuration;

/* ************************************************************************
 *
 * Copyright (C) 2020 RedGogh All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/* Creates on 2022/12/23. */

import com.alibaba.fastjson2.JSONObject;
import com.redgogh.vortextools.ApiTemplateResult;
import com.redgogh.vortextools.BeanUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;

import static com.redgogh.vortextools.StringUtils.*;

/**
 * 全局异常拦截器。
 *
 * @author RedGogh
 */
@Configuration
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {

    @Data
    static class HandlerConfiguration {
        String code;
        String message;
    }

    private static HandlerConfiguration serializeHandlerConfiguration(String[] entrySet, String message) {
        JSONObject configuration = new JSONObject();
        for (String entry : entrySet) {
            String[] tokens = strtok(entry, ":");
            configuration.put(tokens[0], tokens[1]);
        }
        configuration.put("message", message);
        return configuration.toJavaObject(HandlerConfiguration.class);
    }

    private static void configure(ApiTemplateResult<String> apiTemplateResult, @NotNull String message) {
        /* 判断有没有扩展值 */
        message = message.trim();
        next: if (message.startsWith("%x")) {
            /* 遍历扩展值 */
            StringBuilder builder = new StringBuilder();
            for (char ch : strcut(message, 2, 0).toCharArray()) {
                builder.append(ch);
                if (ch == ']')
                    break;
            }
            /* 去掉扩展值的消息 */
            String cleanMessage = strcut(message, 2 + builder.length(), 0);
            if (streq(builder, "[]")) {
                message = cleanMessage;
                break next;
            }
            /* 解析属性 */
            String[] entrySet = strtok(strcut(builder, 1, -1), ",");
            HandlerConfiguration configuration =
                    serializeHandlerConfiguration(entrySet, cleanMessage);
            /* 拷贝属性 */
            BeanUtils.copyProperties(configuration, apiTemplateResult);
            return;
        }
        apiTemplateResult.setErr(message);
    }

    /**
     * 业务异常捕获
     */
    @ExceptionHandler(value = Exception.class)
    public ApiTemplateResult<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        String message = e.getMessage();
        ApiTemplateResult<String> apiTemplateResult = ApiTemplateResult.failed();
        configure(apiTemplateResult, message);
        return apiTemplateResult;
    }

    /**
     * 参数校验异常捕获
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiTemplateResult<String>
    methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        String errorMessage = "";
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError objectError : errors) {
            errorMessage = objectError.getDefaultMessage();
        }
        return ApiTemplateResult.failed(errorMessage);
    }

}
