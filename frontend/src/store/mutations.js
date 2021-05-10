import { setCookie, deleteCookie } from '@/utils/cookies'

export default {
  updateToken(state, tokenInformation){
    setCookie('access_token', tokenInformation.accessToken),
    setCookie('refresh_token', tokenInformation.refreshToken),
    state.accessToken = tokenInformation.accessToken;
    state.refreshToken = tokenInformation.refreshToken;
  },
  updateUser(state, userInformation){
    setCookie('user', JSON.stringify(userInformation)),
    state.user = userInformation.user;
  },
  removeToken(state){
    deleteCookie('access_token'),
    deleteCookie('refresh_token'),
    deleteCookie('user'),
    state.accessToken = null;
    state.refreshToken = null;
    state.user = null;
  },
  refreshToken(state, accessToken){
    setCookie('access_token', accessToken),
    state.accessToken = accessToken;
  }
}