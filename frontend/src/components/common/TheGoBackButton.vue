<template>
  <div>
    <q-btn class="back" round icon="arrow_back_ios" @click="goBack" />
    <q-btn class="back" round icon="arrow_back_ios_new" @click="goBack" />
  </div>
</template>

<script>
export default {
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
