import { setCookie, deleteCookie } from '@/utils/cookies'

export default {
  updateToken(state, tokenInformation){
    setCookie('access_token', tokenInformation.accessToken),
    setCookie('refresh_token', tokenInformation.refreshToken),
    state.accessToken = tokenInformation.accessToken;
    state.refreshToken = tokenInformation.refreshToken;
  },
  updateUser(state, userInformation){
    if (userInformation.userId) {
      setCookie('user_id', (userInformation.userId)),
      state.userId = userInformation.userId;
    }
    setCookie('nickname', (userInformation.nickname)),
    setCookie('profile_image', (userInformation.profileImage)),
    setCookie('keywords', (userInformation.keyword)),
    state.nickname = userInformation.nickname;
    state.profileImage = userInformation.profileImage;
    state.keywords = userInformation.keyword;
  },
  removeToken(state){
    deleteCookie('access_token'),
    deleteCookie('refresh_token'),
    deleteCookie('user_id'),
    deleteCookie('nickname'),
    deleteCookie('profile_image'),
    deleteCookie('keywords'),
    state.accessToken = null;
    state.refreshToken = null;
    state.userId = null;
    state.nickname = null;
    state.profileImage = null;
    state.keywords = null;
  },
  refreshToken(state, accessToken){
    setCookie('access_token', accessToken),
    state.accessToken = accessToken;
  }
}