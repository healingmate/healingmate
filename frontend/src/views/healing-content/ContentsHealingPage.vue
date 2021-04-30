<template>
  <div>
    <the-image-header 
      class="relative" 
      style="background-color: #F2BAB7; height: 17vh;" 
      background-image="healing-contents-header.png" 
      label="Contents"
    ></the-image-header>
    <!-- TODO : 이전 페이지 이동 버튼 넣기-->
    <!-- 콘텐츠 카테고리 버튼 배치 -->
    <q-btn 
      flat 
      class="inline-block text-caption text-weight-bold absolute"
      style="color: black; right: 28px;" 
      icon="tune" 
      :label="this.contentsCategory" 
    >
      <q-menu
        transition-show="flip-right"
        transition-hide="flip-left"
        auto-close
      >
        <q-list style="min-width: 100px">
          <q-item clickable @click="selectCategory(1)">
            <q-item-section>
              <q-icon name="apps" class="text-caption"> 전체</q-icon>
            </q-item-section>
          </q-item>
          <q-item clickable @click="selectCategory(2)">
            <q-item-section>
              <q-icon name="videocam" class="text-caption"> 유튜브</q-icon>
            </q-item-section>
          </q-item>
          <q-item clickable @click="selectCategory(3)">
            <q-item-section>
              <q-icon name="insert_photo" class="text-caption"> GIF</q-icon>
            </q-item-section>
          </q-item>
        </q-list>
      </q-menu>
    </q-btn>
    <div class="q-pa-lg flex justify-between q-mt-lg">
      <contents-card
        v-for="(contents, index) in contentsList" 
        :key="index" 
        :entity="contents"
        :contentsCategory="contentsCategory"
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
      contentsCategory: '전체',
      // json 파일 import 하기
      contents: [
        {
          category: 'GIF',
          title: 'Laughing',
          contents: 'https://media3.giphy.com/media/3oEjHI8WJv4x6UPDB6/200.webp',
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠1',
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
          title: '힐링 콘텐츠2',
          contents: "https://youtu.be/8YX57oN1814",
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠3',
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
          title: '힐링 콘텐츠4',
          contents: "https://youtu.be/WXkJJaCNshY",
          bookmarked: false,
        },
        {
          category: '유튜브',
          title: '힐링 콘텐츠5',
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
  },
  computed: {
    contentsList() {
      return this.contents.filter(contents => {
        if (this.contentsCategory === '전체') return true;
        if (this.contentsCategory === contents.category) return true;
        return false;
      });
    }
  },
  methods: {
    selectCategory(type) {
      if (type === 1) {
        this.contentsCategory = '전체'
      } else if (type === 2) {
        this.contentsCategory = '유튜브'
      } else {
        this.contentsCategory = 'GIF'
      }
    }
  }
}
</script>

<style>

</style>