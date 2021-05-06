import { getInstence } from "./index";

// 사용자 로그인
function postArticle(param) {
  const instance = getInstence();
  return instance.post('/articles', param)
}

export { postArticle };