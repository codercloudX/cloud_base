import api from './index';

export interface Department {
  id: number;
  name: string;
  description: string;
  parentId: number | null;
  status: string;
  createdAt: string;
  updatedAt: string;
  level: number;
}

export interface DepartmentListParams {
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

// 获取部门列表
export const getDepartmentList = (params: DepartmentListParams) => {
  return api.get<ResponseResult>('/departments', { params });
};

// 获取部门详情
export const getDepartmentById = (id: number) => {
  return api.get<ResponseResult>(`/departments/${id}`);
};

// 创建部门
export const createDepartment = (department: Department) => {
  return api.post<ResponseResult>('/departments', department);
};

// 更新部门
export const updateDepartment = (id: number, department: Department) => {
  return api.put<ResponseResult>(`/departments/${id}`, department);
};

// 删除部门
export const deleteDepartment = (id: number) => {
  return api.delete<ResponseResult>(`/departments/${id}`);
};

// 获取部门树
export const getDepartmentTree = () => {
  return api.get<ResponseResult>('/departments/tree');
};

// 批量操作
export const batchOperateDepartments = (params: { operation: string; ids: number[] }) => {
  return api.post<ResponseResult>('/departments/batch', params);
};
