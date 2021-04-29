<template>
  <div>
		<TheImageHeader background-image="night_16x9.jpg"/>

		<div class="p-y-28">
			<BaseTextInput color="#545FD6" label="닉네임 *" :rules="[required(), minLength(2), maxLength(10), korean()]"/>
			<BaseTextInput color="#545FD6" label="출생년도 *" type="birthYear"/>
		</div>

		<div class="p-y-28 absolute" style="bottom: 20px; width: 100%">
			<BaseButton back-ground-color="#545FD6" label="다음" @click.native="onNextButton()"/>
		</div>

	</div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader'
import BaseButton from "@/components/common/BaseButton"
import BaseTextInput from "@/components/common/BaseTextInput"

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
	// props: {},
	// data() {
	//   return {}
	// },
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		onNextButton() {
			// TODO: 입력 값 유효성 검증 후 브라우저 또는 vuex에 해당 정보를 저장 한 후 이동 해야함
			this.$router.push({name: 'AccountSignUpSecondPage'})
		},
	},
}
</script>

<style>

</style>