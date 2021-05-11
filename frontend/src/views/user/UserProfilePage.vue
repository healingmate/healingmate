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
    <!-- 버튼 -->
    <div 
      class="flex justify-center q-pt-lg q-mt-md"
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
import ArticleCard from '@/components/article/ArticleCard.vue';
// import articleListPage from "@/assets/data/articleListDummy.json"
import ContentsCard from '@/components/healing-content/ContentsCard';
import TheGoBackButton from '@/components/common/TheGoBackButton';
import BaseKebabButton from '@/components/common/BaseKebabButton';
import BaseMenu from '@/components/common/BaseMenu';
import { getBookmarkedContents } from '@/api/healing-content';
import { getArticleList } from '@/api/user';
import { data } from '@/assets/data/HealingContents.js';
import { characterList } from '@/assets/data/CharacterList.js';

export default {
  components: {
    TheImageHeader,
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
      articleList: [],
      pagingSize: 5,
      pagingCursorId: 0,
      isLast: false,
    }
  },
  // watch: {
  //   bookmarkedList() {
  //     console.log('변화')
  //     // window.location.reload();
  //   }
  // },
  mounted() {
    const AfterKeywordList = this.keywordList.toString().split(',');
    this.keywordList = AfterKeywordList;
  },
  created() {
    // TODO : 글 작성 후 출력양식 맞추기
    window.addEventListener('scroll', this.handleScroll);
    this.loadData()
    // const cursorId = 0
    // const size = 20
    // getArticleList(cursorId, size)
    // .then((response) => {
    //   console.log(response)
    // })
    // .catch(err => {
    //   console.log(err.response)
    // })
    getBookmarkedContents() 
    .then((response) => {
      console.log(response)
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
    console.log(myCharacter)
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
      console.log('정보수정페이지로 이동')
      this.$router.push('/update-information');
    },
    goToUpdatePasswordPage() {
      console.log('비밀번호수정페이지로 이동')
      this.$router.push('/update-password');
    },
    loadData() {
      this.$q.loading.show();
      getArticleList(this.pagingCursorId, this.pagingSize)
      .then(res => {
        const newData = res.data.articleResponses
        this.isLast = res.data.isLast 
        this.articleList.push(...newData)
        this.pagingCursorId = newData[newData.length - 1].articleId
      })
      .catch(err => {
        console.log(err)
      })
      .finally(() => {
        this.$q.loading.hide();
      })
    },
    handleScroll() {
      if (!this.$q.loading.isActive && Math.round(document.documentElement.scrollTop) + window.innerHeight > document.documentElement.scrollHeight - 10 && !this.isLast) {
        this.loadData()
      }
    }
  },
}
</script>

<style>

</style>