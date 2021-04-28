import axios from 'axios';

const config = {
  baseUrl: 'https://api.adviceslip.com/advice', // 기본적으로 중복되는 url 주소 부분
};

// 2. API 함수들을 정리
function getGoodWords() {
  return axios.get(`${config.baseUrl}`); // 나머지 주소
}

export { getGoodWords };

// import axios from 'axios';
// // import { API_BASE_URL } from '../config';

// function createInstance() {
//   const instance = axios.create({
//     baseURL: 'https://i4b107.p.ssafy.io:8080',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//   });
//   return instance;
// }
// function createMultipartInstance() {
//   const instance = axios.create({
//     baseURL: 'https://i4b107.p.ssafy.io:8080',
//     headers: {
//       // 'Content-Type': false,
//       // 'Content-Type': 'multipart/form-data',
//     },
//   });
//   return instance;
// }

// export { createInstance, createMultipartInstance };
