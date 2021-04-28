<template>
  <div class="q-pa-lg">
    <contents-card :entity="contents"></contents-card>
    <bamboo-card-button :entity="button"></bamboo-card-button>
    <contents-button :entity="asmr"></contents-button>
    <contents-category :entity="category"></contents-category>
    <base-keyword :entity="keyword"></base-keyword>
    <contents-meditation :entity="meditation"></contents-meditation>
    <account-notification :entity="noti"></account-notification>
  </div>
</template>

<script>
import ContentsCard from '@/components/healing-content/ContentsCard';
import BambooCardButton from '@/components/bamboo/BambooCardButton';
import ContentsButton from '@/components/healing-content/ContentsButton';
import ContentsCategory from '@/components/healing-content/ContentsCategory';
import BaseKeyword from '@/components/common/BaseKeyword';
import ContentsMeditation from '@/components/healing-content/ContentsMeditation';
import AccountNotification from '@/components/account/AccountNotification';

export default {
  components: {
    ContentsCard,
    BambooCardButton,
    ContentsButton,
    ContentsCategory,
    BaseKeyword,
    ContentsMeditation,
    AccountNotification,
  },
  data() {
    return {
      contents: {
        category: '유튜브',
        title: '힐링 콘텐츠',
        // contents: 'https://media.giphy.com/media/iDJXOU0SWNCPEsa5EL/giphy.gif',
        contents: "https://youtu.be/k3_tw44QsZQ",
        bookmarked: false,
      },
      button: {
        icon: '💻',
        title: '취업',
        size: '20px',
        color: '#55A822',
        width: '40vw',
        height: '15vh',
      },
      asmr: {
        icon: '🌿',
        title: '숲소리',
        font_color: 'white',
        color: '#2F6673',
        width: '40vw',
        height: '25vh',
      },
      category:{
        address: 'contents',
        title: 'Contents',
        img: 'contents.png',
        color: '#F2BAB7',
        font_color: 'black'
      },
      keyword: {
        keyword: '취업',
        click: false,
      },
      meditation: {
        time: 11,
        title: '아침 명상',
        background_img: 'https://cdn.quasar.dev/img/parallax2.jpg',
        width: '100%',
        height: '27vh',
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