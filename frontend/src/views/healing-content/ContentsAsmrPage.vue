<template>
  <div>
    <the-image-header class="relative" background-image="test.png" label="ASMR"></the-image-header>
    <!-- TODO : 이전 페이지 이동 버튼 넣기-->
    <div class="q-pa-lg flex row wrap justify-between">
      <contents-button 
        v-for="(asmr, index) in asmrList" 
        :key="index" 
        :entity="asmr"
      ></contents-button>
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
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ContentsButton from '@/components/healing-content/ContentsButton';

export default {
  components: {
    TheImageHeader,
    ContentsButton,
  },
  data() {
    return {
      asmrList: [
        {
          icon: '🌂',
          title: '빗소리',
          font_color: 'white',
          color: '#00295A',
          width: '40vw',
          height: '25vh',
        },
        {
          icon: '🌊',
          title: '파도소리',
          font_color: 'white',
          color: '#5A3272',
          width: '40vw',
          height: '25vh',
        },
        {
          icon: '🌿',
          title: '숲소리',
          font_color: 'white',
          color: '#2F6673',
          width: '40vw',
          height: '25vh',
        },
        {
          icon: '🔥',
          title: '장작소리',
          font_color: 'white',
          color: '#F7A6A3',
          width: '40vw',
          height: '25vh',
        },
        {
          icon: '💻',
          title: '키보드소리',
          font_color: 'white',
          color: '#392940',
          width: '40vw',
          height: '25vh',
        },
        {
          icon: '💧',
          title: '시냇물소리',
          font_color: 'white',
          color: '#4478A6',
          width: '40vw',
          height: '25vh',
        },
        {
          icon: '🦢',
          title: '산새소리',
          font_color: 'white',
          color: '#FC7C92',
          width: '40vw',
          height: '25vh',
        },
           {
          icon: '🦗',
          title: '밤벌레소리',
          font_color: 'white',
          color: '#D9A362',
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