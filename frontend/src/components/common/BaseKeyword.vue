<template>
  <div class="inline-block q-mt-sm">
    <!-- 키워드 -->
    <q-badge 
      v-if="check === false"
      class="cursor-pointer text-caption text-bold q-px-sm q-mr-sm" 
      outline 
      color="indigo-9" 
      text-color="white"
      :label="'# ' + entity"
    />
    <!-- @click="toggleKeyword()" -->
    <q-badge
      v-else
      class="cursor-pointer text-caption text-bold q-px-sm q-mr-sm" 
      color="indigo-9" 
      text-color="white"
      :label="'# ' + entity"
    />
  </div>
</template>

<script>
export default {
  props: {
    entity: Object && String,
    check: Boolean,
  },
  methods: {
    // toggleKeyword() {
    //   this.check = !this.check;
    // }
  }
}
</script>

<style scoped>

</style>