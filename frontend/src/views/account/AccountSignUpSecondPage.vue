<template>
  <div>
		<TheImageHeader background-image="night_16x9.jpg"/>

		<div class="p-y-28">
			<BaseTextInput color="#545FD6" label="아이디*" :rules="[required(), minLength(5), maxLength(16), alphabetNumber()]"/>
			<BaseTextInput color="#545FD6" label="패스워드*" type="password" :rules="[required(), minLength(8), maxLength(16)]"/>
			<BaseTextInput color="#545FD6" label="패스워드 확인*" type="password" :rules="[required(), minLength(8), maxLength(16)]"/>
		</div>

		<div class="p-y-28 absolute" style="bottom: 20px; width: 100%">
			<BaseButton back-ground-color="#545FD6" label="회원가입"/>
			<p style="text-align: center; padding:0; magin: 0; font-size: 12px">
				힐링메이트 계정을 생성하시면 힐링메이트 <span style="text-decoration: underline;">이용약관</span> 및 
				<span style="text-decoration: underline;">개인정보보호정책</span>에 동의하는 것으로 간주합니다.
			</p>
		</div>

	</div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader'
import BaseButton from "@/components/common/BaseButton"
import BaseTextInput from "@/components/common/BaseTextInput"

import { validation } from "@/mixins/validation"

export default {
	name: 'AccountSignUpSecondPage',
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
	// methods: {},
}
</script>

<style>

</style>