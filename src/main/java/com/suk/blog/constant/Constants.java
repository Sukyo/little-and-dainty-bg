package com.suk.blog.constant;

/**
 * @author suk
 * @version V1.0
 * @title: Constants
 * @package: com.suk.sys.common.constant
 * @description: 通用常量信息
 * @date 2019/7/4 11:00
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";


    public static final String ASC = "asc";

    public static final String DESC = "desc";

    /**
     * 成功
     */
    public final static String OPERATION_LOG_SUCCESS = "1";
    /**
     * 失败
     */
    public final static String OPERATION_LOG_FAIL = "0";

    public final static String START_TIME = "beginTime";
    public final static String END_TIME = "endTime";

    public final static String DEFAULT_FILE_PATH = "D:/file/";
    public final static String UPLOAD_FILE_PATH = DEFAULT_FILE_PATH + "upload/";
    public final static String DOWNLOAD_FILE_PATH = DEFAULT_FILE_PATH + "download/";

    public static final Integer STATUS_VALID = 1;
    public static final Integer STATUS_INVALID = 0;
    public static Integer TOP_MENU_ID = 0;
}
