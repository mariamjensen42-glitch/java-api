import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/games',
    name: 'GameList',
    component: () => import('../views/GameList.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/game/:id',
    name: 'GameDetail',
    component: () => import('../views/GameDetail.vue'),
    meta: { requiresAuth: true },
    children: [
      {
        path: '',
        redirect: 'story'
      },
      {
        path: 'story',
        name: 'Story',
        component: () => import('../views/Story.vue')
      },
      {
        path: 'character',
        name: 'Character',
        component: () => import('../views/Character.vue')
      },
      {
        path: 'combat',
        name: 'Combat',
        component: () => import('../views/Combat.vue')
      },
      {
        path: 'quests',
        name: 'Quests',
        component: () => import('../views/Quests.vue')
      },
      {
        path: 'items',
        name: 'Items',
        component: () => import('../views/Items.vue')
      },
      {
        path: 'saves',
        name: 'Saves',
        component: () => import('../views/Saves.vue')
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
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  const isAuthenticated = localStorage.getItem('token') !== null

  if (requiresAuth && !isAuthenticated) {
    next('/login')
  } else {
    next()
  }
})

export default router