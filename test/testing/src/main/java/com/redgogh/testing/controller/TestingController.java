package com.redgogh.testing.controller;

import com.redgogh.libraries.springframework.boot.web.R;
import com.redgogh.testing.service.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

}
