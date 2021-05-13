<template>
  <q-btn :size="`${size}rem`" flat icon="more_vert">
    <q-menu transition-show="flip-right" transition-hide="flip-left" auto-close>
      <q-list style="min-width: 90px">
        <slot />
      </q-list>
    </q-menu>
  </q-btn>
</template>

<script>

export default {
  name: 'BaseKebabButton',
	// components: {},
	// filters: {},
  // mixins: [],
  props: {
    size: {
      type: Number,
      default: 1.5,
    },
  },
	// data() {
	//   return {}
	// },
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	// methods: {},
};
</script>

<style>
</style>
