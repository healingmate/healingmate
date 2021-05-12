<template>
  <!-- 기본 버튼 -->
  <q-btn
    :size="entity.size"
    class=""
    :text-color="entity.textColor"
    :style="{
      width: entity.width,
      height: entity.height,
      'border-radius': '10px',
      'border-color': entity.borderColor,
      background: entity.color,
    }"
  >
    <div>
      <div>{{ entity.icon }}</div>
      <div class="text-caption text-bold">{{ entity.title }}</div>
    </div>
  </q-btn>
</template>

<script>
export default {
  props: {
    entity: Object,
  },
};
</script>

<style scoped></style>
