package com.cloudbase.controller;

import com.cloudbase.entity.User;
import com.cloudbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Map<String, Object> getUserList(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long departmentId) {
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("page", page);
        params.put("size", size);
        params.put("keyword", keyword);
        params.put("departmentId", departmentId);
        return userService.getUserList(params);
    }

    @PostMapping
    public Map<String, Object> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/{id}/reset-password")
    public Map<String, Object> resetPassword(@PathVariable Long id) {
        return userService.resetPassword(id);
    }

    @PostMapping("/{id}/disable")
    public Map<String, Object> disableUser(@PathVariable Long id) {
        return userService.disableUser(id);
    }

    @PostMapping("/{id}/enable")
    public Map<String, Object> enableUser(@PathVariable Long id) {
        return userService.enableUser(id);
    }

    @PostMapping("/batch")
    public Map<String, Object> batchOperate(@RequestBody Map<String, Object> params) {
        return userService.batchOperate(params);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginParams) {
        String username = loginParams.get("username");
        String password = loginParams.get("password");
        return userService.login(username, password);
    }

}
