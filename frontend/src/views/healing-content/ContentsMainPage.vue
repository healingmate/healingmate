<template>
  <div>
    <!-- 상단 뒷배경 -->
    <the-image-header 
      class="relative" 
      background-image="contents-main.png"
      brightness="60"
    ></the-image-header>
    <!-- 알람-->
    <!-- TODO : 사이즈 조절  -->
    <the-notification class="absolute-right q-mr-xl q-mt-md text-white"></the-notification>
    <!-- 사용자 프로필 -->
    <base-avatar 
      class="absolute-right q-mr-lg q-mt-lg"
      @click.native="goToProfile"
    ></base-avatar>
    <div class="q-pa-lg">
      <!-- 타이틀 -->
      <div class="absolute-left text-h6 text-bold text-white q-pa-sm q-ml-lg q-my-xl">
        <p class="q-mb-none">당신을 위한</p>
        <p class="q-mb-none">힐링콘텐츠 :)</p>
      </div>
      <!-- 카테고리 콘텐츠 -->
      <contents-category 
        v-for="(category, index) in categoryList" 
        :key="index" 
        :entity="category"
      ></contents-category>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ContentsCategory from '@/components/healing-content/ContentsCategory';
import BaseAvatar from '@/components/common/BaseAvatar';
import TheNotification from '@/components/common/TheNotification';
export default {
  components: {
    TheImageHeader,
    ContentsCategory,
    BaseAvatar,
    TheNotification,
  },
  data() {
    return {
      categoryList: [
        {
          address: 'contents',
          title: 'Contents',
          img: 'contents.png',
          color: '#F2BAB7',
          font_color: 'black'
        },
        {
          address: 'asmr',
          title: 'ASMR',
          img: 'asmr.png',
          color: '#00295A',
          font_color: 'white'

        },
        {
          address: 'meditation',
          title: 'Meditaion',
          img: 'meditation.png',
          color: '#5A3272',
          font_color: 'white'
        },
      ]
    }
  },
  methods: {
    goToProfile() {
      console.log('프로필로 이동')
      this.$router.push('/profile');
    }
  }
}
</script>

<style>

</style>