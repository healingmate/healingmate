<template>
  <div>
    <TheImageHeader :background-image="isNight ? 'night.jpg' : 'day.jpg'"/>
    <!-- 알람-->
    <!-- TODO : 사이즈 조절  -->
    <the-notification class="absolute-right q-mr-xl q-mt-md text-white"></the-notification>
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
      <q-btn color="primary" round icon="check"/>
    </div>

  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseAvatar from '@/components/common/BaseAvatar';
import TheNotification from '@/components/common/TheNotification';
import ArticleCard from "@/components/article/ArticleCard"
import articleListPage from "@/assets/data/articleListDummy.json"

export default {
	name: 'ArticleFeedPage',
	components: {
    TheImageHeader,
    BaseAvatar,
    TheNotification,
    ArticleCard,
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
  bottom: 40px;
  z-index: 2;
  margin: 12px;
  justify-content: flex-end;
}

</style>