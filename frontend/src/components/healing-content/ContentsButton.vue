<template>
  <!-- 기본 버튼 -->
  <q-card
    class="q-px-xl q-py-sm q-mb-md"
    :style="{ 
      'width': entity.width,
      'height': entity.height,
      'border-radius': '10px', 
      'background': entity.color 
    }"
  >
    <div class="absolute-center ">
      <div
        class="relative-position"
        style="background: white; width: 12vh; height: 12vh; border-radius: 50%;">
        <div class="absolute-center text-h5">{{ entity.icon }}</div>
      </div>
      <div 
        class="q-mt-md text-center text-subtitle2 text-bold"
        :style="{ 'color': entity.font_color }"
      >
        {{ entity.title }}
      </div>
    </div>
  </q-card>
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