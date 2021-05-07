import Vue from 'vue'
import Vuex from 'vuex'
import { getCookie, getUserNicknameFromCookie, } from '@/utils/cookies'
import mutations from '@/store/mutations'
import actions from '@/store/actions'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: getCookie('access_token') || null,
    refreshToken: getCookie('refresh_token') || null,
    nickname: getUserNicknameFromCookie() || '',
  },
  mutations,
  actions,
  modules: {}
})
