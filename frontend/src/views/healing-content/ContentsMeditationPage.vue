<template>
  <div>
    <the-image-header class="relative" background-image="test.png" label="Meditation"></the-image-header>
    <div class="q-pa-lg">
      <div class="text-subtitle2 text-weight-bold q-mb-sm q-ml-xs">
        추천명상
      </div>
      <!-- TODO : dots true 지정 가능하도록 -->
      <article-carousel :number="1">
        <contents-meditation 
          v-for="(mainMeditation, index) in mainMeditationList" 
          :key="index"
          :entity="mainMeditation"
        >
        </contents-meditation>
      </article-carousel>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ContentsMeditation from '@/components/healing-content/ContentsMeditation';
import ArticleCarousel from '@/components/article/ArticleCarousel.vue';

export default {
  components: {
    TheImageHeader,
    ContentsMeditation,
    ArticleCarousel,
  },
  props: {
    // 캐러셀 한 슬라이드에 보여줄 아이템의 수
    number: {
			type: Number,
			default: 1
		},
  },
  data() {
    return {
      mainMeditationList : [
        {
          id: 1,
          time: 11,
          title: '아침 명상',
          background_img: 'https://cdn.quasar.dev/img/parallax2.jpg',
          width: '100%',
          height: '27vh',
        },
        {
          id: 2,
          time: 11,
          title: '오후 명상',
          background_img: 'https://cdn.quasar.dev/img/parallax2.jpg',
          width: '100%',
          height: '27vh',
        },
        {
          id: 3,
          time: 11,
          title: '저녁 명상',
          background_img: 'https://cdn.quasar.dev/img/parallax2.jpg',
          width: '100%',
          height: '27vh',
        },
      ]
    }
  }
}
</script>

<style>

</style>