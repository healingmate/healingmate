<template>
  <div>
    <!-- TODO : 상단 헤더 길이 조정 -->
    <the-image-header 
      class="relative" 
      :background-image="isNight ? 'night.jpg' : 'day.jpg'"
      label="Change Password"
      :brightness="60"
    ></the-image-header>
    <!-- 뒤로 가기 버튼 -->
    <the-go-back-button
      :size="1.2"
    ></the-go-back-button>
    <!-- 기존 비밀번호 입력 -->
    <base-text-input 
      class="p-y-28 q-mt-xs" 
      color="#244684" 
      label="기존 비밀번호" 
      @onInputValue="text => password = text"
      type="password" 
      :rules="[required(), minLength(8), maxLength(16)]"
    >
    </base-text-input>
    <!-- 새로운 비밀번호 입력 -->
    <base-text-input 
      class="p-y-28 q-mt-xs" 
      color="#244684" 
      label="새로운 비밀번호" 
      type="password"
      @onInputValue="text => newPassword = text"
      @onValidate="reference => passwordReference = reference"
      :rules="[required(), minLength(8), maxLength(16)]"
    >
    </base-text-input>
    <!-- 새로운 비밀번호 입력 확인 -->
    <base-text-input 
      class="p-y-28 q-mt-xs" 
      color="#244684" 
      label="비밀번호 확인" 
      type="password"
      @onInputValue="text => confirmPassword = text"
      @onValidate="reference => confirmPasswordReference = reference"
      :rules="[required(), minLength(8), maxLength(16)]"
    >
    </base-text-input>
    <!-- 제출 버튼 -->
    <div class="absolute-bottom p-y-28 q-mb-lg"  @click="changePassword">
      <base-button 
        back-ground-color="#244684" 
        text-color="white" 
        label="변경완료"
      ></base-button>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import TheGoBackButton from '@/components/common/TheGoBackButton';
import BaseTextInput from "@/components/common/BaseTextInput"
import BaseButton from '@/components/common/BaseButton';
import { validation } from "@/mixins/validation"

export default {
  components: {
    TheImageHeader,
    TheGoBackButton,
    BaseButton,
    BaseTextInput,
  },
  mixins: [validation],
  props: {
    isNight:{
      isNight: Boolean
    },
  },
  data() {
    return {
      password: '',
      newPassword: '',
      confirmPassword: '',
      passwordReference: null,
			confirmPasswordReference: null,
    }
  },
  computed: {
    checkForm() {
      return this.newPassword === this.confirmPassword
    }
  },
  methods: {
    changePassword() {
      if (!this.password) {
        this.$q.notify({
          position: 'top',
          color: 'negative',
          message: '기존 비밀번호를 입력해주세요.'
        })
        return
      } 
      if (!this.newPassword) {
        this.$q.notify({
          position: 'top',
          color: 'negative',
          message: '새로운 비밀번호를 입력해주세요'
        })
        return
      } 
      if (!this.confirmPassword) {
        this.$q.notify({
          position: 'top',
          color: 'negative',
          message: '새로운 비밀번호를 다시 입력해주세요'
        })
        return
      } 
      this.$router.push('/profile');
    }
  }
}
</script>

<style>

</style>