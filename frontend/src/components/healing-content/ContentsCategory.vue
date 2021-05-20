<template>
  <!-- 콘텐츠 메인 페이지 카테고리 -->
    <q-card 
      @click="goToDetail"
      class="my-card row entitys-center q-mb-md" 
      :style="{ 'border-radius': '10px', 'background': entity.color }"
    >
      <div 
        class="col-4 transparent flex flex-center q-ml-xl text-subtitle2 text-bold" 
        :style="{ 'color': entity.font_color }"
      >
        {{ entity.title }}
      </div>
      <q-img class="col-6" style="height: 13vh;" :src="require(`@/assets/images/category/${ entity.img }`)" />
    </q-card>
</template>

<script>

export default {
  props: {
    entity: Object,
  },
  methods: {
    goToDetail() {
      this.$router.push(`/healing-content/${this.entity.address}`);
    }
  }
}
</script>

<style scoped>

</style>