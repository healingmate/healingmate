import { getInstence } from "./index";

function postArticle(param) {
 const instance = getInstence({ isMultiPart: true });
 return instance.post('/articles', param)
}

function postEmoji(articleId, param) {
 const instance = getInstence();
 return instance.post(`/articles/${articleId}/emoji`, param)
}

function getArticles(size, cursorId) {
 const instance = getInstence();
 return instance.get(`/articles?cursorId=${cursorId}&size=${size}`)
}

function modifyArticle(articleId) {
 const instance = getInstence();
 return instance.put(`/articles/${articleId}`)
}

function modifyEmoji(articleId, param) {
 const instance = getInstence();
 return instance.put(`/articles/${articleId}/emoji`, param)
}

function deleteArticle(articleId) {
 const instance = getInstence();
 return instance.delete(`/articles/${articleId}`)

}

function deleteEmoji(articleId) {
 const instance = getInstence();
 return instance.delete(`/articles/${articleId}/emoji`)
}


export { postArticle, postEmoji, getArticles, modifyArticle, modifyEmoji ,deleteArticle, deleteEmoji };