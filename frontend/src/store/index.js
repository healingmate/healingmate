import Vue from 'vue'
import Vuex from 'vuex'
import jwt_decode from 'jwt-decode'
import { getCookie, setCookie, deleteCookie } from '@/utils/cookies'
import { signIn, refreshToken } from "@/api/account"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: getCookie('access_token'),
    refreshToken: getCookie('refresh_token'),
  },
  mutations: {
    updateToken(state, newAccessToken, newRefreshToken){
      setCookie('access_token', newAccessToken),
      setCookie('refresh_token', newRefreshToken),
      state.accessToken = newAccessToken;
      state.refreshToken = newRefreshToken;
    },
    removeToken(state){
      deleteCookie('access_token'),
      deleteCookie('refresh_token'),
      state.accessToken = null;
      state.refreshToken = null;
    }
  },
  actions: {
    obtainToken(userId, password){
      const param = {
        userId,
        password
      }

      signIn(param)
      .then(res => {
        this.commit('updateToken', res.data.accessToken, res.data.refreshToken)
        // TODO: 힐링방식 공유 피드 화면으로 이동하는 로직 추가
      })
      .catch(err => {
        console.log(err)
      })
    },
    refreshToken(){
      const param = {
        'refreshToken': getCookie('refresh_token')
      }

      this.commit('removeToken')

      refreshToken(param)
      .then(res => {
        this.commit('updateToken', res.data.accessToken, res.data.refreshToken)
      })
      .catch(err => {
        console.log(err)
      })
    },
    checkTokenValidation(){
      // const token = this.state.accessToken;
      const token = getCookie('access_token')

      if(token){
        const decoded = jwt_decode(token);
        const exp = decoded.exp

        if(exp -(Date.now()/1000) > 0){
          // 발급 토큰 체인의 첫번째 (첫 발급 토큰)가 7주일을 넘기지 않았고
          // 만료 시간이 30분 전이라면 리프레시 토큰 함수 실행
          this.dispatch('refreshToken')
          return true
        } else {
          // 토큰을 발급 받은 상태이나 만료되었을 때
          this.commit('removeToken')
          return false
        }
      }
      // 토큰을 발급받지 않은 상태
      return false
    }
  },
  modules: {
  }
})
