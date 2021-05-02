<template>
  <div class="inline-block q-mt-md">
    <!-- 키워드 -->
    <q-badge 
      v-if="!entity.click"
      class="cursor-pointer text-caption text-bold q-px-sm q-mr-xs" 
      outline 
      color="indigo-9" 
      text-color="white"
      @click="toggleKeyword"
      :label="'# ' + entity.keyword"
    />
    <q-badge
      v-else
      class="cursor-pointer text-caption text-bold q-px-sm q-mr-xs" 
      color="indigo-9" 
      text-color="white"
      @click="toggleKeyword"
      :label="'# ' + entity.keyword"
    />
  </div>
</template>

<script>
export default {
  props: {
    entity: Object,
  },
  // methods: {
  //   toggleKeyword() {
  //     this.entity.click = !this.entity.click;
  //   }
  // }
}
</script>

<style scoped>

</style>