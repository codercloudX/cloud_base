import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: () => import('../views/login/LoginPage.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/system',
    component: () => import('../views/system/SystemManagement.vue'),
    meta: { requiresAuth: true },
    children: [
      {
        path: '',
        component: () => import('../views/system/Dashboard.vue'),
        meta: { title: '数据概览', requiresAuth: true }
      },
      {
        path: 'user',
        component: () => import('../views/user/UserManagement.vue'),
        meta: { title: '用户管理', requiresAuth: true }
      },
      {
        path: 'department',
        component: () => import('../views/department/DepartmentManagement.vue'),
        meta: { title: '部门管理', requiresAuth: true }
      },
      {
        path: 'role',
        component: () => import('../views/role/RoleManagement.vue'),
        meta: { title: '角色管理', requiresAuth: true }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  if (to.meta.title) {
    document.title = `${to.meta.title} - 组织架构与权限体系维护系统`
  }

  // 检查是否需要认证
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  const hasToken = localStorage.getItem('token')

  if (requiresAuth && !hasToken) {
    // 需要认证但没有token，跳转到登录页面
    next('/login')
  } else if (to.path === '/login' && hasToken) {
    // 已经有token，跳转到系统首页
    next('/system')
  } else {
    // 其他情况正常跳转
    next()
  }
})

export default router