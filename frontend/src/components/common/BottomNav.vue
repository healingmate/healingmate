<template>
  <div class="q-pa-md">
    <q-layout view="lHh lpr lFf" container style="height: 400px" class="shadow-2 rounded-borders">
      <q-footer bordered class="bg-white text-primary">
        <q-tabs no-caps active-color="primary" indicator-color="transparent" class="text-grey" v-model="tab">
          <q-tab name="feeds" label="Feeds" icon="comment-text-outline" />
          <q-tab name="matching" label="Matching" />
          <q-tab name="contents" label="Contents" />
        </q-tabs>
      </q-footer>
    </q-layout>
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

<style></style>
