package com.suk.blog.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author suk
 * @version V1.0
 * @title: SftpTool
 * @package: com.suk.share.util
 * @description: sftp工具
 * @date 2019/7/15 17:13
 */
@Slf4j
public class SftpTool {

    /**
     * 设置第一次登陆的时候提示，可选值：(ask | yes | no)
     */
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";

    /**
     * 创建SFTP连接
     *
     * @return
     * @throws Exception
     */
    private static ChannelSftp createSftp(SftpProperties config) throws Exception {
        JSch jsch = new JSch();
        log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "], use password[" + config.getPassword() + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort(), config.getSessionStrictHostKeyChecking());
        session.setPassword(config.getPassword());
        if (config.getSessionConnectTimeout() != null) {
            session.connect(config.getSessionConnectTimeout());
        } else {
            session.connect(15000);
        }

        log.info("Session connected to {}.", config.getHost());
        Channel channel;
        if (StringUtils.isBlank(config.getProtocol())) {
            channel = session.openChannel("sftp");
        } else {
            channel = session.openChannel(config.getProtocol());
        }
        if (config.getChannelConnectedTimeout() != null) {
            channel.connect(config.getChannelConnectedTimeout());
        } else {
            channel.connect(15000);
        }

        log.info("Channel created to {}.", config.getHost());

        return (ChannelSftp) channel;
    }

    /**
     * 加密秘钥方式登陆
     *
     * @return
     */
    private static ChannelSftp connectByKey(SftpProperties config) throws Exception {
        JSch jsch = new JSch();

        // 设置密钥和密码 ,支持密钥的方式登陆
        if (StringUtils.isNotBlank(config.getPrivateKey())) {
            if (StringUtils.isNotBlank(config.getPassphrase())) {
                // 设置带口令的密钥
                jsch.addIdentity(config.getPrivateKey(), config.getPassphrase());
            } else {
                // 设置不带口令的密钥
                jsch.addIdentity(config.getPrivateKey());
            }
        }
        log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "], use private key[" + config.getPrivateKey()
                + "] with passphrase[" + config.getPassphrase() + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort(), config.getSessionStrictHostKeyChecking());
        // 设置登陆超时时间
        session.connect(config.getSessionConnectTimeout());
        log.info("Session connected to " + config.getHost() + ".");

        // 创建sftp通信通道
        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());
        log.info("Channel created to " + config.getHost() + ".");
        return (ChannelSftp) channel;
    }

    /**
     * 上传文件
     *
     * @param targetPath
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static boolean uploadFile(SftpProperties config, String targetPath, InputStream inputStream) throws Exception {
        ChannelSftp sftp = createSftp(config);
        try {
            sftp.cd(config.getRoot());
            log.info("Change path to {}", config.getRoot());
            String fileName;
            int index = targetPath.lastIndexOf("/");
            if (index != -1) {
                String fileDir = targetPath.substring(0, index);
                fileName = targetPath.substring(index + 1);
                boolean dirs = createDirs(fileDir, sftp);
                if (!dirs) {
                    log.error("Remote path error. path:{}", targetPath);
                    throw new Exception("Upload File failure");
                }
            } else {
                fileName = targetPath;
            }

            sftp.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            log.error("Upload file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Upload File failure");
        } finally {
            disconnect(sftp);
        }
    }

    /**
     * 下载文件
     *
     * @param targetPath
     * @return
     * @throws Exception
     */
    public static File downloadFile(SftpProperties config, String targetPath) throws Exception {
        ChannelSftp sftp = createSftp(config);
        OutputStream outputStream = null;
        try {
            sftp.cd(config.getRoot());
            log.info("Change path to {}", config.getRoot());
            String fileName;
            int index = targetPath.lastIndexOf("/");
            if (index != -1) {
                fileName = targetPath.substring(index + 1);
            } else {
                fileName = targetPath;
            }
            File file = new File(fileName);

            outputStream = new FileOutputStream(file);
            sftp.get(targetPath, outputStream);
            log.info("Download file success. TargetPath: {}", targetPath);
            return file;
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Download File failure");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            disconnect(sftp);
        }
    }

    /**
     * 从服务器下载文件
     *
     * @param sourcePath 文件原路径
     * @param targetPath 文件存储目标路径
     * @return
     * @throws Exception
     */
    public static File downloadFile(SftpProperties config, String sourcePath, String targetPath) throws Exception {
        ChannelSftp sftp = createSftp(config);
        OutputStream outputStream = null;
        try {
            sftp.cd(config.getRoot());
            log.info("Change path to {}", config.getRoot());
            String fileName;
            int index = sourcePath.lastIndexOf("/");
            if (index != -1) {
                fileName = sourcePath.substring(index + 1);
            } else {
                fileName = sourcePath;
            }
            File file = new File(targetPath);

            outputStream = new FileOutputStream(file);
            sftp.get(fileName, outputStream);
            log.info("Download file success. TargetPath: {}", targetPath);
            return file;
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Download File failure");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            disconnect(sftp);
        }
    }

    /**
     * 删除文件
     *
     * @param targetPath
     * @return
     * @throws Exception
     */
    public static boolean deleteFile(SftpProperties config, String targetPath) throws Exception {
        ChannelSftp sftp = null;
        try {
            sftp = createSftp(config);
            sftp.cd(config.getRoot());
            sftp.rm(targetPath);
            return true;
        } catch (Exception e) {
            log.error("Delete file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Delete File failure");
        } finally {
            disconnect(sftp);
        }
    }

    /**
     * 创建一级或者多级目录
     *
     * @param dirPath
     * @param sftp
     * @return
     */
    private static boolean createDirs(String dirPath, ChannelSftp sftp) {
        if (dirPath != null && !dirPath.isEmpty()
                && sftp != null) {
            String[] dirs = Arrays.stream(dirPath.split("/"))
                    .filter(StringUtils::isNotBlank)
                    .toArray(String[]::new);

            for (String dir : dirs) {
                try {
                    sftp.cd(dir);
                    log.info("Change directory {}", dir);
                } catch (Exception e) {
                    try {
                        sftp.mkdir(dir);
                        log.info("Create directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Create directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                    try {
                        sftp.cd(dir);
                        log.info("Change directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Change directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 创建session
     *
     * @param jsch
     * @param host
     * @param username
     * @param port
     * @param sessionStrictHostKeyChecking
     * @return
     * @throws Exception
     */
    private static Session createSession(JSch jsch, String host, String username, Integer port, String sessionStrictHostKeyChecking) throws Exception {
        Session session = null;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new Exception(host + " session is null");
        }
        if (StringUtils.isNoneBlank(sessionStrictHostKeyChecking)) {
            session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, sessionStrictHostKeyChecking);
        } else {
            session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, "no");
        }
        return session;
    }

    /**
     * 关闭连接
     *
     * @param sftp
     */
    private static void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                    log.info("sftp is closed already");
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}