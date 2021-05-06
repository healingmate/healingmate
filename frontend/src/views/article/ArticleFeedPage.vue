<template>
  <div>
    <TheImageHeader :background-image="isNight ? 'night.jpg' : 'day.jpg'"/>
    
    <!-- 알람-->
    <the-notification 
      class="absolute-right q-pr-md q-mr-xl q-mt-md text-white"
      style="top: 0.7rem;"
    ></the-notification>

    <!-- 사용자 프로필 -->
    <base-avatar 
      class="absolute-right q-mr-lg q-mt-lg"
      @click.native="goToProfile"
    ></base-avatar>

    <div class="absolute-left text-h6 text-bold text-white q-pa-sm q-ml-lg q-my-xl">
        <p class="q-mb-none">당신의 힐링 방식을</p>
        <p class="q-mb-none">공유해주세요 :)</p>
    </div>
    
    <ArticleCard
      v-for="(article, index) in articleList" 
      :key="index"
      :article="article"
    />

    <div class="row justify-right floating-action-button">
      <q-btn fab style="background: #244684; color: #ffffff;" round icon="post_add" @click="onPostButton"/>
    </div>


    <TheBottomNavigation />

  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseAvatar from '@/components/common/BaseAvatar';
import TheNotification from '@/components/common/TheNotification';
import ArticleCard from '@/components/article/ArticleCard'
import TheBottomNavigation from '@/components/common/TheBottomNavigationBar'

import articleListPage from "@/assets/data/articleListDummy.json"

export default {
	name: 'ArticleFeedPage',
	components: {
    TheImageHeader,
    BaseAvatar,
    TheNotification,
    ArticleCard,
    TheBottomNavigation,
  },
	// filters: {},
  // mixins: [],
	props: {
    isNight:{
      isNight: Boolean
    }
  },
	data() {
    return {
      articleList: articleListPage.content,
    }
	},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
    onPostButton() {
      this.$router.push({name: 'ArticleCreatePage'})
    }
  },
}
</script>

<style scoped>
.floating-action-button {
  position: sticky;
  bottom: 60px;
  z-index: 2;
  margin: 12px;
  justify-content: flex-end;
}

</style>