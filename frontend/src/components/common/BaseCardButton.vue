<template>
  <div>
    <!-- 기본 버튼 -->
    <q-btn
      :size="entity.size"
      class="q-px-xl q-py-sm"
      text-color="white"
      :style="{ 
        'width': entity.width,
        'height': entity.height,
        'border-radius': '10px', 
        'background': entity.color 
      }"
    >
      <div>
        <div>{{ entity.icon }}</div>
        <div class="text-caption text-bold">{{ entity.title }}</div>
      </div>
    </q-btn>
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