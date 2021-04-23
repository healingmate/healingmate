<template>
  <div>
    <!-- 키워드 -->
    <q-chip class="text-caption text-bold" outline square color="indigo-9" text-color="white">
      # {{ entity.keyword }}
    </q-chip>
  </div>
</template>

<script>
export default {
  props: {
    entity: Object,
  },
}
</script>

<style>

</style>