<template>
  <div>
    <the-image-header 
      class="relative" 
      style="background-color: #5A3272; height: 17vh;" 
      background-image="meditation-contents-header.png" 
      label="Meditation"
    ></the-image-header>
    <div class="q-pa-lg">
      <!-- 추천 명상 part -->
      <div class="text-subtitle2 text-weight-bold q-mb-sm q-ml-xs">
        추천명상
      </div>
      <!-- TODO : dots true 지정 가능하도록 -->
      <article-carousel :number="1" class="q-mb-xl">
        <contents-meditation 
          v-for="(mainMeditation, index) in mainMeditationList" 
          :key="index"
          :entity="mainMeditation"
          class="q-mr-xs"
        >
        </contents-meditation>
      </article-carousel>
      <!-- 상황별 명상 -->
      <div class="text-subtitle2 text-weight-bold q-mb-sm q-ml-xs">
        상황별 명상
      </div>
      <article-carousel :number="2.1" class="q-mb-xl">
        <contents-meditation 
          v-for="(Meditation, index) in MeditationList" 
          :key="index"
          :entity="Meditation"
          class="q-mr-sm"
        >
        </contents-meditation>
      </article-carousel>
      <!-- 기분별 명상 -->
      <div class="text-subtitle2 text-weight-bold q-mb-sm q-ml-xs">
        기분별 명상
      </div>
      <article-carousel :number="2.1" class="q-mb-xl">
        <contents-meditation 
          v-for="(Meditation, index) in MeditationList" 
          :key="index"
          :entity="Meditation"
          class="q-mr-sm"
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
import { mainData } from '@/assets/data/MeditationMainContents.js';
import { data } from '@/assets/data/MeditationContents.js';

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
      mainMeditationList : mainData,
      MeditationList : data
    }
  }
}
</script>

<style>

</style>