<template>
  <div>
    <TheImageHeader :background-image="isNight ? 'night.jpg' : 'day.jpg'" :brightness="60" />
    
    <!-- 알람-->
    <the-notification 
      class="absolute-right q-pr-md q-mr-xl q-mt-md text-white"
      style="top: 0.7rem;"
    ></the-notification>

    <!-- 사용자 프로필 -->
    <base-avatar 
      class="absolute-right q-mr-lg q-mt-lg"
      :profile-image-path="this.$store.state.profileImage"
      :go-profile="true"
    ></base-avatar>

    <div class="absolute-left text-h6 text-bold text-white q-pa-sm q-ml-lg q-my-xl">
        <p class="q-mb-none">당신의 힐링 방식을</p>
        <p class="q-mb-none">공유해주세요 :)</p>
    </div>
    
    <ArticleCard
      v-for="(article, index) in articleList" 
      :key="index"
      :article="article"
      class="p-x-28"
      @on-delete="onDeleteArticle"
    />

    <div class="floating-action-button">
      <q-btn fab style="background: #244684; color: #ffffff;" round icon="post_add" @click="onPostButton"/>
    </div>


    <TheBottomNavigation
      :style="articleList.length ? '' : 'position: absolute;'" 
    />

  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseAvatar from '@/components/common/BaseAvatar';
import TheNotification from '@/components/common/TheNotification';
import ArticleCard from '@/components/article/ArticleCard'
import TheBottomNavigation from '@/components/common/TheBottomNavigationBar'
import { getArticles } from '@/api/article'


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
      // 게시글을 담을 배열
      articleList: [],
      // 몇개 씩 게시글 받아올지
      pagingSize: 5,
			// 게시글을 받아오는 동안 새로운 게시글이 등록되는 문제를 해결하기 위해 현재 불러온 페이지의 마지막 articleId 를 저장한다.
			pagingCursorId: 0,
      // 더 받아올 게시글이 있는지에 대한 유무
      isLast: false,
    }
	},
	// computed: {},
	// watch: {},
	created() {
    // 윈도우에 스크롤 이벤트 리스너를 달아 스크롤움직임을 감지한다.
    window.addEventListener('scroll', this.handleScroll);
    // 첫 페이지 로드시 한번은 데이터를 받아와야하기 때문에 한번 데이터를 받아온다.
    // 이 후엔 스크롤이 페이지 맨 아래로 내려갔을 때 마다 페이징된 데이터를 받아올 것임
    this.loadData()
  },
	// mounted() {},
	// updated() {},
	methods: {
    onPostButton() {
      this.$router.push({name: 'ArticleCreatePage'})
    },
    loadData() {      
      // 페이징된 데이터를 받아오는 함수
      // axios 요청을 하는 동안은 로딩창을 띄울 것임
      this.$q.loading.show();

      // axios로 데이터 요청
      getArticles(this.pagingSize, this.pagingCursorId)
      .then(res => {
        // 받아 온 데이터중 게시글에 관련된 데이터를 newData 변수에 할당하고(앞으로 몇 번 사용 할것이니까..)
        const newData = res.data.articleResponses
        // 현재 받아온 페이지가 마지막 페이지인지 확인하고
        this.isLast = res.data.isLast 
        // newData는 배열안에 여러 오브젝트가 있는 형태이기 때문에 destructuring 한 후에 우리가 사용할 articleList 데이터에 담는다
        this.articleList.push(...newData)
        // 현재 받아온 newData의 마지막 게시글을 이용하여 중간에 다른 사용자가 최신 게시물을 올려도 순서대로 게시물을 받올 수 있게 할것임
        this.pagingCursorId = newData[newData.length - 1].articleId
      })
      .catch(err => {
        console.log(err)
      })
      .finally(() => {
        // 로딩 끝!
        this.$q.loading.hide();
      })
    },
    handleScroll() {
      // 아직 페이징을 받아올 데이터가 남아있고 로딩중이 아닌 상태로 스크롤을 맨 밑에 가깝게 내렸을 때
      const scrollHeight = document.documentElement.scrollHeight;
      const scrollTop = document.documentElement.scrollTop;
      const clientHeight = document.documentElement.clientHeight;

      console.log('스크롤 하이', scrollHeight)
      console.log('스크롤 탑', scrollHeight)
      console.log('클라이언트 하이', scrollHeight)
      
      if (!this.$q.loading.isActive && scrollTop + clientHeight >= scrollHeight - 1 && !this.isLast) {
        this.$q.loading.show();
        console.log("지금")
        this.loadData()
      }
    },
    onDeleteArticle(article) {
      const index = this.articleList.indexOf(article)
      this.articleList.splice(index, 1)
    }
  },
}
</script>

<style scoped>
.floating-action-button {
  position: sticky;
  display: flex;
  bottom: 60px;
  z-index: 2;
  margin: 12px;
  justify-content: flex-end;
}

</style>