<template>
  <q-img 
    :src="articleImage"
    style="border-radius: 14px;" 
  >
    <div 
      v-if="deletable" 
      style="border-radius: 6px; right: 5px; top: 5px; padding: 0px; line-height: 0;"
    >
      <q-btn 
        icon="remove"
        flat push padding="0"
        style="font-size: 0.5em;"
        @click="onClick" 
      />
    </div>
  </q-img>
</template>

<script>

export default {

	name: 'ArticleCarouselItem',
	// components: {},
	// filters: {},
  // mixins: [],
	props: {
    articleImage: {
			type: String,
			default: null
		},
    deletable: {
      type: Boolean,
      default: false
    },
    index: Number,
  },
	// data() {
	//   return {		}
	// },
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
    onClick() {
      this.$emit("onDeleteItemIndex", this.index)
    }
  },
}
</script>

<style>
.q-btn__wrapper {
  padding: 4px 4px 4px 4px !important;
}
</style>