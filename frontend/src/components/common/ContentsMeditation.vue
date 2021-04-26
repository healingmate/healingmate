<template>
  <div>
    <!-- 명상 card -->
    <q-card class="my-card">
      <q-img style="height: 27vh;" :src="entity.background_img">
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