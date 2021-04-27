<template>
  <div class="q-pa-lg">
    <contents-button :entity="contents"></contents-button>
    <!-- 연습 -->
    <!-- <div>
      <audio controls src="/wind.MP3"> Your user agent does not support the HTML5 Audio element. </audio>
    </div> -->
    <!-- <div class="music-player"> -->
    <!-- <audio
      ref="audio"
      :src="require('@/assets/asmr/wind.MP3')"
      preload
      loop
      id="audio"
      type="mpeg"
    ></audio> -->
  <!-- </div> -->
  </div>
</template>

<script>
import ContentsButton from '@/components/common/ContentsButton.vue';

export default {
  components: {
    ContentsButton,
  },
  data() {
    return {
      contents: [
        {
          icon: '🌿',
          title: '숲소리',
          font_color: 'white',
          color: '#2F6673',
          width: '40vw',
          height: '25vh',
        }
      ]
    }
  }
}
</script>

<style>

</style>