<template>
  <!-- 알람 -->
  <div class="row">
    <q-avatar size="40px" class="q-py-xs">
      <img :src="entity.profile_img">
    </q-avatar>
    <div class="col-10 q-py-xs q-pl-md">
      <div>
        <p class="inline-block text-caption text-bold q-ma-none">{{ entity.username }}</p>
        <p class="inline-block text-caption text-blue-grey-6 q-ma-none">님이 반응을 남겼습니다.</p>
      </div>
      <div class="inline-block text-caption text-blue-grey-6">{{ entity.date }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    entity: Object,
  },
  data() {
    return {

    };
  },
}
</script>

<style scoped>

</style>