<template>
  <div>
		<TheImageHeader :background-image="isNight ? 'night_16x9.jpg' : 'day_16x9.jpg'"/>

		<div class="p-y-28">
			<BaseTextInput 
				color="#545FD6" 
				label="닉네임 *"
				:rules="[required(), minLength(2), maxLength(10), korean()]"
				@onInputValue="text => nickname = text"
				@onValidate="reference => nicknameReference = reference"
			/>
			<BaseTextInput 
				color="#545FD6" 
				label="출생년도 *"
				:readonly="true"
				type="birthYear" 
				:rules="[required(), age()]"
				@onInputValue="text => birthYear = text"
				@onValidate="reference => birthYearReference = reference"
			/>
		</div>

		<div class="p-y-28 absolute" style="bottom: 20px; width: 100%">
			<BaseButton 
				back-ground-color="#545FD6" 
				label="다음" 
				:disable="!nickname || !birthYear"
				@click.native="onNextButton()"
			/>
		</div>

	</div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader'
import BaseButton from "@/components/common/BaseButton"
import BaseTextInput from "@/components/common/BaseTextInput"

import VueCookies from 'vue-cookies';
import { validation } from "@/mixins/validation"

export default {
	name: 'AccountSignUpFirstPage',
	components: {
		TheImageHeader,
		BaseButton,
		BaseTextInput,
	},
	// filters: {},
  mixins: [validation],
	props: {
		isNight: Boolean
	},
	data() {
		return {
			nickname: null,
			birthYear: null,
			nicknameReference: null,
			birthYearReference: null,
		}
	},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		onNextButton() {
			if (!this.nicknameReference.validate()) {
				this.$q.notify({
					position: 'top',
          color: 'negative',
          message: this.nicknameReference.computedErrorMessage,
        })
			} else if (!this.birthYearReference.validate()) {
				this.$q.notify({
					position: 'top',
          color: 'negative',
          message: this.birthYearReference.computedErrorMessage,
        })
			} 
			// TODO: 동기식으로 서버에 요청을 보내 닉네임이 중복인지 확인 해야함
			// else if() {}  
			else {
				// TODO: 입력 값 유효성 검증 후 브라우저 또는 vuex에 해당 정보를 저장 한 후 이동 해야함
				console.log(this.nickname, this.birthYear)
				// 현재 페이지의 데이터가 다음 회원가입 페이지에 넘어가야 함으로 쿠키에 이 정보를 저장한다. (페이스북 참고)
				// 왜 {HttpOnly: true} 이걸 넣어줘야 브라우저를 종료하면 쿠키가 날아갈까...이상하다...
				VueCookies.set("addtional-information", this.nickname + '-' + this.birthYear, {HttpOnly: true})

				this.$router.push({name: 'AccountSignUpSecondPage'})
			}
		},
	},
}
</script>

<style>

</style>