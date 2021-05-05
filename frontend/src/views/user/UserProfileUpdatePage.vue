<template>
  <div>
    <the-image-header 
      class="relative" 
      :background-image="isNight ? 'night.jpg' : 'day.jpg'"
      label="profile setting"
      :brightness="60"
    ></the-image-header>
    <!-- 뒤로 가기 버튼 -->
    <the-go-back-button
      :size="1.2"
    ></the-go-back-button>
    <!-- 프로필(아바타) 이미지 -->
    <div 
      class="absolute"
      style="top: 17vh; left: 50%; transform: translateX(-50%);"
    >
      <q-img
        :src="user.avatar"
        class="relative"
        spinner-color="white"
        width="6rem"
        height="6rem"
        style="border-radius: 10px;"
      />
      <!-- 프로필 이미지 or 캐릭터 선택 버튼 -->
      <q-btn 
        round 
        text-color="indigo-9" 
        color="white"
        icon="edit" 
        class="absolute"
        style="top: 10vh; right: -3vw;"
        @click="chooseCharacter"
      />
    </div>
    <!-- 프로필 이미지 or 캐릭터 선택 페이지 -->
    <q-dialog 
      v-model="open" 
      :maximized="maximizedToggle"
      class="relative"
    >
      <q-card class="text-white" style="background-color: transparent;">
        <q-btn
          class="absolute" 
          style="top: 13vh; left: 50%; transform: translateX(-50%);" 
          round 
          color="white"
          text-color="black" 
          icon="close" 
          @click="open = false"
        />
        <q-img>

        </q-img>
        <q-card-section 
          class="absolute text-center" 
          style="width: 70vw; height: 50vh; top: 25vh; left: 50%; transform: translateX(-50%);"
        >
          <q-img 
            :src="
              selectedCharacter.image
              ? require(`@/assets/images/character/${ selectedCharacter.image }`)
              : require('@/assets/images/character/unnamed.png')
          ">
          </q-img>
        </q-card-section>
        <!-- TODO : index 넘기는 방법 -->
        <article-carousel 
          :number="3" 
          :pageChange="pageChange" 
          class="absolute q-mb-xl"
          style="width: 87vw; height: 60vh; top: 67vh; left: 50%; transform: translateX(-50%);"
        >
          <div 
            v-for="(character, index) in characterList" 
            :key="index"
            @click="selectCharacter(character)"
          >
            <q-img :src="require(`@/assets/images/character/${ character.image }`)">
            </q-img>
            <!-- <p class="q-mt-md text-weight-bold text-h6">{{ character.name }}</p> -->
          </div>
        </article-carousel>
        <!-- 프로필 이미지 or 캐릭터 선택 완료 버튼 -->
        <div class="absolute-bottom p-y-28 q-mb-lg" @click="updateCharacter">
          <base-button 
            back-ground-color="#244684" 
            text-color="white" 
            label="선택완료"
          ></base-button>
        </div>
      </q-card>
    </q-dialog>
    <!-- 닉네임 변경 -->
    <base-text-input 
      class="p-y-28 q-mt-xl" 
      color="#244684" 
      label="닉네임" 
      v-model="user.username"
      @onInputValue="inputNickname"
      :rules="[required(), minLength(2), maxLength(10), korean()]"
    >
    </base-text-input>
    <!-- 키워드 선택 -->
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
    <!-- 제출 버튼 -->
    <div class="absolute-bottom p-y-28 q-mb-lg"  @click="updateProfile">
      <base-button 
        back-ground-color="#244684" 
        text-color="white" 
        label="수정완료"
      ></base-button>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseTextInput from '@/components/common/BaseTextInput';
import BaseKeyword from '@/components/common/BaseKeyword';
import BaseButton from '@/components/common/BaseButton';
import TheGoBackButton from '@/components/common/TheGoBackButton';
import ArticleCarousel from '@/components/article/ArticleCarousel';
import { validation } from "@/mixins/validation"

export default {
  components: {
    TheImageHeader,
    BaseTextInput,
    BaseKeyword,
    BaseButton,
    TheGoBackButton,
    ArticleCarousel,
  },
  mixins: [validation],
  props: {
    isNight:{
      isNight: Boolean
    },
    number: {
			type: Number,
			default: 1
		},
  },
  data() {
    return {
      open: false,
      maximizedToggle: true,
      selectedKeyword: [],
      selectedCharacter: [],
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
      characterList: [
        {
          id: 1,
          name: '???',
          image: 'unnamed.png',
          profile: 'unnamed.png',
        },
        {
          id: 2,
          name: '금붕어',
          image: 'goldenfish.png',
          profile: 'goldenfish.png',
        },
        {
          id: 3,
          name: '펭귄',
          image: 'penguin.png',
          profile: 'penguin.png',
        },
        {
          id: 4,
          name: '사자',
          image: 'lion.png',
          profile: 'lion.png',
        },
        {
          id: 5,
          name: '소',
          image: 'cow.png',
          profile: 'cow.png',
        },
      ]
    }
  },
  computed: {
    pageChange(index) {
      // return index
      return console.log(index)
    }
  },
  methods: {
    toggleKeyword(keyword) {
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
          this.$q.notify({
            position: 'top',
            color: 'negative',
            message: '키워드는 최대 3개까지 선택할 수 있습니다.'
          })
          keyword.click = !keyword.click
        }
      }
    },
    inputNickname(input) {
      this.user.username = input
    },
    updateProfile() {
      if (!this.user.username) {
        this.$q.notify({
          position: 'top',
          color: 'negative',
          message: '닉네임을 입력해주세요.'
        })
        return
      } 
      if (this.selectedKeyword.length < 1) {
        this.$q.notify({
          position: 'top',
          color: 'negative',
          message: '키워드를 한 개 이상 선택해주세요'
        })
        return
      } 
      this.$router.push('/profile');
    },
    chooseCharacter() {
      this.open = true;
    },
    selectCharacter(character) {
      this.selectedCharacter = character
      console.log(this.selectedCharacter)
      // const selected = index.document.getElementsByClassName('active');
      // console.log(selected)
    },
    updateCharacter() {
      // 이미지 변경
      this.user.avatar = require(`@/assets/images/character/${ this.selectedCharacter.image }`)
      this.open = false;
    }
  }
}
</script>

<style>

</style>