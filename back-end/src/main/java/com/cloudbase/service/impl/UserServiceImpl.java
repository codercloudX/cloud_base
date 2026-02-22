package com.cloudbase.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudbase.entity.User;
import com.cloudbase.mapper.UserMapper;
import com.cloudbase.service.UserService;
import com.cloudbase.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Map<String, Object> getUserList(Map<String, Object> params) {
        int page = (int) params.getOrDefault("page", 1);
        int size = (int) params.getOrDefault("size", 10);

        IPage<User> pageInfo = new Page<>(page, size);
        IPage<User> userPage = userMapper.selectUserList(pageInfo, params);

        Map<String, Object> result = new java.util.HashMap<>();
        result.put("records", userPage.getRecords());
        result.put("total", userPage.getTotal());
        result.put("current", userPage.getCurrent());
        result.put("size", userPage.getSize());
        result.put("pages", userPage.getPages());

        return result;
    }

    @Override
    public Map<String, Object> createUser(User user) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            user.setStatus("ACTIVE");
            // 为新用户设置默认密码
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword("123456");
            }
            int rows = userMapper.insert(user);
            if (rows > 0) {
                result.put("success", true);
                result.put("message", "用户创建成功");
                result.put("data", user);
            } else {
                result.put("success", false);
                result.put("message", "用户创建失败");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "用户创建失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> updateUser(Long id, User user) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            user.setId(id);
            int rows = userMapper.updateById(user);
            if (rows > 0) {
                result.put("success", true);
                result.put("message", "用户更新成功");
                result.put("data", user);
            } else {
                result.put("success", false);
                result.put("message", "用户更新失败");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "用户更新失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteUser(Long id) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            User user = userMapper.selectById(id);
            if (user != null) {
                user.setStatus("DELETED");
                int rows = userMapper.updateById(user);
                if (rows > 0) {
                    result.put("success", true);
                    result.put("message", "用户删除成功");
                } else {
                    result.put("success", false);
                    result.put("message", "用户删除失败");
                }
            } else {
                result.put("success", false);
                result.put("message", "用户不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "用户删除失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> resetPassword(Long id) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            User user = userMapper.selectById(id);
            if (user != null) {
                // 这里应该使用加密算法生成密码，这里简化处理
                user.setPassword("123456");
                int rows = userMapper.updateById(user);
                if (rows > 0) {
                    result.put("success", true);
                    result.put("message", "密码重置成功");
                } else {
                    result.put("success", false);
                    result.put("message", "密码重置失败");
                }
            } else {
                result.put("success", false);
                result.put("message", "用户不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "密码重置失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> disableUser(Long id) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            User user = userMapper.selectById(id);
            if (user != null) {
                user.setStatus("DISABLED");
                int rows = userMapper.updateById(user);
                if (rows > 0) {
                    result.put("success", true);
                    result.put("message", "用户禁用成功");
                } else {
                    result.put("success", false);
                    result.put("message", "用户禁用失败");
                }
            } else {
                result.put("success", false);
                result.put("message", "用户不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "用户禁用失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> enableUser(Long id) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            User user = userMapper.selectById(id);
            if (user != null) {
                user.setStatus("ACTIVE");
                int rows = userMapper.updateById(user);
                if (rows > 0) {
                    result.put("success", true);
                    result.put("message", "用户启用成功");
                } else {
                    result.put("success", false);
                    result.put("message", "用户启用失败");
                }
            } else {
                result.put("success", false);
                result.put("message", "用户不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "用户启用失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> getById(Long id) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            User user = userMapper.selectById(id);
            if (user != null) {
                result.put("success", true);
                result.put("message", "获取用户成功");
                result.put("data", user);
            } else {
                result.put("success", false);
                result.put("message", "用户不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取用户失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> batchOperate(Map<String, Object> params) {
        Map<String, Object> result = new java.util.HashMap<>();
        try {
            List<Long> ids = (List<Long>) params.getOrDefault("ids", java.util.Collections.emptyList());
            String operation = (String) params.getOrDefault("operation", "");

            int successCount = 0;
            int failureCount = 0;

            for (Long id : ids) {
                try {
                    switch (operation) {
                        case "resetPassword":
                            resetPassword(id);
                            successCount++;
                            break;
                        case "disable":
                            disableUser(id);
                            successCount++;
                            break;
                        case "enable":
                            enableUser(id);
                            successCount++;
                            break;
                        case "delete":
                            deleteUser(id);
                            successCount++;
                            break;
                        default:
                            failureCount++;
                    }
                } catch (Exception e) {
                    failureCount++;
                }
            }

            result.put("success", true);
            result.put("message", "批量操作完成");
            result.put("successCount", successCount);
            result.put("failureCount", failureCount);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量操作失败: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        User user = userMapper.selectOneByNameOrEmployeeId(username, username);
        Map<String, Object> result = new java.util.HashMap<>();

        if (user == null) {
            result.put("success", false);
            result.put("message", "用户不存在");
            return result;
        }

        if (!"ACTIVE".equals(user.getStatus())) {
            result.put("success", false);
            result.put("message", "用户已禁用");
            return result;
        }

        // 这里应该使用密码加密验证，这里简化处理
        if (!password.equals(user.getPassword())) {
            result.put("success", false);
            result.put("message", "密码错误");
            return result;
        }

        try {
            System.out.println("生成token前");
            String token = jwtUtils.generateToken(user.getName(), user.getId());
            System.out.println("生成token后: " + token);
            result.put("success", true);
            result.put("token", token);
            result.put("user", user);
            System.out.println("返回结果前: " + result);
        } catch (Exception e) {
            System.out.println("生成token异常: " + e.getMessage());
            result.put("success", false);
            result.put("message", "登录失败: " + e.getMessage());
        }

        return result;
    }
}
