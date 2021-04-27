<template>
  <div>
    <!-- 콘텐츠 메인 페이지 카테고리 -->
    <div v-for="(item, index) in entity" :key="index">
      <q-card 
        class="my-card row items-center q-mb-md" 
        :style="{ 'border-radius': '10px', 'background': item.color }"
      >
        <div 
          class="col-4 transparent justify-center q-ml-xl text-subtitle2 text-bold" 
          :style="{ 'color': item.font_color }"
        >
          {{ item.title }}
        </div>
        <q-img class="col-6" style="height: 13vh;" :src="require(`@/assets/images/${ item.img }`)" />
      </q-card>
    </div>
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