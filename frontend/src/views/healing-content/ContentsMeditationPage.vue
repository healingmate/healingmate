<template>
  <div>
    <the-image-header 
      style="background-color: #5A3272;" 
      background-image="meditation-contents-header.png" 
      label="Meditation"
      :brightness="50"
    ></the-image-header>
    <!-- 이전 페이지 이동 -->
    <the-go-back-button
      :size="1.2"
    ></the-go-back-button>
    <div class="q-pa-lg">
      <!-- 추천 명상 part -->
      <div class="text-subtitle2 text-weight-bold q-mb-sm q-ml-xs">
        추천명상
      </div>
      <article-carousel :number="1" :dots="true" class="q-mb-xl">
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
      <article-carousel :number="2" class="q-mb-xl">
        <contents-meditation 
          v-for="(Meditation, index) in MeditationSubOneList" 
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
      <article-carousel :number="2" class="q-mb-xl">
        <contents-meditation 
          v-for="(Meditation, index) in MeditationSubTwoList" 
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
import TheGoBackButton from '@/components/common/TheGoBackButton';
import { mainData } from '@/assets/data/MeditationMainContents.js';
import { subOneData } from '@/assets/data/MeditationSubOneContents.js';
import { subTwoData } from '@/assets/data/MeditationSubTwoContents.js';

export default {
  components: {
    TheImageHeader,
    ContentsMeditation,
    ArticleCarousel,
    TheGoBackButton,
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
      MeditationSubOneList : subOneData,
      MeditationSubTwoList : subTwoData
    }
  }
}
</script>

<style>

</style>