package com.hz.blog.constant;

public class Constant {
    public static final String  BLACKTOKEN = "black-token-";//全局请求token

    public static final String LOGINTOKEN ="login-token-";

    public static final String  ISEXPIRE = "isExpire";

    public static final String IS_LOGIN ="isLogin";

    public static final String APP_TOKEN ="appToken";
    //public static final String  USERROLES = "userRoles";

    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /** 数据权限过滤 */
    public static final String SQL_FILTER = "sql_filter";
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static final Integer ADMIN =1;
    public static final Integer USER =2;
    public static final Integer PRODUCT =3;
    public static final Integer ASSISTANT =4;
    public static final Integer SUPERADMIN =5;

    public static final String TYPE_ADMIN = "admin";
    public static final String TYPE_USER = "user";
    public static final String TYPE_PRODUCT = "product";
    public static final String TYPE_ASSISTANT = "assistant";
    public static final String TYPE_SUPERADMIN = "superadmin";

    public static final String LOG_LOGIN ="1";
    public static final String LOG_POST ="2";

}
