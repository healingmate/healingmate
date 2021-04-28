<template>
  <div class="q-pa-lg">
    <!-- TODO -->
    <!-- 상단 뒷배경 -->
    <!-- 알람 -->
    <!-- 프로필 이미지 -->
    <!-- 타이틀 -->
    <div class="text-h6 text-bold q-pa-sm q-mb-xl">
      <p class="q-mb-none">당신을 위한</p>
      <p class="q-mb-none">힐링콘텐츠 :)</p>
    </div>
    <contents-category 
      v-for="(item, index) in category" 
      :key="index" 
      :entity="item"
    ></contents-category>
  </div>
</template>

<script>
import ContentsCategory from '@/components/healing-content/ContentsCategory';
export default {
  components: {
    ContentsCategory,
  },
  data() {
    return {
      category: [
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
  }
}
</script>

<style>

</style>