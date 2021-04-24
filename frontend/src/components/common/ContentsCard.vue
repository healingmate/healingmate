<template>
  <!-- 콘텐츠 card -->
  <div style="width: 50vw; relative-position">
    <q-video
      :ratio="16/9"
      class="rounded-borders"
      :src="entity.youtube"
    />
    <div class="items-center q-mt-xs">
      <q-chip class="text-caption" color="indigo-9" text-color="white">
        {{ entity.category }}
      </q-chip>
      <p class="inline-block text-subtitle2 text-bold q-py-xs q-ma-none">{{ entity.title }}</p>
      <q-icon 
        class="cursor-pointer float-right" 
        :name="entity.bookmarked ? 'bookmark' : 'bookmark_border'"
        size="sm" 
        color="indigo-9"
        @click="checkbookmarked"
      ></q-icon>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    entity: Object,
  },
  methods: {
    checkbookmarked() {
      this.entity.bookmarked = !this.entity.bookmarked;
    }
  }
}
</script>

<style>

</style>