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

export {
  setCookie,
  getCookie,
  deleteCookie,
  saveUserNicknameToCookie,
  getUserNicknameFromCookie,
};
