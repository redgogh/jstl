package com.redgogh.middleware.service;

import com.redgogh.vortextools.io.UFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件安全服务
 *
 * @author RedGogh
 */
@Service
public class FileSecurityServiceImplements implements FileSecurityService {

    @Override
    public boolean checkFileSecurity(MultipartFile document) {
        return new UFile(document.getOriginalFilename()).typeEquals(".pdf");
    }

}
