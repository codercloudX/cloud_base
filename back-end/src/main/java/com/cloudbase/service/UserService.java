package com.cloudbase.service;

import com.cloudbase.entity.User;

import java.util.Map;

public interface UserService {
    Map<String, Object> getUserList(Map<String, Object> params);
    Map<String, Object> createUser(User user);
    Map<String, Object> updateUser(Long id, User user);
    Map<String, Object> getById(Long id);
    Map<String, Object> deleteUser(Long id);
    Map<String, Object> resetPassword(Long id);
    Map<String, Object> disableUser(Long id);
    Map<String, Object> enableUser(Long id);
    Map<String, Object> batchOperate(Map<String, Object> params);
    Map<String, Object> login(String username, String password);
}
