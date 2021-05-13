<template>
  <div>
    <the-image-header 
      style="background-color: #F2BAB7;" 
      background-image="healing-contents-header.png" 
      label="Contents"
      :brightness="50"
    ></the-image-header>
    <!-- 이전 페이지 이동 -->
    <the-go-back-button
      :size="1.2"
    ></the-go-back-button>
    <!-- 콘텐츠 카테고리 버튼 배치 -->
    <q-btn 
      flat 
      class="inline-block text-caption text-weight-bold absolute"
      style="color: black; right: 28px;" 
      icon="tune" 
      :label="this.contentsCategory" 
    >
      <q-menu auto-close>
        <base-menu 
          @click.native="selectCategory(1)" 
          v-if="true" 
          icon="apps"
          text="전체"
          style="width: 120px;"
        ></base-menu>
        <base-menu 
          @click.native="selectCategory(2)" 
          v-if="true"
          icon="videocam" 
          text="유튜브"
          style="width: 120px;"
        ></base-menu>
        <base-menu 
          @click.native="selectCategory(3)" 
          v-if="true"
          icon="insert_photo" 
          text="GIF"
          style="width: 120px;"
        ></base-menu>
      </q-menu>
    </q-btn>
    <div class="q-pa-lg flex justify-between q-mt-lg">
      <contents-card
        v-for="(contents, index) in contentsList" 
        :key="index" 
        :entity="contents"
        :bookmarkedList="bookmarkedList"
        :contentsCategory="contentsCategory"
      >
      </contents-card>
    </div>  
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ContentsCard from '@/components/healing-content/ContentsCard';
import TheGoBackButton from '@/components/common/TheGoBackButton';
import BaseMenu from '@/components/common/BaseMenu';
import { data } from '@/assets/data/HealingContents.js';
import { getBookmarkedContents } from '@/api/healing-content';

export default {
  components: {
    TheImageHeader,
    ContentsCard,
    TheGoBackButton,
    BaseMenu,
  },
  data() {
    return {
      contentsCategory: '전체',
      contents: data,
      bookmarkedList: [],
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
  },
  created() {
    getBookmarkedContents() 
    .then((response) => {
      for (var i = 0; i < response.data.length; i++) {
        for (var j = 0; j < this.contents.length; j++) {
          if (this.contents[j].id === response.data[i]) {
            this.contents[j].bookmarked = true;
          }
        }
      }
    })
    .catch(err => {
      console.log(err.response)
    })
  }
}
</script>

<style>

</style>