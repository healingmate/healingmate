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
        :src="require(`@/assets/images/character/${ user.avatar.image }`)"
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
          style="width: 70vw; height: 50vh; top: 23vh; left: 50%; transform: translateX(-50%);"
        >
          <q-img 
            :src="
              selectedCharacter.image
              ? require(`@/assets/images/character/${ selectedCharacter.image }`)
              : require('@/assets/images/character/unnamed.png')
            "
          >
          </q-img>
        </q-card-section>
        <!-- TODO : nav or dots 표시 필요 -->
        <article-carousel 
          :number="3" 
          class="q-y-28 absolute q-mb-xl"
          style="width: 87vw; height: 60vh; top: 67vh; left: 50%; transform: translateX(-50%);"
        >
          <div 
            v-for="(character, index) in characterList" 
            :key="index"
            @click="selectCharacter(character)"
          >
            <q-img 
              :src="require(`@/assets/images/character/${ character.image }`)"
              width="6rem"
              height="6rem"
              style="border-radius: 10px; background-color: #000;">
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
      :entity="user.username"
      @onInputValue="text => user.username = text"
      @onValidate="reference => nicknameReference = reference"
      :rules="[required(), minLength(2), maxLength(10), korean()]"
    >
    </base-text-input>
    <!-- 키워드 선택 -->
    <div class="p-y-28 q-mt-sm">
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
import { validation } from '@/mixins/validation'
import { nicknameCheck, modifyAccount } from '@/api/account';
import { Notify } from 'quasar'
// import { saveUserNicknameToCookie, saveUserKeywordsToCookie } from '@/utils/cookies';
import { keywordList } from '@/assets/data/KeywordList.js';
import { characterList } from '@/assets/data/CharacterList.js';

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
      user: {
        avatar: {
          id: 0,
          image: 'unnamed.png'
        },
        username: '',
      },
      characterList: characterList,
    }
  },
  methods: {
    toggleKeyword(keyword) {
      keyword.click = !keyword.click;
      console.log(this.selectedKeyword)
      console.log(keyword.keyword)
      if (this.selectedKeyword.length < 3) {
        if (keyword.click) {
          this.selectedKeyword.push(keyword.keyword)
          // console.log(this.selectedKeyword)
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
      nicknameCheck(this.user.username)
      .then((res) => {
        if (res.data) {
          this.$q.notify({
            position: 'top',
            color: 'negative',
            message: '이미 사용중인 닉네임입니다.'
          }) 
        } else {
          const param = {
            'nickname': this.user.username,
            'profileImage': this.user.avatar.name,
            'keywords': this.selectedKeyword,
          }
          modifyAccount(param) 
          .then(() => {
            // TODO : 새로운 cookie 저장 방식에 맞춰서 변경 하기  
            // saveUserNicknameToCookie(this.user.username);
            // saveUserKeywordsToCookie(this.selectedKeyword);
            Notify.create({
              position: 'top',
              color: 'primary',
              message: '프로필이 수정되었습니다.'
            })
            location.reload();
            // this.$router.push('/profile');
          })
          .catch(err => {
            console.log(err.response)
          })
        }
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    chooseCharacter() {
      this.open = true;
      this.selectedCharacter = this.user.avatar
    },
    selectCharacter(character) {
      this.selectedCharacter = character
    },
    updateCharacter() {
      this.user.avatar = this.selectedCharacter
      this.open = false;
    }
  },
  created() {
    this.user.username = this.$store.state.nickname;

    this.selectedKeyword = this.$store.state.keyword;
    console.log(this.selectedKeyword)
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