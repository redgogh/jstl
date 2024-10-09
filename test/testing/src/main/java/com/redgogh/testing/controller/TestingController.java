package com.redgogh.testing.controller;

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

import com.redgogh.springframework.boot.web.R;
import com.redgogh.testing.dto.User;
import com.redgogh.testing.enums.TikaFile;
import com.redgogh.testing.service.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author RedGogh
 */
@RestController
@RequestMapping("/testing")
public class TestingController {

    @Autowired
    private TestingService testingService;

    /**
     * 文件上传测试
     */
    @PostMapping("/upload-file")
    public R<Boolean> check(@RequestParam("file") MultipartFile multipartFile) {
        return R.ok(testingService.uploadFile(multipartFile));
    }

    /**
     * 异步调用测试
     */
    @PostMapping("/async-call")
    public R<Void> asyncCall(@RequestParam("sleep") int sleep) {
        testingService.asyncCall(sleep);
        return R.ok();
    }

    /**
     * tika 解析测试
     */
    @PostMapping("/tika/{type}")
    public R<String> tika(@RequestParam("file") MultipartFile multipartFile, @PathVariable("type") String type) {
        return R.ok(testingService.tika(multipartFile, TikaFile.PDF));
    }

    /**
     * User
     */
    @GetMapping("/user")
    public R<User> user() {
        return R.ok(new User("RedGogh", "redgogh@example.com", 30));
    }

}
