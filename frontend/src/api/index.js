import axios from 'axios';

const config = {
  baseUrl: 'https://api.adviceslip.com/advice' // 기본적으로 중복되는 url 주소 부분
};

// 2. API 함수들을 정리
function getGoodWords() {
  return axios.get(`${config.baseUrl}`); // 나머지 주소
}

export {
	getGoodWords
}