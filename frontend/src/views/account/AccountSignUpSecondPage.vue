<template>
  <div>
		<TheImageHeader :background-image="isNight ? 'night_16x9.jpg' : 'day_16x9.jpg'"/>

		<TheGoBackButton />

		<div class="p-x-28">
			<BaseTextInput 
				color="#545FD6" 
				label="아이디 *" 
				:rules="[required(), minLength(5), maxLength(16), alphabetNumber()]"
				@onInputValue="text => userId = text"
				@onValidate="reference => userIdReference = reference"
			/>
			<BaseTextInput 
				color="#545FD6" 
				label="비밀번호 *" 
				type="password" 
				:rules="[required(), minLength(8), maxLength(16)]"
				@onInputValue="text => password = text"
				@onValidate="reference => passwordReference = reference"
			/>
			<BaseTextInput 
				color="#545FD6" 
				label="비밀번호 확인 *"
				type="password" 
				:rules="[required(), minLength(8), maxLength(16), passwordConfirm(password)]"
				@onInputValue="text => confirmPassword = text"
				@onValidate="reference => confirmPasswordReference = reference"
			/>
		</div>

		<div class="p-x-28" style="padding-top: 24px; width: 100%">
			<BaseButton 
				back-ground-color="#545FD6" 
				label="회원가입" 
				:disable="!userId || !password || !confirmPassword"
				@click.native="onSignUpButton()" 
			/>
			<p style="text-align: center; padding:0; magin: 0; font-size: 12px">
				힐링메이트 계정을 생성하시면 힐링메이트 <span style="text-decoration: underline;">이용약관</span> 및 <br>
				<span style="text-decoration: underline;">개인정보보호정책</span>에 동의하는 것으로 간주합니다.
			</p>
		</div>

	</div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader'
import TheGoBackButton from "@/components/common/TheGoBackButton"
import BaseButton from "@/components/common/BaseButton"
import BaseTextInput from "@/components/common/BaseTextInput"
import { signUp } from "@/api/account"

// import VueCookies from 'vue-cookies';
import { validation } from "@/mixins/validation"

export default {
	name: 'AccountSignUpSecondPage',
	components: {
		TheImageHeader,
		TheGoBackButton,
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
			userId: null,
			password: null,
			confirmPassword: null,
			userIdReference: null,
			passwordReference: null,
			confirmPasswordReference: null,
		}
	},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		onSignUpButton() {
			if (!this.userIdReference.validate()) {
				this.$q.notify({
					position: 'top',
					color: 'negative',
					message: this.userIdReference.computedErrorMessage,
				})
			} else if (!this.passwordReference.validate()) {
				this.$q.notify({
					position: 'top',
					color: 'negative',
					message: this.passwordReference.computedErrorMessage,
				})
			} 
			else if (!this.confirmPasswordReference.validate()) {
				this.$q.notify({
					position: 'top',
					color: 'negative',
					message: this.confirmPasswordReference.computedErrorMessage,
				})
			} 
			else {
				// const addtionalInformationList = VueCookies.get('addtional-information').split('-')
				const addtionalInformationList = this.$q.cookies.get('addtional-information').split('-')

				// 회원가입을 하기 위한 정보
				const param = {
					'userId': this.userId,
					'password': this.password,
					'nickname': addtionalInformationList[0],
					'birthYear': addtionalInformationList[1],
				}
				
				signUp(param)
				.then(() => {
					// 회원가입 부가정보에 대한 쿠키 삭제
					this.$q.cookies.remove('addtional-information')
					// 토큰 획득을 위해 obtainToken action 실행 
					
					// 로그인을 하기위한 정보
					const param = {
						'userId' : this.userId,
						'password' : this.password
					}

					this.$store.dispatch('obtainToken', param)
				})
				.catch(err => {
					this.$q.notify({
						position: 'top',
						color: 'negative',
						message: err.response.data.message
					})
				})
			}
		},
	}
}
</script>

<style>

</style>