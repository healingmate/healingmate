<template>
  <q-btn
    v-if="icon"
    :style="`background: ${backGroundColor}; color: ${textColor}; height: 44px; width: ${width}; margin-bottom: 20px;`"
    size="1rem"
    :label="label" 
		:disable="disable"
    :icon="icon"
  />
  <q-btn
    v-else
    :style="`background: ${backGroundColor}; color: ${textColor}; height: 44px; width: ${width}; margin-bottom: 20px;`"
    size="1rem"
    :label="label" 
		:disable="disable"
  />
</template>

<script>
export default {
	name: 'BaseButton',
	// components: {},
	// filters: {},
	props: {
		backGroundColor: {
			type: String,
			default: '#545FD6'
		},
		textColor: {
			type: String,
			default: '#ffffff'
		},
		label: {
			type: String,
			default: null,
		},
    icon: {
      type: String,
      default: null,
    },
    width: {
      type: String,
      default: '100%'
    },
		disable: {
			type: Boolean,
			default: false,
		}
	},
	// data() {},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	// methods: {},
}
</script>

<style scoped>
.q-btn--rectangle {
  border-radius: 14px !important;
}    
</style>
