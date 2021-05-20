<template>
  <div>
    <the-image-header 
      class="relative" 
      :background-image="isNight ? 'night.jpg' : 'day.jpg'"
      label="프로필"
      :brightness="60"
    ></the-image-header>
    <!-- 이전 페이지 이동 -->
    <the-go-back-button
      :size="1.2"
    ></the-go-back-button>
    <!-- 수정 버튼 -->
    <base-kebab-button
      class="absolute"
      :size="0.8"
      style="top: 2vh; right: 15px; color: white;"
    >
      <base-menu 
        @click.native="goToUpdateInfoPage" 
        v-if="true" 
        icon="tune"
        text="기본정보수정"
        style="width: 140px;"
      ></base-menu>
      <base-menu 
        @click.native="goToUpdatePasswordPage" 
        v-if="true"
        icon="lock" 
        text="비밀번호변경"
        style="width: 140px;"
      ></base-menu>
      <base-menu 
        @click.native="logout" 
        v-if="true && this.$store.state.accessToken"
        icon="logout" 
        text="로그아웃"
        style="width: 140px;"
      ></base-menu>
    </base-kebab-button>
    <!-- 프로필(아바타) 이미지 -->
    <q-img
      :src="require(`@/assets/images/character/${ profile_image.name }.png`)"
      class="absolute"
      spinner-color="white"
      width="6rem"
      height="6rem"
      style="top: 17vh; left: 50%; transform: translateX(-50%); border-radius: 10px; background-color: #000;"
    />
    <div
      class="text-subtitle2 text-weight-bold q-pt-lg q-mt-sm text-center"
    >
      {{ nickname }}
    </div>
    <!-- 키워드 -->
    <div 
      v-if="keywordList.length === 0"
      class="q-mt-sm"
      style="text-align: center;"
    >
      <q-badge 
        class="cursor-pointer text-caption text-bold q-px-sm q-mr-sm" 
        outline 
        color="indigo-9" 
        text-color="white"
        :label="'선택한 키워드가 없어요'"
      />
    </div>
    <div 
      v-else 
      class="q-mt-sm"
      style="text-align: center;"
    >
      <q-badge 
        v-for="(keyword, index) in keywordList" :key="index"
        class="cursor-pointer text-caption text-bold q-px-sm q-mr-sm" 
        outline 
        color="indigo-9" 
        text-color="white"
        :label="'# ' + keyword"
      />
    </div>
    <!-- 말랑이 추천 수 확인하기 -->
    <!-- <user-recommend></user-recommend> -->
    <!-- 버튼 -->
    <div 
      class="flex justify-center q-pt-lg q-mt-sm"
    >
      <q-btn 
        v-if="postButton"
        class="text-caption text-weight-bold"
        style="width: 42vw; border-radius: 10px; background: #244684; color: white;" 
        label="게시글" 
        @click="selectButton"
      />
      <q-btn 
        v-else
        flat 
        class="text-caption text-weight-bold"
        style="width: 42vw; color: #244684" 
        label="게시글" 
        @click="selectButton"
      /><q-btn 
        v-if="bookmarkButton"
        class="text-caption text-weight-bold"
        style="width: 42vw; border-radius: 10px; background: #244684; color: white;" 
        label="북마크" 
        @click="selectButton"
      />
      <q-btn 
        v-else
        flat 
        class="text-caption text-weight-bold"
        style="width: 42vw; color: #244684" 
        label="북마크" 
        @click="selectButton"
      />
    </div>
    <!-- post -->
    <div v-if="postButton" class="q-mt-lg">
      <p v-if="articleList.length === 0" class="text-center">작성한 post가 없습니다.</p>
      <article-card
        v-else
        class="p-x-28 q-py-sm"
        v-for="(article, index) in articleList" 
        :key="index"
        :article="article"
      >
      </article-card>
    </div>
    <!-- bookmark -->
    <div v-if="bookmarkButton" class="q-mt-lg">
      <p v-if="bookmarkedList.length === 0" class="text-center">저장한 북마크가 없습니다.</p>
      <div class="p-x-28 flex justify-between q-mt-lg">
        <contents-card
          v-for="(contents, index) in bookmarkedList" 
          :key="index" 
          :entity="contents"
          @checkBookmarkList="checkBookmark"
        >
        </contents-card>
    </div>  
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ArticleCard from '@/components/article/ArticleCard.vue';
import ContentsCard from '@/components/healing-content/ContentsCard';
import TheGoBackButton from '@/components/common/TheGoBackButton';
import BaseKebabButton from '@/components/common/BaseKebabButton';
import BaseMenu from '@/components/common/BaseMenu';
import { getBookmarkedContents } from '@/api/healing-content';
import { getArticleList } from '@/api/user';
import { data } from '@/assets/data/HealingContents.json';
import { characterList } from '@/assets/data/CharacterList.json';
// import UserRecommend from '@/components/user/UserRecommend';

export default {
  components: {
    TheImageHeader,
    ArticleCard,
    ContentsCard,
    TheGoBackButton,
    BaseKebabButton,
    BaseMenu,
    // UserRecommend
  },
  props: {
    
    isNight:{
      isNight: Boolean
    }
  },
  data() {
    return {
      nickname: this.$store.state.nickname,
      keywordList: '',
      profile_image: {
        id: 1,
        name: 'RABBIT',
      },
      noKeyword: {
        keyword: '선택한 키워드가 없어요',
        click: false,
      },
      postButton: true,
      contentList: data,
      bookmarkButton: false,
      bookmarkedList: [],
      newBookmarkList: [],
      articleList: [],
      pagingSize: 5,
      pagingCursorId: 0,
      isLast: false,
      isLoading: false,
      value: 70,
      characterList: characterList,
    }
  },
  watch: {
    newBookmarkList() {
      // 사용자가 북마크를 해제하여 리스트에 변화가 생겼을 경우
      this.bookmarkedList = [];
      for (var i = 0; i < this.newBookmarkList.length; i++) {
        for (var j = 0; j < this.contentList.length; j++) {
          if (this.contentList[j].id === this.newBookmarkList[i]) {
            this.contentList[j].bookmarked = true;
            this.bookmarkedList.push(this.contentList[j]);
          }
        }
      }
    }
  },
  mounted() {
    const AfterKeywordList = this.keywordList.toString().split(',');
    this.keywordList = AfterKeywordList;
  },
  created() {
    window.addEventListener('scroll', this.handleScroll);
    this.loadData()
    getBookmarkedContents() 
    .then((response) => {
      for (var i = 0; i < response.data.length; i++) {
        for (var j = 0; j < this.contentList.length; j++) {
          if (this.contentList[j].id === response.data[i]) {
            this.contentList[j].bookmarked = true;
            this.bookmarkedList.push(this.contentList[j]);
          }
        }
      }
    })
    .catch(err => {
      console.log(err.response)
    })
    this.keywordList = this.$store.state.keyword;
    const myCharacter = this.$store.state.profileImage
    // console.log(myCharacter)
    for (var a = 0; a < characterList.length; a++) {
      if (characterList[a].name === myCharacter) {
        this.profile_image = characterList[a]
      }
    }
  },
  methods: {
    selectButton() {
      this.postButton = !this.postButton;
      this.bookmarkButton = !this.bookmarkButton;
    },
    goToUpdateInfoPage() {
      this.$router.push('/update-information');
    },
    goToUpdatePasswordPage() {
      this.$router.push('/update-password');
    },
    logout() {
      this.$store.dispatch('removeToken')
    },
    loadData() {
      this.isLoading = !this.isLoading
      this.$q.loading.show();

      getArticleList(this.pagingCursorId, this.pagingSize)
      .then(response => {
        const newData = response.data.articleResponses
        this.isLast = response.data.isLast 
        this.articleList.push(...newData)
        this.pagingCursorId = newData[newData.length - 1].articleId
      })
      .catch(err => {
        console.log(err)
      })
      .finally(() => {
        this.$q.loading.hide();
        this.isLoading = !this.isLoading
      })
    },
    handleScroll() {
      var scrollDepth = ((window.scrollY + window.innerHeight)/document.body.scrollHeight)
      
      if (!this.isLoading && scrollDepth > 0.97 && !this.isLast) {
        this.loadData()
      }
    },
    checkBookmark(bookmark) {
      this.newBookmarkList = bookmark;
    }
  },
}
</script>

<style>

</style>