import { getInstence } from "./index";

// 회원이 작성한 게시글 목록 조회
function getArticleList(cursorId, size) {
  const instance = getInstence();
  return instance.get(`/users/articles?cursorId=${cursorId}&size=${size}`)
}

export { getArticleList };