package com.suk.blog.util;

import lombok.Data;

/**
 * @author suk
 * @version V1.0
 * @title: SftpProperties
 * @package: com.suk.share.config
 * @description: sftp服务配置
 * @date 2019/7/15 16:15
 */
@Data
public class SftpProperties {
    private String host;

    private Integer port;

    private String protocol;

    private String username;

    private String password;

    private String root;

    private String privateKey;

    private String passphrase;

    private String sessionStrictHostKeyChecking;

    private Integer sessionConnectTimeout;

    private Integer channelConnectedTimeout;

    private String domain;

    public SftpProperties() {
    }

    public SftpProperties(String host, Integer port, String username, String password, String root) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.root = root;
    }

    public SftpProperties(String host, Integer port, String protocol, String username, String password, String root, String privateKey, String passphrase, String sessionStrictHostKeyChecking, Integer sessionConnectTimeout, Integer channelConnectedTimeout) {
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.username = username;
        this.password = password;
        this.root = root;
        this.privateKey = privateKey;
        this.passphrase = passphrase;
        this.sessionStrictHostKeyChecking = sessionStrictHostKeyChecking;
        this.sessionConnectTimeout = sessionConnectTimeout;
        this.channelConnectedTimeout = channelConnectedTimeout;
    }
}
