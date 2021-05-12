import { Cookies } from 'quasar'

export default {
  updateToken(state, tokenInformation){
    Cookies.set('access_token', tokenInformation.accessToken)
    Cookies.set('refresh_token', tokenInformation.refreshToken)
    state.accessToken = tokenInformation.accessToken
    state.refreshToken = tokenInformation.refreshToken
  },
  updateUser(state, userInformation){
    if (userInformation.userId) {
      Cookies.set('user_id', userInformation.userId)
      state.userId = userInformation.userId;
    }
    Cookies.set('nickname', userInformation.nickname)
    Cookies.set('profile_image', userInformation.profileImage)
    Cookies.set('keyword', userInformation.keyword.join())
    state.nickname = userInformation.nickname;
    state.profileImage = userInformation.profileImage;
    state.keyword = userInformation.keyword;
  },
  removeToken(state){
    Cookies.remove('access_token'),
    Cookies.remove('refresh_token'),
    Cookies.remove('user_id'),
    Cookies.remove('nickname'),
    Cookies.remove('profile_image'),
    Cookies.remove('keyword'),
    state.accessToken = null;
    state.refreshToken = null;
    state.userId = null;
    state.nickname = null;
    state.profileImage = null;
    state.keyword = null;
  },
  refreshToken(state, accessToken){
    Cookies.set('access_token', accessToken)
    state.accessToken = accessToken;
  }
}