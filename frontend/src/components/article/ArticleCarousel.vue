<template>
  <carousel
    :items="number"
    :margin="2"
    :nav="false"
    :dots="dots"
  >
    <slot/>
  </carousel>
</template>

<script>
import carousel from 'vue-owl-carousel2'

export default {
	name: '',
	components: {
    carousel,
  },
	// filters: {},
  // mixins: [],
	props: {
    // 캐러셀 한 슬라이드에 보여줄 아이템의 수
    number: {
			type: Number,
			default: 4
		},
    dots: {
      type: Boolean,
      default: false
    }
  },
  // data() {
  //   return {
  //   }
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