import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes'
// TODO: 풀어야함
// import store from '@/store/index'
// import { Notify } from 'quasar'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

// TODO: 풀어야함
// router.beforeEach(function (to, from, next) {
//   // 인증이 필요한 페이지인데 사용자의 토큰이 없거나 이 토큰이 유효하지 않을때는 로그인페이지로 보낸다
//   if (to.matched.some(record => record.meta.authRequired) && store.dispatch('checkTokenValidation')) {

//     Notify.create({
//       position: 'top',
//       color: 'negative',
//       message: '로그인이 필요합니다.'
//     })

//     next({
//       name: 'AccountSignInPage'
//     })

//   }
//   // 그 외의 경우는 정상적으로 페이지 전환
//   next();
// });

export default router