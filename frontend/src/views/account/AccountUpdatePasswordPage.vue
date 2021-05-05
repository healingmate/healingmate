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
      @onInputValue="password"
      type="password" 
      :rules="[required(), minLength(8), maxLength(16)]"
    >
    </base-text-input>
    <!-- 새로운 비밀번호 입력 -->
    <base-text-input 
      class="p-y-28 q-mt-xs" 
      color="#244684" 
      label="새로운 비밀번호" 
      @onInputValue="newPassword"
      :rules="[required(), minLength(8), maxLength(16)]"
    >
    </base-text-input>
    <!-- 새로운 비밀번호 입력 확인 -->
    <base-text-input 
      class="p-y-28 q-mt-xs" 
      color="#244684" 
      label="비밀번호 확인" 
      @onInputValue="checkPassword"
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
      checkPassword: '',
    }
  },
  methods: {
    changePassword() {
      if (!this.user.username) {
        this.$q.notify({
          position: 'top',
          color: 'negative',
          message: '닉네임을 입력해주세요.'
        })
        return
      } 
      if (this.selectedKeyword.length < 1) {
        this.$q.notify({
          position: 'top',
          color: 'negative',
          message: '키워드를 한 개 이상 선택해주세요'
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