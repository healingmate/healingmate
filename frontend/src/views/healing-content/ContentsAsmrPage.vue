<template>
  <div>
    <the-image-header class="relative" style="background-color: #00295A; height: 17vh;" background-image="asmr-contents-header.png" label="ASMR"></the-image-header>
    <!-- TODO : 이전 페이지 이동 버튼 넣기-->
    <div class="q-pa-lg flex row wrap justify-between">
      <contents-asmr 
        v-for="(asmr, index) in asmrList" 
        :key="index" 
        :entity="asmr"
      ></contents-asmr>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ContentsAsmr from '@/components/healing-content/ContentsAsmr';

export default {
  components: {
    TheImageHeader,
    ContentsAsmr,
  },
  data() {
    return {
      asmrList: [
        {
          id: 1,
          icon: '🌂',
          title: '빗소리',
          font_color: 'white',
          color: '#00295A',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
          width: '40vw',
          height: '25vh',
        },
        {
          id: 2,
          icon: '🌊',
          title: '파도소리',
          font_color: 'white',
          color: '#5A3272',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
          width: '40vw',
          height: '25vh',
        },
        {
          id: 3,
          icon: '🌿',
          title: '숲소리',
          font_color: 'white',
          color: '#2F6673',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
          width: '40vw',
          height: '25vh',
        },
        {
          id: 4,
          icon: '🔥',
          title: '장작소리',
          font_color: 'white',
          color: '#F7A6A3',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
          width: '40vw',
          height: '25vh',
        },
        {
          id: 5,
          icon: '💻',
          title: '키보드소리',
          font_color: 'white',
          color: '#392940',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
          width: '40vw',
          height: '25vh',
        },
        {
          id: 6,
          icon: '💧',
          title: '시냇물소리',
          font_color: 'white',
          color: '#4478A6',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
          width: '40vw',
          height: '25vh',
        },
        {
          id: 7,
          icon: '🦢',
          title: '산새소리',
          font_color: 'white',
          color: '#FC7C92',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
          width: '40vw',
          height: '25vh',
        },
        {
          id: 8,
          icon: '🦗',
          title: '밤벌레소리',
          font_color: 'white',
          color: '#D9A362',
          background_img: 'morning.jpg',
          audio: 'wind.mp3',
          time: 3,
          explanation: '추적추적 내리는 빗소리를 들으며 일상의 고민을 내려놓아요',
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