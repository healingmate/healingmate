import { getCookie } from '@/utils/cookies'

export default {
  accessToken: getCookie('access_token') || null,
  refreshToken: getCookie('refresh_token') || null,
  userId: getCookie('user_id') || null,
  nickname: decodeURI(getCookie('nickname')) || null,
  profileImage: getCookie('profile_image') || null,
  keyword: decodeURI(getCookie('keyword')) || null,
};