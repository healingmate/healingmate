import axios from 'axios';
import store from '@/store/index'
import { getCookie } from '@/utils/cookies'

// base url 설정
const BASE_URL = process.env.VUE_APP_DEVELOPMENT_SERVER_URL

// 토큰이 필요하건 필요하지 않건 일단 Authoriztion에 실어서 보낼것임 왜냐면 인증이 필요한 서비스는 back에서 토큰을 확인해볼거니까
function getInstence() {
  // 토큰 유효성을 검사.
  store.dispatch('checkTokenValidation')
  
  // 세션쿠키에서 accessToken을 꺼내온다.
  const token = getCookie('access_token')

  // axios 인스턴스를 생성한다.
  return axios.create({
    baseURL: BASE_URL,
    headers: {
      "Content-type": "application/json",
      "Authorization": `Bearer ${token}`,
    },
  });
}

export { getInstence };
