<template>
  <div>
    <!-- 기본 버튼 -->
    <div v-for="(item, index) in entity" :key="index">
      <q-card
        class="q-px-xl q-py-sm"
        :style="{ 
          'width': item.width,
          'height': item.height,
          'border-radius': '10px', 
          'background': item.color 
        }"
      >
        <div class="absolute-center ">
          <div
            class="relative-position"
            style="background: white; width: 12vh; height: 12vh; border-radius: 50%;">
            <div class="absolute-center text-h5">{{ item.icon }}</div>
          </div>
          <div 
            class="q-mt-md text-center text-subtitle2 text-bold"
            :style="{ 'color': item.font_color }"
          >
            {{ item.title }}
          </div>
        </div>
      </q-card>
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