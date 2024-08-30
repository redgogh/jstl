package com.redgogh.middleware.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件安全服务
 *
 * @author RedGogh
 */
public interface FileSecurityService {

    /**
     * 检查文件密级，检查文件是否可发布，如果不可发布则
     * 返回 false。
     *
     * @param document 文件内容
     * @return 如果安全检查通过返回 true
     */
    boolean checkFileSecurity(MultipartFile document);

}
