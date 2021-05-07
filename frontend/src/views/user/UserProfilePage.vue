<template>
  <div>
    <!-- TODO : font-size 조절 -->
    <the-image-header 
      class="relative" 
      :background-image="isNight ? 'night.jpg' : 'day.jpg'"
      label="profile"
      :brightness="60"
    ></the-image-header>
    <!-- 이전 페이지 이동 -->
    <the-go-back-button
      :size="1.2"
    ></the-go-back-button>
    <!-- 수정 버튼 -->
    <!-- TODO : 프로필 사용자 본인만 해당 버튼을 확인할 수 있다. -->
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
    </base-kebab-button>
    <!-- 프로필(아바타) 이미지 -->
    <q-img
      :src="user.avatar"
      class="absolute"
      spinner-color="white"
      width="6rem"
      height="6rem"
      style="top: 17vh; left: 50%; transform: translateX(-50%); border-radius: 10px;"
    />
    <div
      class="text-subtitle2 text-weight-bold q-pt-lg text-center"
    >
      <!-- TODO : vuex에 저장된 이름으로 변경 -->
      <!-- {{ user.username }} -->
      <!-- {{ $store.state.nickname }} -->
      {{ this.cookies }}
    </div>
    <!-- 키워드 -->
    <div 
      v-if="user.keywordList.length === 0"
      class="inline-block absolute" 
      style="left: 50%; transform: translateX(-50%); max-width: 50vw;"
    >
      <base-keyword 
        :entity="noKeyword"
      >
      </base-keyword>
    </div>
    <div 
      v-else
      class="inline-block absolute" 
      style="left: 50%; transform: translateX(-50%); max-width: 50vw;"
    >
      <base-keyword 
        v-for="(keyword, index) in user.keywordList" 
        :key="index" 
        :entity="keyword"
      >
      </base-keyword>
    </div>
    <!-- 버튼 -->
    <div 
      class="flex justify-center q-pt-lg q-mt-xl"
    >
      <q-btn 
        v-if="postButton"
        class="text-caption text-weight-bold"
        style="width: 42vw; border-radius: 10px; background: #244684; color: white;" 
        label="Post" 
        @click="selectButton"
      />
      <q-btn 
        v-else
        flat 
        class="text-caption text-weight-bold"
        style="width: 42vw; color: #244684" 
        label="Post" 
        @click="selectButton"
      /><q-btn 
        v-if="bookmarkButton"
        class="text-caption text-weight-bold"
        style="width: 42vw; border-radius: 10px; background: #244684; color: white;" 
        label="Bookmark" 
        @click="selectButton"
      />
      <q-btn 
        v-else
        flat 
        class="text-caption text-weight-bold"
        style="width: 42vw; color: #244684" 
        label="Bookmark" 
        @click="selectButton"
      />
    </div>
    <!-- post -->
    <div v-if="postButton" class="q-mt-lg">
      <p v-if="articleList.length === 0" class="text-center">작성한 post가 없습니다.</p>
      <article-card
        v-else
        class="p-y-28 q-py-sm"
        v-for="(article, index) in articleList" 
        :key="index"
        :article="article"
      >
      </article-card>
    </div>
    <!-- bookmark -->
    <div v-if="bookmarkButton" class="q-mt-lg">
      <p v-if="bookmarkedList.length === 0" class="text-center">저장한 북마크가 없습니다.</p>
      <div class="p-y-28 flex justify-between q-mt-lg">
        <contents-card
          v-for="(contents, index) in bookmarkedList" 
          :key="index" 
          :entity="contents"
        >
        </contents-card>
    </div>  
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseKeyword from '@/components/common/BaseKeyword';
import ArticleCard from '@/components/article/ArticleCard.vue';
import articleListPage from "@/assets/data/articleListDummy.json"
import ContentsCard from '@/components/healing-content/ContentsCard';
import TheGoBackButton from '@/components/common/TheGoBackButton';
import BaseKebabButton from '@/components/common/BaseKebabButton';
import BaseMenu from '@/components/common/BaseMenu';
import { getBookmarkedContents } from '@/api/healing-content';
import { data } from '@/assets/data/HealingContents.js';

export default {
  components: {
    TheImageHeader,
    BaseKeyword,
    ArticleCard,
    ContentsCard,
    TheGoBackButton,
    BaseKebabButton,
    BaseMenu,
  },
  props: {
    isNight:{
      isNight: Boolean
    }
  },
  data() {
    return {
      // TODO : 백엔드 api 연결
      cookies: '',
      user: {
        avatar: "https://www.gannett-cdn.com/-mm-/767d79353012d41372e77e6d13373453b5f6cd8d/c=0-111-4256-2511/local/-/media/USATODAY/USATODAY/2014/05/01//1398973646000-EMMA-STONE-252.JPG",
        username: '말랑말랑',
        keywordList: [
          {
            keyword: '취업',
            click: false,
          }, 
          {
            keyword: '생활',
            click: false,
          }
        ],
      },
      noKeyword: {
        keyword: '선택한 키워드가 없어요',
        click: false,
      },
      postButton: true,
      bookmarkButton: false,
      articleList: articleListPage.content,
      contentList: data,
      bookmarkedList: []
    }
  },
  // watch: {
  //   bookmarkedList() {
  //     console.log('변화')
  //     // window.location.reload();
  //   }
  // },
  methods: {
    // toggleKeyword(keyword) {
    //   console.log(keyword)
    //   keyword.click = !keyword.click;
    // },
    selectButton() {
      this.postButton = !this.postButton;
      this.bookmarkButton = !this.bookmarkButton;
    },
    goToUpdateInfoPage() {
      console.log('정보수정페이지로 이동')
      this.$router.push('/update-information');
    },
    goToUpdatePasswordPage() {
      console.log('비밀번호수정페이지로 이동')
      this.$router.push('/update-password');
    }
  },
  created() {
    getBookmarkedContents() 
    .then((response) => {
      console.log(response)
      for (var i = 0; i < response.data.length; i++) {
        for (var j = 0; j < this.contentList.length; j++) {
          if (this.contentList[j].id === response.data[i].id) {
            this.contentList[j].bookmarked = true;
            this.bookmarkedList.push(this.contentList[j]);
          }
        }
      }
    })
    .catch(err => {
      console.log(err.response)
    })
    this.cookies = this.$q.cookies.get('additional-information')
    console.log(this.cookies)
    // console.log(value)
    // this.cookies = value

  }
}
</script>

<style>

</style>