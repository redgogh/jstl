package com.redgogh.testing.service;

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

/* Create on 2024/9/13 */

import com.redgogh.testing.enums.TikaFile;
import com.redgogh.tools.Assert;
import com.redgogh.tools.io.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

/**
 * @author RedGogh
 */
@Service
public class TestingServiceImplements implements TestingService {

    @Override
    public boolean uploadFile(MultipartFile multipartFile) {
        return new File(multipartFile.getOriginalFilename()).typeEquals(".pdf");
    }

    @Override
    public void asyncCall(int sleep) {
        Assert.ifError(() -> Thread.sleep(TimeUnit.SECONDS.toMillis(sleep)));
    }

    @Override
    public String tika(MultipartFile multipartFile, TikaFile tikaFile) {
        return Assert.ifError(() -> TikaFile.readText(multipartFile.getInputStream()));
    }
}
