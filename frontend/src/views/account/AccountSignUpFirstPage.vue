<template>
  <div>
		<TheImageHeader :background-image="isNight ? 'night_16x9.jpg' : 'day_16x9.jpg'"/>

		<TheGoBackButton />

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
import TheGoBackButton from "@/components/common/TheGoBackButton"
import BaseButton from "@/components/common/BaseButton"
import BaseTextInput from "@/components/common/BaseTextInput"
import { nicknameCheck } from "@/api/account"

// import VueCookies from 'vue-cookies';
import { validation } from "@/mixins/validation"

export default {
	name: 'AccountSignUpFirstPage',
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
			else {
				// 현재 페이지의 데이터가 다음 회원가입 페이지에 넘어가야 함으로 쿠키에 이 정보를 저장한다. (페이스북 참고)
				nicknameCheck(this.nickname)
				.then((res) => {
					if (res.data) {
						this.$q.notify({
							position: 'top',
							color: 'negative',
							message: '이미 사용중인 닉네임입니다.'
						})
					} else {
						this.$q.cookies.set('addtional-information', this.nickname + '-' + this.birthYear)
						this.$router.push({name: 'AccountSignUpSecondPage'})
					}
				})
				.catch(err => {
					console.log(err.response)
				})
			}
		},
	},
}
</script>

<style>

</style>