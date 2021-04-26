<template>
  <!-- 콘텐츠 card -->
  <div style="width: 45vw; relative-position">
    <div>
      <!-- <iframe
        id="iframe"
        width="100%"
        height="100vh"
        style="border-radius: 10px;"
        :src="entity.contents"
        frameborder="0"
        allowfullscreen
      ></iframe> -->
      <!-- Youtube -->
      <q-img 
        v-if="entity.category === '유튜브'"
        class="rounded-borders"
        :src="'https://img.youtube.com/vi/' + thumbnail + '/mqdefault.jpg'"
      >
        <div class="absolute-full flex flex-center">
          <!-- <a :href="entity.contents">
            <q-icon
              class="text-white"
              name="play_circle"
              size="md"
            ></q-icon>
          </a>   -->
          <q-icon
            class="text-white"
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
    <!-- <q-video
      :ratio="16/9"
      class="rounded-borders"
      :src="entity.youtube"
    /> -->
    <div class="items-center q-mt-xs">
      <q-chip class="text-caption" color="indigo-9" text-color="white">
        {{ entity.category }}
      </q-chip>
      <p class="inline-block text-caption text-bold q-pa-xs q-ma-none">{{ entity.title }}</p>
    </div>
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