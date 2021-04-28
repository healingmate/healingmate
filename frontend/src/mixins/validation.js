export const validation =  {
  methods: {
    required() {
      return val => !!val || '필수 입력 값 입니다.'
    },
    maxLength(length) {
      return val => val.length <= length || `글자 수를 ${length}자 이하로 입력해 주세요.`
    },
    minLength(length) {
      return val => val.length >= length || `글자 수를 ${length}자 이상로 입력해 주세요.`
    },
    alphabetNumber() {
      const alphabetNumberPattern = /^[A-Za-z0-9+]*$/; 
      return val => alphabetNumberPattern.test(val) || '아이디는 공백을 제외한 영문과 숫자만 입력 가능합니다.';
    },
    korean() {
      const koreanPattern = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$/;
      return val => koreanPattern.test(val) || '닉네임은 공백을 제외한 한글만 입력 가능합니다.';
    },
    passwordConfirm(password) {
      return val => val === password || '비밀번호가 일치하지 않습니다.'
    },
  }
}