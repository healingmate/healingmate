<template>
  <div class="q-pa-lg">
    <contents-card :entity="contents"></contents-card>
    <base-button :entity="button"></base-button>
    <contents-category :entity="category"></contents-category>
    <base-keyword :entity="keyword"></base-keyword>
    <contents-meditation :entity="meditation"></contents-meditation>
    <notification :entity="noti"></notification>
  </div>
</template>

<script>
import ContentsCard from '@/components/common/ContentsCard';
import BaseButton from '@/components/common/BaseButton.vue';
import ContentsCategory from '@/components/common/ContentsCategory';
import BaseKeyword from '@/components/common/BaseKeyword';
import ContentsMeditation from '@/components/common/ContentsMeditation';
import Notification from '@/components/common/Notification';

export default {
  components: {
    ContentsCard,
    BaseButton,
    ContentsCategory,
    BaseKeyword,
    ContentsMeditation,
    Notification,
  },
  data() {
    return {
      contents: {
        category: '유튜브',
        title: '힐링 콘텐츠',
        youtube: 'https://www.youtube.com/embed/k3_tw44QsZQ?rel=0',
        bookmarked: false,
      },
      button: {
        icon: '💻',
        title: '취업',
        size: '22px',
        color: 'green-6'
      },
      category: {
        title: 'Contents',
        img: 'https://cdn.quasar.dev/img/parallax2.jpg',
        color: 'blue'
      },
      keyword: {
        keyword: '취업'
      },
      meditation: {
        time: 11,
        title: '아침 명상',
        background_img: 'https://cdn.quasar.dev/img/parallax2.jpg',
      },
      noti: {
        username: '하이하이',
        date: '2021.05.03',
        profile_img: 'https://cdn.quasar.dev/img/avatar.png',
      }
    }
  }
}
</script>

<style>

</style>