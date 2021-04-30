<template>
  <!-- 음악 재생 -->
    <q-dialog 
      :maximized="maximizedToggle"
      class="relative"
    >
      <q-card class="text-white">
        <q-img
          :src="require(`@/assets/images/music-background/${ entity.background_img }`)"
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
          @click="open = false"
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
        <base-button v-if="play === false" @click.native="playMusic" style="bottom: 20vh; left: 50%; transform: translateX(-50%);" back-ground-color="#244684" label="PLAY" width="9rem" icon="play_arrow"></base-button>
        <base-button v-else @click.native="playMusic" style="bottom: 20vh; left: 50%; transform: translateX(-50%);" back-ground-color="#244684" label="PLAYING" width="9rem" icon="pause"></base-button>
      </q-card>
    </q-dialog>
</template>

<script>
import BaseButton from '@/components/common/BaseButton.vue';
export default {
  components: { 
    BaseButton 
  },
  props: {
    entity: Object,
  },
  data() {
    return {
      open: true,
      maximizedToggle: true,
      meditationInfo: this.entity,
      play: false,
    }
  },
  methods: {
    playMusic() {
      console.log('클릭')
      this.play = !this.play;
    }
  }
}
</script>

<style>

</style>