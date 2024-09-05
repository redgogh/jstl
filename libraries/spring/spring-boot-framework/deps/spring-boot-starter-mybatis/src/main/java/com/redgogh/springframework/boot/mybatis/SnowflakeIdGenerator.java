package com.redgogh.springframework.boot.mybatis;

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

/* Creates on 2023/1/13. */

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.redgogh.tools.generators.SnowflakeGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ibatis plus雪花算法id生成器.
 *
 * @author RedGogh
 */
@Component
@ConfigurationProperties(prefix = "ibatis-plus.enable-primary-generator")
public class SnowflakeIdGenerator implements IdentifierGenerator {

    /**
     * 数据中心ID
     */
    @Getter
    @Setter
    private Integer dataCenterId = 0;

    /**
     * 机器ID
     */
    @Getter
    @Setter
    private Integer machineId = 0;

    private SnowflakeGenerator snowflakeGenerator;

    @Override
    public Long nextId(Object entity) {
        if (snowflakeGenerator == null)
            snowflakeGenerator = new SnowflakeGenerator(dataCenterId, machineId);
        return snowflakeGenerator.nextId();
    }

}
