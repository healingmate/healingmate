<template>
  <div :class="isNight ? 'home home--night-backgound-color' : 'home home--day-backgroud-color'">
		<img 
			:src="require(isNight ? '@/assets/images/image-header/night_1x1.jpg' : '@/assets/images/image-header/day_1x1.jpg')" 
			alt="사진을 불러오지 못했어요." 
			style="width: 100%;"
			class="layered-image"
		>

		<p v-if="isNight" class="home_saying home--night-font-color">오늘 하루도 고생했어요.</p>
		<p v-else class="home_saying home--day-font-color">오늘 하루도 힘내세요.</p>

		<div class="absolute p-y-28" style="bottom: 20px; width: 100%;">
			<BaseButton back-ground-color="#545FD6" label="로그인" @click.native="onSignInButton"/>
			<BaseButton back-ground-color="#ffffff" text-color="#545FD6" label="회원가입" @click.native="onSignUpButton"/>
		</div>

	</div>
</template>

<script>
import BaseButton from '@/components/common/BaseButton'

export default {
	name: 'Home',
	components: {
		BaseButton,
	},
	// filters: {},
  // mixins: [],
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