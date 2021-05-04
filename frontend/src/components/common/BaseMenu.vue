<template>
  <q-item clickable style="padding: 0;">
    <q-item-section avatar>
      <q-icon :name="icon" class="" style="padding-left: 14px; font-size: 12px;"/>
    </q-item-section>
      <q-item-section style="text-align:center;">{{text}}</q-item-section>
  </q-item>
</template>

<script>

export default {
	name: 'BaseMenu',
	// components: {},
	// filters: {},
  // mixins: [],
	props: {
    icon: {
      type: String,
      default: null,
    },
    text: {
      type: String,
      default: null,
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
}
</script>

<style></style>
