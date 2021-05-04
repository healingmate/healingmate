<template>
  <div>
    <the-image-header 
      style="background-color: #F2BAB7;" 
      background-image="healing-contents-header.png" 
      label="Contents"
      :brightness="50"
    ></the-image-header>
    <!-- 이전 페이지 이동 -->
    <the-go-back-button></the-go-back-button>
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
import TheGoBackButton from '@/components/common/TheGoBackButton';
import { data } from '@/assets/data/HealingContents.js';

export default {
  components: {
    TheImageHeader,
    ContentsCard,
    TheGoBackButton,
  },
  data() {
    return {
      contentsCategory: '전체',
      contents: data,
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