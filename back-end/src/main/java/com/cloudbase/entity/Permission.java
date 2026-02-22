package com.cloudbase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("auth_permission")
public class Permission {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String description;
    private String type;
    private String path;
    private Long parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
