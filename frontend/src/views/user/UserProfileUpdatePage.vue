<template>
  <div>
    <the-image-header 
      class="relative" 
      :background-image="isNight ? 'night.jpg' : 'day.jpg'"
      label="프로필 설정"
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
        :src="require(`@/assets/images/character/${ profile_image.name }.png`)"
        class="relative"
        spinner-color="white"
        width="6rem"
        height="6rem"
        style="border-radius: 10px; background-color: #000;"
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
          style="width: 70vw; height: 50vh; top: 22vh; left: 50%; transform: translateX(-50%);"
        >
          <q-img 
            :src="
              selectedCharacter.name
              ? require(`@/assets/images/character/${ selectedCharacter.name }.png`)
              : require('@/assets/images/character/RABBIT.png')
            "
          >
          </q-img>
        </q-card-section>
        <article-carousel 
          :number="3" 
          :dots="true"
          class="q-y-28 absolute q-mb-xl"
          style="width: 87vw; height: 60vh; top: 64vh; left: 50%; transform: translateX(-50%);"
        >
          <div 
            v-for="(character, index) in characterList" 
            :key="index"
            @click="selectCharacter(character)"
          >
            <q-img 
              :src="require(`@/assets/images/character/${ character.name }.png`)"
              width="6rem"
              height="6rem"
              style="border-radius: 10px; background-color: #000;">
            </q-img>
          </div>
        </article-carousel>
        <!-- 프로필 이미지 or 캐릭터 선택 완료 버튼 -->
        <div class="absolute-bottom p-x-28 q-mb-lg" @click="updateCharacter">
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
      class="p-x-28 q-mt-xl" 
      color="#244684" 
      label="닉네임" 
      :entity="nickname"
      @onInputValue="text => nickname = text"
      @onValidate="reference => nicknameReference = reference"
      :rules="[required(), minLength(2), maxLength(10), korean()]"
    >
    </base-text-input>
    <!-- 키워드 선택 -->
    <div class="p-x-28 q-mt-sm">
      <p style="color: #244684; font-size: 1rem;">키워드</p>
      <base-keyword 
        v-for="(keyword, index) in keywordList" 
        :key="index" 
        :entity="keyword"
        @click.native="toggleKeyword(keyword)"
      >
      </base-keyword>
    </div>
    <!-- 제출 버튼 -->
    <div class="absolute-bottom p-x-28 q-mb-lg"  @click="updateProfile">
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
import { validation } from '@/mixins/validation'
import { nicknameCheck } from '@/api/account';
import { keywordList } from '@/assets/data/KeywordList.json';
import { characterList } from '@/assets/data/CharacterList.json';

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
      nicknameReference: null,
      maximizedToggle: true,
      selectedKeyword: [],
      selectedCharacter: [],
      keywordList: keywordList,
      nickname: '',
      profile_image: {
        id: 1,
        name: 'RABBIT',
      },
      characterList: characterList,
    }
  },
  methods: {
    toggleKeyword(keyword) {
      keyword.click = !keyword.click;
      if (this.selectedKeyword.length < 3) {
        if (keyword.click) {
          this.selectedKeyword.push(keyword.keyword)
        } else {
          for (let i = 0; i < this.selectedKeyword.length; i++) {
            if (this.selectedKeyword[i] === keyword.keyword) {
              this.selectedKeyword.splice(i, 1)
            }
          }
        }
      } else {
        if (!keyword.click) {
          for (let i = 0; i < this.selectedKeyword.length; i++) {
            if (this.selectedKeyword[i] === keyword.keyword) {
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
    updateProfile() {
      if (!this.nicknameReference.validate()) {
				this.$q.notify({
					position: 'top',
          color: 'negative',
          message: this.nicknameReference.computedErrorMessage,
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
      // 사용자가 기존의 닉네임을 그대로 사용할 경우
      if (this.nickname === this.$store.state.nickname) {
        const userInformation = {
          'nickname': this.nickname,
          'profileImage': this.profile_image.name,
          'keywords': this.selectedKeyword,
        }
        this.$store.dispatch('updateUser', userInformation)
      } else {
        nicknameCheck(this.nickname)
        .then((res) => {
          if (res.data) {
            this.$q.notify({
              position: 'top',
              color: 'negative',
              message: '이미 사용중인 닉네임입니다.'
            }) 
          } else {
            const userInformation = {
              'nickname': this.nickname,
              'profileImage': this.profile_image.name,
              'keywords': this.selectedKeyword,
            }
            this.$store.dispatch('updateUser', userInformation)
          }
        })
        .catch(err => {
          console.log(err.response)
        })
      }
    },
    chooseCharacter() {
      this.open = true;
      this.selectedCharacter = this.profile_image
    },
    selectCharacter(character) {
      this.selectedCharacter = character
    },
    updateCharacter() {
      this.profile_image = this.selectedCharacter
      this.open = false;
    }
  },
  created() {
    const myCharacter = this.$store.state.profileImage
    for (var a = 0; a < characterList.length; a++) {
      if (characterList[a].name === myCharacter) {
        this.profile_image = characterList[a]
      }
    }
    this.nickname = this.$store.state.nickname;
    const BeforeKeywordList = this.$store.state.keyword;
    const AfterKeywordList = BeforeKeywordList.toString().split(',');
    this.selectedKeyword = AfterKeywordList;
    for (var i = 0; i < this.keywordList.length; i++ ){
      for (var j = 0; j < this.selectedKeyword.length; j++ ) {
        if (this.keywordList[i].keyword === this.selectedKeyword[j]) {
          this.keywordList[i].click = true;
        }
      }
    }
  }
}
</script>

<style>

</style>