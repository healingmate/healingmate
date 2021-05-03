<template>
  <div>
    <the-image-header 
      class="relative" 
      background-image="contents-main.png"
      label="profile setting"
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
    <!-- 닉네임 변경 -->
    <base-text-input class="p-y-28 q-mt-xl" color="#244684" label="닉네임" :rules="[required(), minLength(2), maxLength(10), korean()]">
    </base-text-input>
    <div class="p-y-28 q-mt-sm">
      <p style="color: #244684; font-size: 1rem;">키워드</p>
      <base-keyword 
        v-for="(keyword, index) in user.keywordList" 
        :key="index" 
        :entity="keyword"
        @click.native="toggleKeyword(keyword)"
      >
      </base-keyword>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseTextInput from '@/components/common/BaseTextInput';
import BaseKeyword from '@/components/common/BaseKeyword';
import { validation } from "@/mixins/validation"

export default {
  components: {
    TheImageHeader,
    BaseTextInput,
    BaseKeyword,
  },
  mixins: [validation],

  data() {
    return {
      selectedKeyword: [],
      user: {
        avatar: "https://www.gannett-cdn.com/-mm-/767d79353012d41372e77e6d13373453b5f6cd8d/c=0-111-4256-2511/local/-/media/USATODAY/USATODAY/2014/05/01//1398973646000-EMMA-STONE-252.JPG",
        username: '말랑말랑',
        keywordList: [
          {
            id: 1,
            keyword: '취업',
            click: false,
          }, 
          {
            id: 2,
            keyword: '가족',
            click: false,
          }, 
          {
            id: 3,
            keyword: '학업/진로',
            click: false,
          },
          {
            id: 4,
            keyword: '성격고민',
            click: false,
          },
          {
            id: 5,
            keyword: '직장',
            click: false,
          }, 
          {
            id: 6,
            keyword: '학교',
            click: false,
          },
          {
            id: 7,
            keyword: '대인관계',
            click: false,
          },
          {
            id: 8,
            keyword: '생활정보',
            click: false,
          }, 
        ],
      },
    }
  },
  methods: {
    toggleKeyword(keyword) {
      console.log(keyword)
      keyword.click = !keyword.click;
      if (this.selectedKeyword.length < 3) {
        if (keyword.click) {
          this.selectedKeyword.push(keyword)
        } else {
          for (let i = 0; i < this.selectedKeyword.length; i++) {
            if (this.selectedKeyword[i].id === keyword.id) {
              this.selectedKeyword.splice(i, 1)
            }
          }
        }
      } else {
        if (!keyword.click) {
          for (let i = 0; i < this.selectedKeyword.length; i++) {
            if (this.selectedKeyword[i].id === keyword.id) {
              this.selectedKeyword.splice(i, 1)
            }
          }
        } else {
          // TODO : alert 디자인
          alert('키워드는 최대 3개까지 선택할 수 있습니다.')
          keyword.click = !keyword.click
        }
      }
      console.log(this.selectedKeyword)
    },
  }
}
</script>

<style>

</style>