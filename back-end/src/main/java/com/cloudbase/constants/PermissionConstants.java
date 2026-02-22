package com.cloudbase.constants;

public class PermissionConstants {
    // 系统管理模块
    public static final String SYSTEM_MANAGE = "system:manage";

    // 用户管理模块
    public static final String USER_LIST = "user:list";
    public static final String USER_CREATE = "user:create";
    public static final String USER_UPDATE = "user:update";
    public static final String USER_DELETE = "user:delete";
    public static final String USER_RESET_PASSWORD = "user:resetPassword";
    public static final String USER_DISABLE = "user:disable";
    public static final String USER_BATCH_OPERATE = "user:batchOperate";

    // 部门管理模块
    public static final String DEPARTMENT_LIST = "department:list";
    public static final String DEPARTMENT_CREATE = "department:create";
    public static final String DEPARTMENT_UPDATE = "department:update";
    public static final String DEPARTMENT_DELETE = "department:delete";
    public static final String DEPARTMENT_MERGE = "department:merge";
    public static final String DEPARTMENT_ADJUST = "department:adjust";

    // 角色管理模块
    public static final String ROLE_LIST = "role:list";
    public static final String ROLE_CREATE = "role:create";
    public static final String ROLE_UPDATE = "role:update";
    public static final String ROLE_DELETE = "role:delete";
    public static final String ROLE_ASSIGN_PERMISSION = "role:assignPermission";
    public static final String ROLE_ASSIGN_USER = "role:assignUser";

    // 权限管理模块
    public static final String PERMISSION_LIST = "permission:list";
}
