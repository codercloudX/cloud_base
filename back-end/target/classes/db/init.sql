-- 创建数据库
CREATE DATABASE IF NOT EXISTS cloud_base DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE cloud_base;

-- 用户表
CREATE TABLE IF NOT EXISTS auth_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    employee_id VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    password VARCHAR(100) NOT NULL,
    department_id BIGINT,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login_at DATETIME,
    FOREIGN KEY (department_id) REFERENCES auth_department(id)
);

-- 部门表
CREATE TABLE IF NOT EXISTS auth_department (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    parent_id BIGINT,
    status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    level INT NOT NULL DEFAULT 1,
    FOREIGN KEY (parent_id) REFERENCES auth_department(id)
);

-- 角色表
CREATE TABLE IF NOT EXISTS auth_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(200),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_system BOOLEAN NOT NULL DEFAULT FALSE
);

-- 权限表
CREATE TABLE IF NOT EXISTS auth_permission (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(200),
    type VARCHAR(20) NOT NULL,
    path VARCHAR(100),
    parent_id BIGINT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES auth_permission(id)
);

-- 用户角色关系表
CREATE TABLE IF NOT EXISTS auth_user_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES auth_user(id),
    FOREIGN KEY (role_id) REFERENCES auth_role(id),
    UNIQUE KEY uk_user_role (user_id, role_id)
);

-- 角色权限关系表
CREATE TABLE IF NOT EXISTS auth_role_permission (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES auth_role(id),
    FOREIGN KEY (permission_id) REFERENCES auth_permission(id),
    UNIQUE KEY uk_role_permission (role_id, permission_id)
);

-- 插入默认数据

-- 插入默认部门
INSERT INTO auth_department (id, name, description, parent_id, status, level) VALUES
(1, '公司总部', '公司总部', NULL, 'NORMAL', 1),
(2, '技术部', '负责技术研发', 1, 'NORMAL', 2),
(3, '市场部', '负责市场推广', 1, 'NORMAL', 2),
(4, '财务部', '负责财务管理', 1, 'NORMAL', 2),
(5, '人力资源部', '负责人力资源管理', 1, 'NORMAL', 2);

-- 插入默认角色
INSERT INTO auth_role (id, name, description, is_system) VALUES
(1, '系统管理员', '拥有系统的最高权限', TRUE),
(2, '信息科工作人员', '负责日常的组织架构和权限管理工作', FALSE),
(3, '普通用户', '系统的一般使用者', FALSE);

-- 插入默认权限
INSERT INTO auth_permission (id, name, code, description, type, path, parent_id) VALUES
(1, '系统管理', 'system:manage', '系统管理模块', 'MENU', '/system', NULL),

-- 用户管理权限
(2, '用户列表', 'user:list', '查看用户列表', 'BUTTON', '/system/user', 1),
(3, '新增用户', 'user:create', '新增系统用户', 'BUTTON', '/system/user', 1),
(4, '编辑用户', 'user:update', '编辑用户信息', 'BUTTON', '/system/user', 1),
(5, '删除用户', 'user:delete', '删除用户账号', 'BUTTON', '/system/user', 1),
(6, '重置密码', 'user:resetPassword', '重置用户密码', 'BUTTON', '/system/user', 1),
(7, '禁用用户', 'user:disable', '禁用用户账号', 'BUTTON', '/system/user', 1),
(8, '批量操作', 'user:batchOperate', '批量操作用户', 'BUTTON', '/system/user', 1),

-- 部门管理权限
(9, '部门列表', 'department:list', '查看部门列表', 'BUTTON', '/system/department', 1),
(10, '新增部门', 'department:create', '新增部门', 'BUTTON', '/system/department', 1),
(11, '编辑部门', 'department:update', '编辑部门信息', 'BUTTON', '/system/department', 1),
(12, '删除部门', 'department:delete', '删除部门', 'BUTTON', '/system/department', 1),
(13, '合并部门', 'department:merge', '合并部门', 'BUTTON', '/system/department', 1),
(14, '调整层级', 'department:adjust', '调整部门层级', 'BUTTON', '/system/department', 1),

-- 角色管理权限
(15, '角色列表', 'role:list', '查看角色列表', 'BUTTON', '/system/role', 1),
(16, '新增角色', 'role:create', '新增角色', 'BUTTON', '/system/role', 1),
(17, '编辑角色', 'role:update', '编辑角色信息', 'BUTTON', '/system/role', 1),
(18, '删除角色', 'role:delete', '删除角色', 'BUTTON', '/system/role', 1),
(19, '分配权限', 'role:assignPermission', '为角色分配权限', 'BUTTON', '/system/role', 1),
(20, '分配用户', 'role:assignUser', '为用户分配角色', 'BUTTON', '/system/role', 1);

-- 为系统管理员角色分配所有权限
INSERT INTO auth_role_permission (role_id, permission_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8),
(1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14),
(1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20);

-- 为信息科工作人员分配权限
INSERT INTO auth_role_permission (role_id, permission_id) VALUES
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8),
(2, 9), (2, 10), (2, 11), (2, 12), (2, 13), (2, 14),
(2, 15), (2, 16), (2, 17), (2, 18), (2, 19), (2, 20);

-- 为普通用户分配权限
INSERT INTO auth_role_permission (role_id, permission_id) VALUES
(3, 1);

-- 插入默认管理员用户
INSERT INTO auth_user (id, name, employee_id, email, phone, password, department_id, status) VALUES
(1, '管理员', 'admin', 'admin@example.com', '13800138000', '123456', 1, 'ACTIVE');

-- 为管理员用户分配系统管理员角色
INSERT INTO auth_user_role (user_id, role_id) VALUES
(1, 1);
