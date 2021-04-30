<template>
  <div class="row justify-center">
    <div class="col-xs-12 col-sm-6 col-md-4" style="height: 100vh; position: relative;">
      <router-view :is-night="isNight"/>
    </div>
  </div>
</template>

<script>

export default {
  name: 'App',
	// components: {},
	// filters: {},
  // mixins: [],
	// props: {},
	// data() {
	//   return {}
	// },
	computed: {
		// 현재 시간이 낮인지 밤인지 구하는 boolean 타입의 isNight 밤이면 true 낮이면 false
		isNight() {
			const hour = new Date().getHours();
			return 18 <= hour || hour <= 6
		}
	},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	// methods: {},
}
</script>

<style>
@import url('./assets/styles/global.css');
</style>
