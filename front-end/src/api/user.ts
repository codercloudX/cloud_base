import api from './index';

export interface User {
  id: number;
  name: string;
  employeeId: string;
  email: string;
  phone: string;
  password: string;
  departmentId: number;
  status: string;
  createdAt: string;
  updatedAt: string;
  lastLoginAt: string;
}

export interface UserListParams {
  page?: number;
  size?: number;
  keyword?: string;
  departmentId?: number;
}

export interface ResponseResult<T = any> {
  success: boolean;
  message?: string;
  data?: T;
  records?: T[];
  total?: number;
  current?: number;
  size?: number;
  pages?: number;
  token?: string;
  user?: User;
  successCount?: number;
  failureCount?: number;
}

// 获取用户列表
export const getUserList = (params: UserListParams) => {
  return api.get<ResponseResult>('/users', { params });
};

// 获取用户详情
export const getUserById = (id: number) => {
  return api.get<ResponseResult>(`/users/${id}`);
};

// 创建用户
export const createUser = (user: User) => {
  return api.post<ResponseResult>('/users', user);
};

// 更新用户
export const updateUser = (id: number, user: User) => {
  return api.put<ResponseResult>(`/users/${id}`, user);
};

// 删除用户
export const deleteUser = (id: number) => {
  return api.delete<ResponseResult>(`/users/${id}`);
};

// 重置密码
export const resetPassword = (id: number) => {
  return api.post<ResponseResult>(`/users/${id}/reset-password`);
};

// 禁用用户
export const disableUser = (id: number) => {
  return api.post<ResponseResult>(`/users/${id}/disable`);
};

// 启用用户
export const enableUser = (id: number) => {
  return api.post<ResponseResult>(`/users/${id}/enable`);
};

// 批量操作
export const batchOperate = (params: { ids: number[]; operation: string }) => {
  return api.post<ResponseResult>('/users/batch', params);
};

// 登录
export const login = (username: string, password: string) => {
  return api.post<ResponseResult>('/users/login', { username, password });
};