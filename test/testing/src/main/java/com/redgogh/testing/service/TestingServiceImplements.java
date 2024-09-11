package com.redgogh.testing.service;

import com.redgogh.tools.io.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

import static com.redgogh.tools.Assert.throwIfError;

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
        throwIfError(() -> Thread.sleep(TimeUnit.SECONDS.toMillis(sleep)));
    }

}
