import Vue from 'vue'
import Vuex from 'vuex'
import { getCookie } from '@/utils/cookies'
import mutations from '@/store/mutations'
import actions from '@/store/actions'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: getCookie('access_token') || null,
    refreshToken: getCookie('refresh_token') || null,
    userId: getCookie('user_id') || null,
    nickname: getCookie('nickname') || null,
    profileImage: getCookie('profileImage') || null,
    keywords: getCookie('keywords') || null,
  },
  mutations,
  actions,
  modules: {}
})