import axios from 'axios';
import store from '@/store/index'
import { Cookies } from 'quasar'

// base url 설정
const BASE_URL = process.env.VUE_APP_SERVER_URL

// 토큰이 필요하건 필요하지 않건 일단 Authoriztion에 실어서 보낼것임 왜냐면 인증이 필요한 서비스는 back에서 토큰을 확인해볼거니까
function getInstence( validateRequired = true, isMultiPart = false ) {
  // 토큰 유효성을 검사.

  // 현재 토큰이 유효한지 검사하기위한 코드
  // 대부분의 요청에는 토큰 유효성 검사가 필요하지만 refresh-token과 같은 요청은 무한루프가 발생할수 있어 추가함. 
  // ex) 토큰 만료 -> 재요청 -> 토큰검사 -> 토큰 만료 -> 재요청 ...
  if (validateRequired) {
    store.dispatch('checkTokenValidation')
  }
  
  // 세션쿠키에서 accessToken을 꺼내온다.
  const token = Cookies.get('access_token')

  // axios 인스턴스를 생성한다.
  return axios.create({
    baseURL: BASE_URL,
    mimeType: `${isMultiPart} ? 'multipart/form-data' : ''`,
    contentType: `${isMultiPart} ? ${ false } : ${ true }`,
    processData: `${isMultiPart} ? ${ false } : ${ true }`,
    headers: {
      "Content-Type": 'application/json',
      "Authorization": `Bearer ${token}`,
    },
  });
}

export { getInstence };
