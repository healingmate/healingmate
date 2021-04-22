<template>
  <div>
    <q-btn class="back" round icon="notifications_none" @click="onClick" />
  </div>
</template>

<script>
export default {
  methods: {
    onClick() {
      console.log('clicked');
    },
  },
};
</script>

<style>
.back {
  background-color: transparent;

  margin: auto;
  /* backface-visibility: visible; */
}
</style>
