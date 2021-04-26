<template>
  <div>
    <!-- 콘텐츠 메인 카테고리 card -->
    <q-card class="my-card row items-center" :style="{ 'background': entity.color }">
      <div class="col-4 transparent justify-center q-ml-lg text-subtitle2 text-bold">{{ entity.title }}</div>
      <q-img class="col-6" style="height: 13vh;" :src="entity.img" />
    </q-card>
  </div>
</template>

<script>
export default {
  props: {
    entity: Object,
  },
}
</script>

<style scoped>

</style>