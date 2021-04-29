<template>
  <div>
    <the-image-header class="relative" background-image="test.png" label="Contents"></the-image-header>
    <!-- TODO : 이전 페이지 이동 버튼 넣기-->
    <!-- TODO : 전체 버튼 배치 -->
    <div class="inline-block absolute-right q-mr-lg q-pa-none cursor-pointer" style="margin-top: 6rem">
      <q-icon name="tune"></q-icon>
      <p class="inline-block q-ml-sm text-caption text-bold">전체</p>
    </div>
    <div class="q-pa-lg flex justify-between q-ma-none">
      <contents-card
        v-for="(contents, index) in contentsList" 
        :key="index" 
        :entity="contents"
      >
      </contents-card>
    </div>  
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ContentsCard from '@/components/healing-content/ContentsCard';
export default {
  components: {
    TheImageHeader,
    ContentsCard,
  },
  data() {
    return {
      // json 파일 import 하기
      contentsList: [
        {
          category: 'GIF',
          title: 'Laughing',
          contents: 'https://media3.giphy.com/media/3oEjHI8WJv4x6UPDB6/200.webp',
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/77jy6yVLSH8",
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: 'Laughing',
          contents: 'https://media3.giphy.com/media/3EjqRNFJmn0C4/200w.webp',
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/8YX57oN1814",
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/b-amjUifQqw",
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: 'Smile',
          contents: 'https://media.giphy.com/media/QLvRBqfLXCphu/giphy.gif',
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: '힐링 콘텐츠',
          contents: 'https://media.giphy.com/media/iDJXOU0SWNCPEsa5EL/giphy.gif',
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/WXkJJaCNshY",
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/3AQ2f2A6R4s",
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: '힐링 콘텐츠',
          contents: 'https://media.giphy.com/media/10t57cXgo7x5kI/giphy.gif',
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: 'Love U',
          contents: 'https://media.giphy.com/media/jRlP4zbERYW5HoCLvX/giphy.gif',
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: 'CAT',
          contents: 'https://media.giphy.com/media/GeimqsH0TLDt4tScGw/giphy.gif',
          bookmarked: false,
        },
      ]  
    }
  }
}
</script>

<style>

</style>