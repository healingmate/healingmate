<template>
  <div>
    <!-- 기본 버튼 -->
    <div v-for="(item, index) in entity" :key="index">
      <q-btn
        :size="item.size"
        class="q-px-xl q-py-sm"
        text-color="white"
        :style="{ 
          'width': item.width,
          'height': item.height,
          'border-radius': '10px', 
          'background': item.color 
        }"
      >
        <div>
          <div>{{ item.icon }}</div>
          <div class="text-caption text-bold">{{ item.title }}</div>
        </div>
      </q-btn>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    entity: Array,
  },
}
</script>

<style scoped>

</style>