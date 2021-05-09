function setCookie(name, value, day) {
  var date = new Date();
  date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
  document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

function getCookie(name) {
  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value? value[2] : null;
}

 function deleteCookie(name) {
  var date = new Date();
  document.cookie = name + "= " + "; expires=" + date.toUTCString() + "; path=/";
}

function saveUserNicknameToCookie(value) {
  document.cookie = `nickname=${value}`;
}

function getUserNicknameFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)nickname\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function saveUserKeywordsToCookie(value) {
  // 배열을 쉼표로 구분
  document.cookie = `keywords=${value.join(',')}`;
}

function getUserKeywordsFromCookie() {
  //쉼표로 구분한 데이터를 배열로 분리
  const res = document.cookie.replace(
    /(?:(?:^|.*;\s*)keywords\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
  return res ? res.split(',') : [];
}

export {
  setCookie,
  getCookie,
  deleteCookie,
  saveUserNicknameToCookie,
  getUserNicknameFromCookie,
  saveUserKeywordsToCookie,
  getUserKeywordsFromCookie,
};
