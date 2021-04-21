<template>
  <div>
    <div>
      <ion-icon name="chevron-back-outline">아이콘</ion-icon>
    </div>
    <div>
      <q-btn color="" icon="chevron-left" label="" @click="goBack" />
    </div>
    <!-- <q-btn v-go-back="'/'" color="primary" /> -->
  </div>
</template>

<script>
export default {
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
