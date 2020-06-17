package com.suk.blog.controller;

import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.util.SftpProperties;
import com.suk.blog.util.SftpTool;
import com.suk.blog.util.UUIDUitl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author suk
 * @version V1.0
 * @title: CommonUploadController
 * @package: com.suk.blog.controller
 * @description: 文件上传
 * @date 2019/10/9 11:33
 */
@Controller
public class UploadController extends BaseController {

    @Value("${sftp.client.host}")
    private String host;
    @Value("${sftp.client.port}")
    private Integer port;
    @Value("${sftp.client.username}")
    private String username;
    @Value("${sftp.client.password}")
    private String password;
    @Value("${sftp.client.root}")
    private String root;
    @Value("${sftp.client.domain}")
    private String domain;

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);


    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            SftpProperties sftpProperties = new SftpProperties(host, port, username, password, root);
            String filename = file.getOriginalFilename();
            int lastIndexof = filename.lastIndexOf(".");
            String suffix = filename.substring(lastIndexof, filename.length());
            String uuid = UUIDUitl.getUUID() + suffix;
            SftpTool.uploadFile(sftpProperties, uuid, file.getInputStream());
            String url = domain + uuid;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", file.getOriginalFilename());
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

}
