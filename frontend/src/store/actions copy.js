import router from '@/routes'
import jwt_decode from 'jwt-decode'
import { getCookie } from '@/utils/cookies'
import { signIn, refreshToken, modifyAccount } from "@/api/account"
import { Notify } from 'quasar'

export default {
  async obtainToken({ commit }, userCredential){
    await signIn(userCredential)
    .then(res => {
      // 서버에 해당 아이디와 비밀번호로 인증이 성공한 경우
      // 클라이언트 사이드에도 updateToken 을 통해 토큰을 저장한다.
      const param = {
        'accessToken' : res.data.accessToken,
        'refreshToken' : res.data.refreshToken
      }

      commit('updateToken', param)
      commit('updateUser', res.data.user)
      
      // 클라이언트에 토큰저장이 완료되면 아티클 메인 피드로 이동한다.
      // TODO: 아티클 페이지가 생기면 그쪽으로 이동해야함
      router.push({name: 'ArticleFeedPage'})
    })
    .catch(() => {
      // 서버에 해당 아이디와 비밀번호로 인증이 실패한 경우
      // 사용자에게 알려준다.
      Notify.create({
        position: 'top',
        color: 'negative',
        message: '아이디 또는 비밀번호를 확인해주세요.'
      })
    })
  },
  async updateUser({ commit }, userInformation){
    await modifyAccount(userInformation)
    .then(() => {
      commit('updateUser', userInformation)
      router.push({ name: 'Profile' })
    })
    .catch(err => {
      console.log(err.response)
    })
  },
  async refreshToken({ commit }){
    const param = {
      'refreshToken': getCookie('refresh_token')
    }

    const isRefreshed = await refreshToken(param)
    .then(res => {
      commit('refreshToken', res.data.accessToken)
      // console.log("리프레시 토큰 정상 발급완료", res.data.accessToken)
      return true
    })
    .catch(() => {
      commit('removeToken')
      return false
    })

    return isRefreshed
  },
  removeToken({ commit }){
    commit('removeToken')
    router.push({name: 'Home'})
  },
  async checkTokenValidation(){
    // jwt_decode 에서 이상한 토큰을 받으면 exception을 내버려서 try catch사용
    try {
      const token = getCookie('access_token')

      if(token){
        const decoded = jwt_decode(token);
        const exp = decoded.exp
        
        if( exp -(Date.now()/1000) > 0){
          // accessToken이 아직 만료되지 않은경우
          // console.log("accessToken이 아직 만료되지 않은경우")
          return true
        } else {
          // 토큰 만료되었을 때
          // console.log("토큰 만료되었을 때")
          return await this.dispatch('refreshToken')
        }
      }
      // console.log("토큰을 발급받지 않은 상태")
      // 토큰을 발급받지 않은 상태
      return false
    } catch(err) {
      // "정상적이지 않은 토큰/ 이상한 모양의 조작된 토큰"
      // console.log("정상적이지 않은 토큰/ 이상한 모양의 조작된 토큰")
      return await this.dispatch('refreshToken')
    }
  },
}