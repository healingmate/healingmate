import { setCookie, deleteCookie } from '@/utils/cookies'

export default {
  updateToken(state, tokenInformation){
    setCookie('access_token', tokenInformation.accessToken),
    setCookie('refresh_token', tokenInformation.refreshToken),
    state.accessToken = tokenInformation.accessToken;
    state.refreshToken = tokenInformation.refreshToken;
  },
  removeToken(state){
    deleteCookie('access_token'),
    deleteCookie('refresh_token'),
    state.accessToken = null;
    state.refreshToken = null;
  },
  refreshToken(state, accessToken){
    setCookie('access_token', accessToken),
    state.accessToken = accessToken;
  }
}