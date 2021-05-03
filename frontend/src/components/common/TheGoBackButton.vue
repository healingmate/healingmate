<template>
  <q-icon :size="`${size}rem`" name="arrow_back_ios_new" @click="goBack" />
</template>

<script>
export default {
  name: 'TheGoBackButton',
  props: {
    size: {
      type: Number,
      default: 1.5,
    },
  },
  methods: {
    goBack() {
      console.log('clicked');
      this.$router.go(-1);
    },
  },
};
</script>

<style>
.back {
  background-color: transparent;

  padding-left: 10px;
  /* backface-visibility: visible; */
}
</style>
