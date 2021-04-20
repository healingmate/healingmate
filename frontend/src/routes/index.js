import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

// router.beforeEach((to, from, next) => {

// 페이지 이동 전 수행해야할 동작을 여기다 적으면 됩니다. 예를들어 

// }); 

export default router