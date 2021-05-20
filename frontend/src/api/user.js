import { getInstence } from "./index";

// 북마크 삭제
function deleteHealingContentBookmark(contentId) {
  const instance = getInstence();
  return instance.delete(`/users/healing-content/${contentId}`)
}

// 회원이 북마크한 힐링 콘텐츠 조회
function getHealingContents() {
  const instance = getInstence();
  return instance.get('/users/healing-contents')
}

// 회원이 작성한 게시글 목록 조회
function getArticleList(cursorId, size) {
  const instance = getInstence();
  return instance.get(`/users/articles?cursorId=${cursorId}&size=${size}`)
}

// 회원 평가
function evaluateUser(param) {
  const instance = getInstence();
  return instance.patch('/users/evaluate', param)
}

// 힐링 콘텐츠 북마킹
function bookmarkHealingContent(contentId) {
  const instance = getInstence();
  return instance.post(`/users/healing-content/${contentId}`)
}

// 회원 차단
function banUser(userId) {
  const instance = getInstence();
  return instance.post(`/users/ban/${userId}`)
}


export { getArticleList, deleteHealingContentBookmark, getHealingContents, evaluateUser, bookmarkHealingContent, banUser};