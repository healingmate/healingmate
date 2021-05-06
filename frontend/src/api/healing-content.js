import { getInstence } from "./index";

// 힐링 콘텐츠 북마크 하기
function bookmarkContent(contentId) {
  const instance = getInstence();
  return instance.post(`/healing-content/content/bookmark/${contentId}`)
}

// 힐링 콘텐츠 북마크 제거하기
function deleteBookmarkContent(contentId) {
  const instance = getInstence();
  return instance.delete(`/healing-content/content/bookmark/${contentId}`)
}

// 사용자가 북마크한 힐링 콘텐츠 조회하기
function getBookmarkedContents() {
  const instance = getInstence();
  return instance.get('/healing-content/content/bookmarks')
}


export { bookmarkContent, deleteBookmarkContent, getBookmarkedContents };