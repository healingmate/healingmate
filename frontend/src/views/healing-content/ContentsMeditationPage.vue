<template>
  <div>
    <the-image-header class="relative" background-image="test.png" label="Meditation"></the-image-header>
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
          background_img: 'morning.jpg',
          explanation: '편안한 자세로 아침명상을 즐겨보세요:) 고요한 아침의 소리를 들으면 마음이 차분해지는 것을 느낄 수 있습니다.',
          width: '100%',
          height: '27vh',
        },
        {
          id: 2,
          time: 11,
          title: '오후 명상',
          background_img: 'afternoon.jpg',
          explanation: '나른한 오후, 명상을 통해 마음을 정리해보는 시간을 가져보세요. 차분한 오후에 어울리는 따뜻한 명상 음악 어떠신가요?',
          width: '100%',
          height: '27vh',
        },
        {
          id: 3,
          time: 11,
          title: '저녁 명상',
          background_img: 'night.jpg',
          explanation: '오늘 하루도 수고 많으셨어요:) 하루를 정리하며 명상 어떠신가요?',
          width: '100%',
          height: '27vh',
        },
      ],
      MeditationList : [
        {
          id: 1,
          time: 11,
          title: '아침 명상',
          background_img: 'morning.jpg',
          explanation: '편안한 자세로 아침명상을 즐겨보세요:) 고요한 아침의 소리를 들으면 마음이 차분해지는 것을 느낄 수 있습니다.',
          width: '100%',
          height: '18vh',
        },
        {
          id: 2,
          time: 11,
          title: '오후 명상',
          background_img: 'afternoon.jpg',
          explanation: '나른한 오후, 명상을 통해 마음을 정리해보는 시간을 가져보세요. 차분한 오후에 어울리는 따뜻한 명상 음악 어떠신가요?',
          width: '100%',
          height: '18vh',
        },
        {
          id: 3,
          time: 11,
          title: '저녁 명상',
          background_img: 'night.jpg',
          explanation: '오늘 하루도 수고 많으셨어요:) 하루를 정리하며 명상 어떠신가요?',
          width: '100%',
          height: '18vh',
        },
        {
          id: 1,
          time: 11,
          title: '아침 명상',
          background_img: 'morning.jpg',
          explanation: '편안한 자세로 아침명상을 즐겨보세요:) 고요한 아침의 소리를 들으면 마음이 차분해지는 것을 느낄 수 있습니다.',
          width: '100%',
          height: '18vh',
        },
        {
          id: 2,
          time: 11,
          title: '오후 명상',
          background_img: 'afternoon.jpg',
          explanation: '나른한 오후, 명상을 통해 마음을 정리해보는 시간을 가져보세요. 차분한 오후에 어울리는 따뜻한 명상 음악 어떠신가요?',
          width: '100%',
          height: '18vh',
        },
        {
          id: 3,
          time: 11,
          title: '저녁 명상',
          background_img: 'night.jpg',
          explanation: '오늘 하루도 수고 많으셨어요:) 하루를 정리하며 명상 어떠신가요?',
          width: '100%',
          height: '18vh',
        },
      ]
    }
  }
}
</script>

<style>

</style>