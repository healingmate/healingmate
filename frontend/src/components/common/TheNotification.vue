<template>
  <!-- color default는 흰색입니다 -->
  <q-icon 
    :size="`${size}rem`" 
    name="notifications" 
    @click="goToNotification" 
    :color="color" />
</template>

<script>
export default {
  name: 'TheNotification',
  props: {
    size: {
      type: Number,
      default: 1.5,
    },
    color: {
      type: String,
      default: 'white',
    },
  },
  methods: {
    goToNotification() {
      this.$router.push('/notification');
    }
  },
};
</script>

<style></style>
