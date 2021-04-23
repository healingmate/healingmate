<template>
  <!-- 콘텐츠 card -->
  <div style="width: 50vw; relative-position">
    <q-video
      :ratio="16/9"
      class="rounded-borders"
      src="https://www.youtube.com/embed/k3_tw44QsZQ?rel=0"
    />
    <div class="items-center q-mt-xs">
      <q-chip class="text-caption" color="indigo-9" text-color="white">
        유튜브
      </q-chip>
      <p class="inline-block text-subtitle2 text-bold q-py-xs q-ma-none">힐링 콘텐츠</p>
      <q-icon class="cursor-pointer float-right" name="bookmark_border" size="sm" color="indigo-9"></q-icon>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>