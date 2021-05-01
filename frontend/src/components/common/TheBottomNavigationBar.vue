<template>
  <!-- <q-layout view="lHh lpr lFf" container style="height: 400px" class="shadow-2 rounded-borders">
      <div class="nav">
        <q-footer bordered class="bg-white text-primary">
          <q-tabs no-caps active-color="primary" indicator-color="transparent" class="text-grey" v-model="tab">
            <q-tab name="feeds" label="Feeds" icon="chat" />
            <q-tab name="matching" label="Matching" icon="pets" />
            <q-tab name="contents" label="Contents" icon="dynamic_feed" />
          </q-tabs>
        </q-footer>
      </div>
    </q-layout> -->

  <q-tabs id="bottom-nav" bordered no-caps active-color="white" indicator-color="transparent" class="text-grey" v-model="tab">
    <q-tab name="feeds" label="Feeds" icon="chat" />
    <q-tab name="matching" label="Matching" icon="pets" />
    <q-tab name="contents" label="Contents" icon="dynamic_feed" />
  </q-tabs>
</template>

<script>
export default {
  data() {
    return {
      tab: 'images',
    };
  },
};
</script>

<style>
#bottom-nav {
  position: absolute;
  background-color: #244684;
  width: 100%;
}
.q-tab {
  color: #7b9ad3;
}
</style>
