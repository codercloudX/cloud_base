import api from './index';

export interface Permission {
  id: number;
  name: string;
  code: string;
  description: string;
  type: string;
  path: string;
  parentId: number | null;
  status: string;
  createdAt: string;
  updatedAt: string;
}

export interface PermissionListParams {
  page?: number;
  size?: number;
  keyword?: string;
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
  successCount?: number;
  failureCount?: number;
}

// 获取权限列表
export const getPermissionList = (params: PermissionListParams) => {
  return api.get<ResponseResult>('/permissions', { params });
};

// 获取权限详情
export const getPermissionById = (id: number) => {
  return api.get<ResponseResult>(`/permissions/${id}`);
};

// 创建权限
export const createPermission = (permission: Permission) => {
  return api.post<ResponseResult>('/permissions', permission);
};

// 更新权限
export const updatePermission = (id: number, permission: Permission) => {
  return api.put<ResponseResult>(`/permissions/${id}`, permission);
};

// 删除权限
export const deletePermission = (id: number) => {
  return api.delete<ResponseResult>(`/permissions/${id}`);
};

// 获取权限树
export const getPermissionTree = () => {
  return api.get<ResponseResult>('/permissions/tree');
};

// 批量操作权限
export const batchOperatePermissions = (params: { operation: string; ids: number[] }) => {
  return api.post<ResponseResult>('/permissions/batch', params);
};