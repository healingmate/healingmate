<template>
  <div>
    <the-image-header class="relative" background-image="test.png" label="Contents"></the-image-header>
    <!-- TODO : 이전 페이지 이동 버튼 넣기-->
    <div class="q-pa-lg flex row wrap justify-between">
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
      contentsList: [
        {
          category: 'GIF',
          title: '힐링 콘텐츠',
          contents: 'https://media.giphy.com/media/iDJXOU0SWNCPEsa5EL/giphy.gif',
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/k3_tw44QsZQ",
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
          contents: "https://youtu.be/k3_tw44QsZQ",
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/k3_tw44QsZQ",
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: '힐링 콘텐츠',
          contents: 'https://media.giphy.com/media/iDJXOU0SWNCPEsa5EL/giphy.gif',
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
          contents: "https://youtu.be/k3_tw44QsZQ",
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠',
          contents: "https://youtu.be/k3_tw44QsZQ",
          bookmarked: false,
        },
        {
          category: 'GIF',
          title: '힐링 콘텐츠',
          contents: 'https://media.giphy.com/media/iDJXOU0SWNCPEsa5EL/giphy.gif',
          bookmarked: false,
        },
      ]  
    }
  }
}
</script>

<style>

</style>