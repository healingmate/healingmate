import { getInstence } from "./index";

function postArticle(param) {
 const instance = getInstence({ isMultiPart: true });
 return instance.post('/articles', param)
}

function postEmoji(articleId) {
 const instance = getInstence();
 return instance.post(`/articles/${articleId}/emoji`)
}

function modifyArticle(articleId) {
 const instance = getInstence();
 return instance.put(`/articles/${articleId}`)
}

function modifyEmoji(articleId) {
 const instance = getInstence();
 return instance.put(`/articles/${articleId}/emoji`)
}

function deleteArticle(articleId) {
 const instance = getInstence();
 return instance.delete(`/articles/${articleId}`)

}

function deleteEmoji(articleId) {
 const instance = getInstence();
 return instance.delete(`/articles/${articleId}/emoji`)
}


export { postArticle, postEmoji, modifyArticle, modifyEmoji ,deleteArticle, deleteEmoji };