<template>
  <div :class="isNight ? 'home home--night-backgound-color' : 'home home--day-backgroud-color'">
		<img 
			:src="require(isNight ? '@/assets/images/image-header/night_1x1.jpg' : '@/assets/images/image-header/day_1x1.jpg')" 
			alt="사진을 불러오지 못했어요." 
			style="width: 100%;"
			class="layered-image"
		>

		<div class="p-y-28 absolute" style="bottom: 130px; width: 100%">
			<BaseTextInput :color="isNight ? '#ffffff' : '#000000'" label="아이디" :rules="[required(), minLength(5), maxLength(16), alphabetNumber()]"/>
			<BaseTextInput :color="isNight ? '#ffffff' : '#000000'" label="비밀번호" type="password" :rules="[required(), minLength(8), maxLength(16)]"/>
		</div>

		<div class="p-y-28 absolute" style="bottom: 20px; width: 100%">
			<BaseButton back-ground-color="#545FD6" label="다음" />
		</div>

	</div>
</template>

<script>
import BaseButton from '@/components/common/BaseButton'
import BaseTextInput from "@/components/common/BaseTextInput"

import { validation } from "@/mixins/validation"

export default {
	name: 'AccountSignInPage',
	components: {
		BaseButton,
		BaseTextInput,
	},
	// filters: {},
  mixins: [validation],
	props: {
		isNight: Boolean
	},
	// data() {
	//   return {}
	// },
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		onSignInButton() {
			this.$router.push({name: 'AccountSignInPage'})
		},
		onSignUpButton() {
			this.$router.push({name: 'AccountSignUpFirstPage'})
		},
	},
}
</script>

<style scoped`>
.home { 
	height: 100vh;
}

.home_saying {
	text-align: center; 
	font-size: 24px;
	font-weight: 300;
}

.home--night-backgound-color {
	background-color: #000934;
}

.home--night-font-color {
	color: #ffffff;
}

.home--day-backgroud-color {
	background-color: #FFD89D;
}

.home--day-font-color {
	color: #000000;
}
</style>