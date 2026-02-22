import axios from 'axios';

// 创建 axios 实例
const api = axios.create({
  baseURL: '/api', // 后端 API 基础路径
  timeout: 10000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json'
  }
});

// 请求重试次数
const MAX_RETRIES = 3;

// 请求拦截器
api.interceptors.request.use(
  config => {
    // 添加认证信息，如 token
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    // 添加请求时间戳，防止缓存
    config.headers['X-Request-Time'] = Date.now().toString();
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// 响应拦截器
api.interceptors.response.use(
  response => {
    return response.data;
  },
  error => {
    console.error('API 错误:', error);
    
    // 处理 401 错误
    if (error.response && error.response.status === 401) {
      // 清除本地存储的 token
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      // 跳转到登录页面
      window.location.href = '/login';
      return Promise.reject(error);
    }
    
    // 处理网络错误和超时错误
    if (!error.response) {
      // 检查是否需要重试
      const config = error.config;
      if (config && !config._retryCount) {
        config._retryCount = 0;
      }
      
      if (config && config._retryCount < MAX_RETRIES) {
        // 增加重试次数
        config._retryCount++;
        // 计算重试延迟（指数退避）
        const delay = Math.pow(2, config._retryCount) * 1000;
        
        // 重试请求
        return new Promise(resolve => {
          setTimeout(() => {
            resolve(api(config));
          }, delay);
        });
      }
    }
    
    // 处理其他错误
    return Promise.reject(error);
  }
);

export default api;