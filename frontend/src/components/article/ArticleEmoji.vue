<template>
  <span>
		<q-btn flat>
      <q-avatar size="20px">
        <img :src="require(`../../assets/images/article-emoji-list/${emoji}.png`)">
      </q-avatar>
    </q-btn>
		<span>{{likeCount}}</span>
  </span>
</template>

<script>


export default {
	name: 'ArticleEmoji',
	// components: {},
	// filters: {},
  // mixins: [],
	props: {
		isLikeEmoji: {
			type: Boolean,
			default: false
		},
		emoji:{
			type: String,
			default: null
		},
		likeCount: {
			type: Number,
			default: 0
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
.emoji {
  height: 24px;
  width: 24px;
}

.q-btn__wrapper{
	padding: 4px 4px 5px 4px !important;
}

</style>