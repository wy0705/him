package com.lmxdawn.him.api.test;

public class Constants {
    public static final String SESSION_USER_BEAN = "userBean";
    //定义hbase中用到的表
    public static final String HBASE_TABLE_GID = "gid";
    public static final String HBASE_TABLE_USER_ID = "user_id";
    public static final String HBASE_TABLE_ID_USER = "id_user";
    //定义列族
    public static final String HBASE_FAMILY_GID_GID = "gid";
    public static final String HBASE_FAMILY_USER_ID_ID = "id";
    public static final String HBASE_FAMILY_ID_USER_USER = "user";

    //定义行健
    public static final String HBASE_ROW_KEY_GID_GID = "gid";
    public static final String HBASE_ROW_KEY_USER_ID_NAME = "name";
    public static final String HBASE_ROW_KEY_ID_USER_ID = "id";

    //定义列
    public static final String HBASE_COLUMN_GID_GID = "gid";
    public static final String HBASE_COLUMN_USER_ID_ID = "id";
    public static final String HBASE_COLUMN_ID_USER_PWD = "pwd";
    public static final String HBASE_COLUMN_ID_USER_NAME = "name";
    public static final String HBASE_COLUMN_ID_USER_EMAIL = "email";
}
