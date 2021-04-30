<template>
  <div style="position: relative;">
    <img :src="require(`../../assets/images/image-header/${backgroundImage}`)" alt="사진을 불러오지 못했어요." style="width: 100%;">
    <svg viewBox="0 0 500 150" style="position: absolute; bottom: 0; left: 0;">
      <path 
        d="M-20.03,121.47 C210.54,38.57 267.36,175.75 521.38,87.92 L499.66,150.00 L0.00,150.00 Z" 
        :style="`stroke: none; fill: ${color};`"> 
      </path>
    </svg>
    <p
      v-if="label" 
      style="position: absolute; top: 16px; width: 100%; text-align: center; font-size: 24px; color: #ffffff;"
    >
      {{label}}
    </p>
  </div>
</template>

<script>
export default {
	name: 'TheImageHeader',
	// components: {},
	// filters: {},
  // mixins: [],
	props: {
    color: {
			type: String,
			default: '#ffffff'
		},
    backgroundImage: {
			type: String,
			default: null
		},
    label: {
			type: String,
			default: null
		},
  },
	// data() {
	//   return {		}
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