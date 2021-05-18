<template>
<div>
	<div id="scene" ref="sceneContainer"></div>
</div>
</template>

<script></script>
<script>

export default {
  name: 'Blockland',
  // components: {},
	// filters: {},
  // mixins: [],
	// props: {},
	data() {
    return {
			BLOCKLAND: null, 
    }
	},
	// computed: {},
	// watch: {},
	created() {
		},
	mounted() {
		this.BLOCKLAND = new Game()
		this.initBlockLand(this.BLOCKLAND)
  },
	// updated() {},
	methods: {
		initBlockLand(blockland) {
		}
	},
};
</script>
<style scoped>
#scene-container {
  height: 100%;
}
</style>