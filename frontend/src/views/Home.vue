<template>
  <div class="home">
    <h3>컴포넌트 화면</h3>
    <q-separator />
    <h4>이미지 헤더</h4>
    <TheImageHeader background-image="night.png" color="#131034" />
    <TheImageHeader background-image="test.png" label="Meditation" />
    <q-separator />
    <h4>아바타</h4>
    <BaseAvatar />
    <BaseAvatar :size=5 />
    <BaseAvatar :size=7 profile-image-path="https://i.pinimg.com/564x/3b/ab/dc/3babdc3f9e2c7bed5d0548f9f818db4e.jpg"/>
    <q-separator />
    <h4>프로필 카드</h4>
    <BaseProfileCard nickname="하이헬로우" caption="#가족 #학교" :is-login-user="true"/>
    <BaseProfileCard nickname="헬로우못해" caption="#학교" />
    <BaseProfileCard nickname="헬로우못해" caption="2021.04.27" :is-notification="true" />
    <q-separator />
    <h4>텍스트 인풋</h4>
    <BaseTextInput color="#545FD6" label="아이디" :rules="[required(), minLength(5), maxLength(16), alphabetNumber()]"/>
    <BaseTextInput color="#545FD6" label="닉네임" :rules="[required(), minLength(2), maxLength(10), korean()]"/>
    <BaseTextInput color="#545FD6" label="패스워드" type="password" :rules="[required(), minLength(8), maxLength(16)]"/>
    <BaseTextInput color="#545FD6" label="출생년도" type="birthYear"/>
    <q-separator />
    <h4>기본 버튼</h4>
    <BaseButton label="로그인"/>
    <BaseButton back-ground-color="ffffff" text-color="#545FD6" label="회원가입"/>
    <BaseButton back-ground-color="#244684" label="PLAY" width="10rem" icon="play_arrow"/>
    <BaseButton back-ground-color="#244684" label="PLAYING" width="10rem" icon="pause"/>
    <q-separator />
    <h4>캐러셀</h4>
    <ArticleCarousel>
      <ArticleCarouselItem 
        v-for="(articleImage, index) in articleImages" :key="index" 
        :article-image="articleImage"
        :deletable="true"
      />
    </ArticleCarousel>

    <ArticleCarousel :number="2">
      <ArticleCarouselItem 
        v-for="(articleImage, index) in articleImages" :key="index" 
        :article-image="articleImage" 
      />
    </ArticleCarousel>
    <q-separator />
    <h1>컴포넌트 화면</h1>
    <q-btn color="primary" icon="check" label="OK" />
    <h3>퀘이사도 불러왔읍니당</h3>
    <iframe
      width="300"
      height="300"
      src="https://www.youtube.com/embed/fMNgy1JNz6A"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
      allowfullscreen
    ></iframe>
    <iframe width="300" height="300" src="https://www.instagram.com/p/CN6XiZElT4X/embed" frameborder="0"></iframe>
    <div></div>
    <KebabButton />
    <GoBack />
    <BottomNav />
    <Notification />
  </div>
</template>

<script>
import TheImageHeader from "@/components/common/TheImageHeader"
import BaseAvatar from "@/components/common/BaseAvatar"
import BaseTextInput from "@/components/common/BaseTextInput"
import BaseButton from "@/components/common/BaseButton"
import BaseProfileCard from "@/components/common/BaseProfileCard"
import ArticleCarousel from "@/components/article/ArticleCarousel"
import ArticleCarouselItem from "@/components/article/ArticleCarouselItem"
import GoBack from '@/components/common/GoBack.vue';
import BottomNav from '@/components/common/BottomNav.vue';
import Notification from '@/components/common/Notification.vue';
import KebabButton from '@/components/common/KebabButton.vue';
import { validation } from "@/mixins/validation"
import { getGoodWords } from '../api/index.js';

export default {
  name: 'Home',
  components: {
    TheImageHeader,
    BaseAvatar,
    BaseTextInput,
    BaseButton,
    BaseProfileCard,
    ArticleCarousel,
    ArticleCarouselItem,
    GoBack,
    BottomNav,
    Notification,
    KebabButton,
  },
  // filters: {},
  mixins: [validation],
  // props: {},
  data() {
    return {
      articleImages: ['https://placeimg.com/200/200/any?1',
         'https://placeimg.com/200/200/any?2', 
         'https://placeimg.com/200/200/any?7', 
         'https://placeimg.com/200/200/any?4',
         'https://placeimg.com/200/200/any?11',
         'https://placeimg.com/200/200/any?12', 
         'https://placeimg.com/200/200/any?14', 
         'https://placeimg.com/200/200/any?15', ]
    }
  },
  // computed: {},
  // watch: {},
  created() {
    getGoodWords()
      .then(function(response) {
        console.log('호출 후: ', response);
      })
      .catch(function(error) {
        console.log(error);
      });
  },
  // mounted() {},
  // updated() {},
  // methods: {},
};
>>>>>>> 593adca5788137221138b67fd84713124bdc8cc7
</script>
