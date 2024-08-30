package com.redgogh.springframework.boot.jpa;

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

/* Creates on 2023/6/25. */

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author RedGogh
 */
@Data
@MappedSuperclass
public class JpaSuperModel implements Serializable {

    /**
     * 主键时间
     */
    @Id
    @GeneratedValue(generator = "snowflakes")
    @GenericGenerator(
            name = "snowflakes",
            strategy = "com.redgogh.springframework.boot.jpa.generators.GenericSnowflakeIdGenerator"
    )
    private Long id;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateTime;

}
