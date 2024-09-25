package com.redgogh.testing.controller;

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

/* Create on 2024/9/13 */

import com.redgogh.libraries.springframework.boot.web.R;
import com.redgogh.testing.dto.User;
import com.redgogh.testing.enums.TikaFile;
import com.redgogh.testing.service.TestingService;
import com.redgogh.tools.enums.Enumerates;
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
