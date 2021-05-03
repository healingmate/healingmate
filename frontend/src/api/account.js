import { getInstence } from "./index";

// 사용자 가입
function signUp(param) {
  const instance = getInstence();
  return instance.post('/accounts', param)
}

// 사용자 닉네임 중복 체크
function nicknameCheck(nickname) {
  const instance = getInstence();
  return instance.get(`/accounts/${nickname}/exists`)
}

// 사용자 로그인
function signIn(param) {
  const instance = getInstence();
  return instance.post('/accounts/login', param)
}

// 사용자 삭제
function deleteAccount() {
  const instance = getInstence();
  return instance.delete('/accounts')
}

// 사용자 정보 수정
function modifyAccount(param) {
  const instance = getInstence();
  return instance.patch('/accounts', param)
}

// 리프레쉬 토큰 요청
function refreshToken(param) {
  const instance = getInstence(false);
  return instance.post('/accounts/refresh-token', param)
}

export { signUp, signIn, nicknameCheck, deleteAccount, modifyAccount, refreshToken };