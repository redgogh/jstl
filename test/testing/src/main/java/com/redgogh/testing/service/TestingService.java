package com.redgogh.testing.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author RedGogh
 */
public interface TestingService {

    boolean uploadFile(MultipartFile multipartFile);

    void asyncCall(int sleep);

}
