<template>
  <div>
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
      class="p-x-28 q-mt-xs" 
      color="#244684" 
      label="기존 비밀번호" 
      @onInputValue="text => password = text"
      @onValidate="reference => passwordReference = reference"
      type="password" 
      :rules="[required(), minLength(8), maxLength(16)]"
    >
    </base-text-input>
    <!-- 새로운 비밀번호 입력 -->
    <base-text-input 
      class="p-x-28 q-mt-xs" 
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
      class="p-x-28 q-mt-xs" 
      color="#244684" 
      label="비밀번호 확인" 
      type="password"
      @onInputValue="text => confirmPassword = text"
      @onValidate="reference => confirmPasswordReference = reference"
      :rules="[required(), minLength(8), maxLength(16)]"
    >
    </base-text-input>
    <!-- 제출 버튼 -->
    <div class="absolute-bottom p-x-28 q-mb-lg"  @click="changePassword">
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
import { validation } from "@/mixins/validation";
import { updatePassword } from '@/api/account';
import { Notify } from 'quasar'

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
  methods: {
    changePassword() {
      if (!this.passwordReference.validate()) {
				this.$q.notify({
					position: 'top',
					color: 'negative',
					message: this.passwordReference.computedErrorMessage,
				})
        return
			} 
      else if (!this.confirmPasswordReference.validate()) {
				this.$q.notify({
					position: 'top',
					color: 'negative',
					message: this.confirmPasswordReference.computedErrorMessage,
				})
        return
			} else if ( this.password === this.newPassword ) {
        this.$q.notify({
					position: 'top',
					color: 'negative',
					message: '기존 비밀번호와 다른 비밀번호를 입력해주세요'
				})
        return
      } else {
        const param = {
          'password': this.password,
          'newPassword': this.newPassword,
        }
        updatePassword(param)
        .then(() => {
            Notify.create({
              position: 'top',
              color: 'primary',
              message: '비밀번호가 수정되었습니다.'
            })
            this.$router.go(-1);
        })
        .catch(err => {
          console.log(err.response)
          this.$q.notify({
            position: 'top',
            color: 'negative',
            message: '현재 비밀번호가 일치하지 않습니다.',
          })
          return
        })
      }  
    }
  }
}
</script>

<style>

</style>