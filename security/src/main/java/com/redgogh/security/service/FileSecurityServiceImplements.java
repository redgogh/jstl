package com.redgogh.security.service;

import com.redgogh.libext.io.File;
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
        return new File(document.getOriginalFilename()).typeEquals(".pdf");
    }

}
