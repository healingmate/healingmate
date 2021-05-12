<template>
  <div class="inline-block q-mt-sm">
    <!-- 키워드 -->
    <q-badge 
      v-if="entity.click === false"
      class="cursor-pointer text-caption text-bold q-px-sm q-mr-sm" 
      outline 
      color="indigo-9" 
      text-color="white"
      :label="'# ' + entity.keyword"
    />
    <q-badge
      v-else
      class="cursor-pointer text-caption text-bold q-px-sm q-mr-sm" 
      color="indigo-9" 
      text-color="white"
      :label="'# ' + entity.keyword"
    />
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
.bg-indigo-9 {
  border: 1px solid currentColor;
}
</style>