import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes'
import store from '@/store/index'
import { Notify } from 'quasar'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

router.beforeEach(function (to, from, next) {
  // 인증이 필요한 페이지인 경우
  if (to.matched.some(record => record.meta.authRequired)) {
    // 토큰 유효성 검사를 한다.
    store.dispatch('checkTokenValidation')
    .then(res => {
      if (res) {
        // 유효성에 맞는 경우 정상적으로 다음 페이지 진입
        next();
      } else {
        // 유효성 검사에 실패한 경우 로그인 페이지 진입
        Notify.create({
          position: 'top',
          color: 'negative',
          message: '로그인이 필요합니다.'
        })
    
        next({
          name: 'AccountSignInPage'
        })
      }
    })
    .catch( err => {
      console.log(err.response)
    })
  // 인증되지 않은 유저 즉, 로그인하지 않은 유저만 들어갈 수 있는 페이지의 경우
  } else if (to.matched.some(record => record.meta.anonymRequired)) {
    store.dispatch('checkTokenValidation')
    .then(res => {
      if (res) {
        // 유효성에 맞는 경우 정상적으로 다음 페이지 진입
        Notify.create({
          position: 'top',
          color: 'negative',
          message: '현재 로그인한 상태 입니다.'
        })
    
        next({
          name: 'Healing-content'
        })
      } else {
        // 유효성 검사에 실패한 경우 로그인 페이지 진입
        next();
      }
    })
    .catch( err => {
      console.log(err.response)
    })
  // 인증되지 않은 유저 즉, 로그인하지 않은 유저만 들어갈 수 있는 페이지의 경우
  } else {
    // 그 외, 그인한 유저나 로그인하지 않은유저 모두 갈수 있는 페이지의 경우 정상 진입
    next();
  }
  // 그 외의 경우는 정상적으로 페이지 전환
});

export default router