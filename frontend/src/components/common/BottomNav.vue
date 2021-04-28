<template>
  <div class="q-pa-md">
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

    <q-tabs bordered no-caps active-color="primary" indicator-color="transparent" class="text-grey" v-model="tab">
      <q-tab name="feeds" label="Feeds" icon="chat" />
      <q-tab name="matching" label="Matching" icon="pets" />
      <q-tab name="contents" label="Contents" icon="dynamic_feed" />
    </q-tabs>
  </div>
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
.nav {
  position: absolute;
}
</style>
