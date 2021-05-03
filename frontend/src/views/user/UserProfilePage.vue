<template>
  <div>
    <!-- TODO : font-size 조절 -->
    <the-image-header 
      class="relative" 
      background-image="contents-main.png"
      label="profile"
    ></the-image-header>
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
      class="text-subtitle2 text-weight-bold q-pt-xl text-center"
    >
      {{ user.username }}
    </div>
    <!-- 키워드 -->
    <div 
      class="inline-block absolute" 
      style="left: 50%; transform: translateX(-50%); max-width: 50vw;"
    >
      <base-keyword 
        v-for="(keyword, index) in user.keywordList" 
        :key="index" 
        :entity="keyword"
        @click.native="toggleKeyword(keyword)"
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
        style="width: 40vw; background: #244684; color: white;" 
        label="Post" 
        @click="selectButton"
      />
      <q-btn 
        v-else
        flat 
        class="text-caption text-weight-bold"
        style="width: 40vw; color: #244684" 
        label="Post" 
        @click="selectButton"
      /><q-btn 
        v-if="bookmarkButton"
        class="text-caption text-weight-bold"
        style="width: 40vw; background: #244684; color: white;" 
        label="Bookmark" 
        @click="selectButton"
      />
      <q-btn 
        v-else
        flat 
        class="text-caption text-weight-bold"
        style="width: 40vw; color: #244684" 
        label="Bookmark" 
        @click="selectButton"
      />
    </div>
    <!-- post -->
    <div v-if="postButton" class="q-mt-lg">
      <p class="text-center">작성한 post가 없습니다.</p>
    </div>
    <div v-if="bookmarkButton" class="q-mt-lg">
      <p class="text-center">저장한 북마크가 없습니다.</p>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseKeyword from '@/components/common/BaseKeyword';

export default {
  components: {
    TheImageHeader,
    BaseKeyword,
  },
  data() {
    return {
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
      postButton: true,
      bookmarkButton: false,
    }
  },
  methods: {
    toggleKeyword(keyword) {
      console.log(keyword)
      keyword.click = !keyword.click;
    },
    selectButton() {
      this.postButton = !this.postButton;
      this.bookmarkButton = !this.bookmarkButton;
    }
  }
}
</script>

<style>

</style>