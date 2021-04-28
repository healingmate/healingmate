<template>
  <!-- 콘텐츠 card -->
  <div style="width: 42vw; relative-position">
    <div>
      <!-- Youtube -->
      <q-img 
        v-if="entity.category === '유튜브'"
        class="rounded-borders"
        :src="'https://img.youtube.com/vi/' + thumbnail + '/mqdefault.jpg'"
      >
        <div class="absolute-full flex flex-center">
          <q-icon
            class="text-white cursor-pointer"
            name="play_circle"
            size="md"
            @click="showVideo"
          ></q-icon>
        </div>
        <q-icon 
          class="cursor-pointer float-left q-ma-xs" 
          :name="entity.bookmarked ? 'bookmark' : 'bookmark_border'"
          size="sm" 
          color="white"
          @click="checkbookmarked"
        ></q-icon>
      </q-img>
      <!-- GIPHY 이미지 -->
      <q-img 
        v-else
        class="rounded-borders"
        :src="entity.contents"
      >
        <q-icon 
          class="cursor-pointer float-left q-ma-xs" 
          :name="entity.bookmarked ? 'bookmark' : 'bookmark_border'"
          size="sm" 
          color="white"
          @click="checkbookmarked"
        ></q-icon>
      </q-img>
    </div>
    <div class="items-center q-mt-xs q-mb-md">
      <q-chip class="text-caption" color="indigo-9" text-color="white">
        {{ entity.category }}
      </q-chip>
      <p class="inline-block text-caption text-bold q-pa-xs q-ma-none">{{ entity.title }}</p>
    </div>
    <!-- Youtube 개별 재생-->
    <q-dialog v-model="open">
      <div 
        class="relative"
        width="100%"
        height="200%" 
        style="background-color: transparent;"
      >
        <!-- 유튜브 개별 재생 종료 버튼 -->
        <q-btn 
          class="absolute" 
          style="top: 13vh; left: 46vw;" 
          round 
          color="white"
          text-color="black" 
          icon="close" 
          @click="open = false"
        />
        <iframe
          class="absolute-center"
          style="border-radius: 10px;"
          :src="'https://www.youtube.com/embed/' + thumbnail + '?rel=0?modestbranding=0'"
          frameborder="0"
          allowfullscreen
        ></iframe>
      </div>
    </q-dialog>
  </div>
</template>

<script>
export default {
  props: {
    entity: Object,
  },
  data() {
    return {
      open: false,
      thumbnail: this.entity.contents.split('/').reverse()[0]
    }
  },
  methods: {
    checkbookmarked() {
      this.entity.bookmarked = !this.entity.bookmarked;
    },
    showVideo() {
      this.open = true;
    }
  }
}
</script>

<style scoped>
</style>