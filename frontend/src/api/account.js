import { getInstence } from "./index";

const instance = getInstence();

// 사용자 가입
function signUp(param) {
  return instance.post('/accounts', param)
}

// 사용자 닉네임 중복 체크
function nicknameCheck(nickname) {
  return instance.get(`/accounts/${nickname}/exists`)
}

// 사용자 로그인
function signIn(param) {
  return instance.post('/accounts/signin', param)
}

// 사용자 삭제
function deleteAccount() {
  return instance.delete('/accounts')
}

// 사용자 정보 수정
function modifyAccount(param) {
  return instance.patch('/accounts', param)
}

// 리프레쉬 토큰 요청
function refreshToken(param) {
  return instance.post('/accounts/refresh-token', param)
}

export { signUp, signIn, nicknameCheck, deleteAccount, modifyAccount, refreshToken };