<template>
  <div>
    <TheImageHeader :background-image="isNight ? 'night.jpg' : 'day.jpg'"/>
    <!-- 알람-->
    <!-- TODO : 사이즈 조절  -->
    <div class="absolute" style="top: 0; width: 100%">

      <div class="row justify-between">
        <div></div>
        <!-- 뒤로가기 버튼 들어갈곳 -->
        <div>
          <TheNotification  />
          <!-- 사용자 프로필 -->
          <BaseAvatar style="margin: 8px;" @click.native="goToProfile" />  
        </div>
      </div>
    </div>
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
      <q-btn fab style="background: #244684; color: #ffffff;" round icon="post_add"/>
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
	// methods: {},
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