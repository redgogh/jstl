package com.redgogh.security.controller;

import com.redgogh.libraries.springframework.boot.web.R;
import com.redgogh.security.service.FileSecurityService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件安全服务
 *
 * @author RedGogh
 */
@RestController
@RequestMapping("/")
public class FileSecurityController {

    @Autowired
    private FileSecurityService fileSecurityService;

    /**
     * 文件安全检查
     */
    @PostMapping("/check/file")
    public R<Boolean> check(@RequestParam("document") MultipartFile document) {
        return R.ok(fileSecurityService.checkFileSecurity(document));
    }

    @Data
    public static class User {
        private Long    id;
        private String  name;
        private Integer age;
    }

    /**
     * 文件安全检查
     */
    @PostMapping("/save")
    public R<User> save(@RequestBody User user) {
        return R.ok(user);
    }

    @GetMapping("/get")
    public R<User> get(User user) {
        return R.ok(user);
    }

}
