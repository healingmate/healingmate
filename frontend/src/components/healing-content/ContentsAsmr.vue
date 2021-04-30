<template>
  <div>
    <!-- asmr card -->
    <q-card
      class="q-px-xl q-py-sm q-mb-md"
      :style="{ 
        'width': entity.width,
        'height': entity.height,
        'border-radius': '10px', 
        'background': entity.color 
      }"
      @click="goToDetail"
    >
      <div class="absolute-center ">
        <div
          class="relative-position"
          style="background: white; width: 12vh; height: 12vh; border-radius: 50%;">
          <div class="absolute-center text-h5">{{ entity.icon }}</div>
        </div>
        <div 
          class="q-mt-md text-center text-subtitle2 text-bold"
          :style="{ 'color': entity.font_color }"
        >
          {{ entity.title }}
        </div>
      </div>
    </q-card>
    <!-- 음악 재생 -->
    <q-dialog 
      v-model="open" 
      :maximized="maximizedToggle"
      class="relative"
    >
      <q-card class="text-white">
        <q-img
          :src="require(`@/assets/images/asmr/${ entity.background_img }`)"
          style="filter: brightness(50%);"
          width="100%"
          height="100%">
        </q-img>
        <q-btn
          class="absolute" 
          style="top: 13vh; left: 50%; transform: translateX(-50%);" 
          round 
          color="white"
          text-color="black" 
          icon="close" 
          @click="[open = false, play = false]"
        />
        <q-card-section 
          class="absolute text-center" 
          style="width: 200px; top: 25vh; left: 50%; transform: translateX(-50%);"
        >
          <div class="text-blue-grey-12">
            {{ entity.time }} min
          </div>
          <div class="text-h5 text-weight-bold text-white">
            {{ entity.title }}
          </div>
        </q-card-section>
        <q-card-section 
          class="absolute text-center text-caption" 
          style="width: 300px; top: 50vh; left: 50%; transform: translateX(-50%);"
        >
          {{ entity.explanation }}
        </q-card-section>
        <base-button 
          v-if="play === false" 
          @click.native="playMusic" 
          style="bottom: 20vh; left: 50%; transform: translateX(-50%);" 
          back-ground-color="#244684" 
          label="PLAY" 
          width="9rem" 
          icon="play_arrow"
        ></base-button>
        <base-button 
          v-else 
          @click.native="playMusic" 
          style="bottom: 20vh; left: 50%; transform: translateX(-50%);" 
          back-ground-color="#244684" 
          label="PLAYING" 
          width="9rem" 
          icon="pause"
        ></base-button>
        <!-- asmr 음악 -->
        <audio v-if="play === true" autoplay>
          <source :src="require(`@/assets/audios/asmr/${entity.audio}`)">
        </audio>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import BaseButton from '@/components/common/BaseButton.vue';
export default {
  components: {
    BaseButton,
  },
  props: {
    entity: Object,
  },
  data() {
    return {
      open: false,
      maximizedToggle: true,
      meditationInfo: this.entity,
      play: false,
    }
  },
  methods: {
    goToDetail() {
      console.log('클릭')
      console.log(this.open)
      this.open = true;
    },
    playMusic() {
      console.log('클릭')
      this.play = !this.play;
    }
  }
}
</script>

<style scoped>

</style>