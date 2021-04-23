<template>
  <div class="q-pa-lg">
    <contents-card></contents-card>
    <base-button></base-button>
    <contents-main></contents-main>
    <base-keyword></base-keyword>
    <contents-meditation></contents-meditation>
    <notification :entity="noti"></notification>
  </div>
</template>

<script>
import ContentsCard from '@/components/common/ContentsCard';
import BaseButton from '@/components/common/BaseButton.vue';
import ContentsMain from '@/components/common/ContentsMain';
import BaseKeyword from '@/components/common/BaseKeyword';
import ContentsMeditation from '@/components/common/ContentsMeditation';
import Notification from '@/components/common/Notification';

export default {
  components: {
    ContentsCard,
    BaseButton,
    ContentsMain,
    BaseKeyword,
    ContentsMeditation,
    Notification,
  },
  data() {
    return {
      noti: {
        username: '하이하이',
        date: '2021.05.03',
        profile_img: 'https://cdn.quasar.dev/img/avatar.png'
      }
    }
  }
}
</script>

<style>

</style>