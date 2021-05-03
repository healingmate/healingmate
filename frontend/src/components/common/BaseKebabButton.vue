<template>
  <q-btn :size="`${size}rem`" flat icon="more_vert">
    <q-menu transition-show="flip-right" transition-hide="flip-left" auto-close>
      <q-list style="min-width: 100px">
        <slot />
      </q-list>
    </q-menu>
  </q-btn>
</template>

<script>
export default {
  name: 'BaseKebabButton',
  props: {
    size: {
      type: Number,
      default: 1.5,
    },
  },
  components: {},
  data() {
    return {};
  },

  methods: {
    onClick() {
      console.log('clicked');
      this.isOpened = true;
      console.log(this.isOpened);
    },
    onClose(state) {
      console.log('닫기');
      this.isOpened = state;
    },
  },
  created() {},
};
</script>

<style>
.back {
  background-color: transparent;

  margin: auto;
  /* backface-visibility: visible; */
}
</style>
