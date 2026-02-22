import api from './index';

export interface Role {
  id: number;
  name: string;
  description: string;
  status: string;
  createdAt: string;
  updatedAt: string;
}

export interface RoleListParams {
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

// 获取角色列表
export const getRoleList = (params: RoleListParams) => {
  return api.get<ResponseResult>('/roles', { params });
};

// 获取角色详情
export const getRoleById = (id: number) => {
  return api.get<ResponseResult>(`/roles/${id}`);
};

// 创建角色
export const createRole = (role: Role) => {
  return api.post<ResponseResult>('/roles', role);
};

// 更新角色
export const updateRole = (id: number, role: Role) => {
  return api.put<ResponseResult>(`/roles/${id}`, role);
};

// 删除角色
export const deleteRole = (id: number) => {
  return api.delete<ResponseResult>(`/roles/${id}`);
};

// 获取角色权限
export const getRolePermissions = (roleId: number) => {
  return api.get<ResponseResult>(`/roles/${roleId}/permissions`);
};

// 分配角色权限
export const assignRolePermissions = (roleId: number, permissionIds: number[]) => {
  return api.post<ResponseResult>(`/roles/${roleId}/permissions`, { permissionIds });
};

// 批量操作角色
export const batchOperateRoles = (params: { operation: string; ids: number[] }) => {
  return api.post<ResponseResult>('/roles/batch', params);
};