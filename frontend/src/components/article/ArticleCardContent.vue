<template>
  <span v-if="content" :class="isMore ? 'content' : 'content fade'">
    {{content}}
    <span v-if="isMore" class="more" style="bottom: -12px"
    @click="isMore = !isMore"> 
    접기
    </span>
    <span v-else class="more" style="bottom: 0;"
    @click="isMore = !isMore"> 
    ...더보기
    </span>
  </span>
</template>

<script>
export default {
	name: 'ArticleCardContent',
	// components: {},
	// filters: {},
  // mixins: [],
	props: {
    content: {
			type: String,
			default: null
		},
  },
	data() { 
    return {	
      isMore: false,
    }
	},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	// methods: {},
}
</script>

<style scoped>
.content {
  position: relative;
  display: -webkit-box; 
  font-weight: bold; 
  font-size: 12px; 
  margin-bottom: 12px;
}

.more {
  color: #959595;
  size: 12px; 
  position: absolute; 
  right: 0;
  z-index: 1; 
  cursor: pointer
}

.fade {
  overflow: hidden; 
  -webkit-box-orient: vertical; 
  -webkit-line-clamp: 2; 
}

.fade:after {
  content: "";
  text-align: right;
  position: absolute;
  bottom: 0;
  right: 0;
  width: 30%;
  height: 1.5em;
  background: linear-gradient(to right, rgba(255, 255, 255, 0), rgba(255, 255, 255, 1) 50%);
}
</style>