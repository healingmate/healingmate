<template>
  <div>
    <!-- 명상 card -->
    <q-card 
      class="my-card"
      :style="{ 
        'width': entity.width,
        'height': entity.height,
        'border-radius': '10px', 
      }"
    >
      <q-img
        style="width=100%; height: 100%; border-radius: 10px;"
        :src="entity.background_img"
      >
        <div class="absolute-bottom text-right transparent">
          <p class="q-ma-none text-caption text-blue-grey-2">{{ entity.time }} min</p>
          <p class="q-ma-none text-subtitle2 text-bold">{{ entity.title }}</p>
        </div>
      </q-img>  
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