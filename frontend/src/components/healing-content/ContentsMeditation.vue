<template>
  <div>
    <!-- 명상 card -->
    <q-card 
      class="my-card"
      :style="{ 
        'width': entity.width,
        'height': entity.height,
        'border-radius': '10px', 
      }"
      @click="goToDetail"
    >
      <!-- @click="goToDetail(entity.id)" -->
      <q-img
        style="width=100%; height: 100%; border-radius: 10px;"
        :src="require(`@/assets/images/meditation/${ entity.background_img }`)"
      >
        <div class="absolute-bottom text-right transparent" style="padding-right: 10px; padding-bottom: 5px;">
          <p class="q-ma-none text-caption text-blue-grey-1">{{ entity.time }} min</p>
          <p class="q-ma-none text-bold" style="font-size: 0.rem;">{{ entity.title }}</p>
        </div>
      </q-img>  
    </q-card>
    <!-- 음악 재생 -->
    <!-- <contents-music v-model="open" :entity="entity"></contents-music> -->
    <q-dialog 
      v-model="open" 
      :maximized="maximizedToggle"
      class="relative"
    >
      <q-card class="text-white">
        <q-img
          :src="require(`@/assets/images/meditation/${ entity.background_img }`)"
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
        <!-- 명상 음악 -->
        <audio v-if="play === true" autoplay>
          <source :src="require(`@/assets/audios/meditation/${entity.audio}`)">
        </audio>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
// import ContentsMusic from '@/components/healing-content/ContentsMusic';
import BaseButton from '@/components/common/BaseButton.vue';
export default {
  components: { 
    // ContentsMusic,
    BaseButton 
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
      // this.$router.push(`/healing-content/meditation/${id}`);
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