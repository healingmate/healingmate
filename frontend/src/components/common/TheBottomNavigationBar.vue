<template>
  <q-tabs id="nav" class="text-grey" bordered no-caps active-color="white" indicator-color="transparent" v-model="tab">
    <q-tab name="feeds" label="Feeds" icon="chat" />
    <q-tab name="matching" label="Matching" icon="pets" />
    <q-tab name="contents" label="Contents" icon="dynamic_feed" />
  </q-tabs>
</template>

<script>
export default {
  name: 'TheBottomNavigationBar',
  props: {
    width: {
      type: String,
      default: '100%',
    },
  },
  data() {
    return {
      tab: 'images',
    };
  },
};
</script>

<style>
#nav {
  position: fixed;
  width: 100%;
  background-color: #244684;
  bottom: 0;
  z-index: 100;
}
.q-tab {
  color: #7b9ad3;
}
</style>
