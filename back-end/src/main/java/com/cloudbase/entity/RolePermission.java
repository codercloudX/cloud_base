package com.cloudbase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("auth_role_permission")
public class RolePermission {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long roleId;
    private Long permissionId;
}
