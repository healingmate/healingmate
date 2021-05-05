<template>
  <div style="position: absolute; top: 20px; left: 20px;">
    <q-icon :size="`${size}rem`" :color="color" name="arrow_back_ios_new" @click="goBack" />
  </div>
</template>

<script>
export default {
  name: 'TheGoBackButton',
  props: {
    size: {
      type: Number,
      default: 2,
    },
    color: {
      type: String,
      default: 'white'
    }
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
</style>
