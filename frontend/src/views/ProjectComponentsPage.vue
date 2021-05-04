<template>
  <div class="home">
    <h3>컴포넌트 화면</h3>
    <q-separator />
    <h4>이미지 헤더</h4>
    <TheImageHeader background-image="night_16x9.jpg" color="#131034" />
    <TheImageHeader background-image="day_16x9.jpg" label="Meditation" />
    <q-separator />
    <h4>아바타</h4>
    <BaseAvatar />
    <BaseAvatar :size="5" />
    <BaseAvatar :size="7" profile-image-path="https://i.pinimg.com/564x/3b/ab/dc/3babdc3f9e2c7bed5d0548f9f818db4e.jpg" />
    <q-separator />
    <h4>아티클 카드</h4>
    <ArticleCard v-for="(article, index) in articleList" :key="index" :article="article" />
    <q-separator />
    <h4>텍스트 인풋</h4>
    <BaseTextInput color="#545FD6" label="아이디" :rules="[required(), minLength(5), maxLength(16), alphabetNumber()]" />
    <BaseTextInput color="#545FD6" label="닉네임" :rules="[required(), minLength(2), maxLength(10), korean()]" />
    <BaseTextInput color="#545FD6" label="패스워드" type="password" :rules="[required(), minLength(8), maxLength(16)]" />
    <BaseTextInput color="#545FD6" label="출생년도" type="birthYear" />
    <q-separator />
    <h4>기본 버튼</h4>
    <BaseButton label="로그인" />
    <BaseButton back-ground-color="ffffff" text-color="#545FD6" label="회원가입" />
    <BaseButton back-ground-color="#244684" label="PLAY" width="10rem" icon="play_arrow" />
    <BaseButton back-ground-color="#244684" label="PLAYING" width="10rem" icon="pause" />
    <q-separator />
    <h4>캐러셀</h4>
    <ArticleCarousel>
      <ArticleCarouselItem v-for="(articleImage, index) in articleImages" :key="index" :article-image="articleImage" :deletable="true" />
    </ArticleCarousel>

    <ArticleCarousel :number="2">
      <ArticleCarouselItem v-for="(articleImage, index) in articleImages" :key="index" :article-image="articleImage" />
    </ArticleCarousel>
    <q-separator />
    <h4>케밥버튼</h4>
    <BaseKebabButton>
      <BaseMenu v-for="(kebabIcon, index) in kebabIcons" :key="index" :kebab-icon="kebabIcon" />
    </BaseKebabButton>
    <q-separator />
    <h4>뒤로가기 버튼</h4>
    <TheGoBackButton />
    <q-separator />
    <h4>바텀 네비게이션 바</h4>
    <TheBottomNavigationBar />
    <q-separator />
    <h4>알림 버튼</h4>
    <TheNotification :color="color" />
    <q-separator />
    <h4>힐링 콘텐츠 카드</h4>
    <contents-card :entity="contents"></contents-card>
    <q-separator />
    <h4>힐링 콘텐츠 버튼</h4>
    <bamboo-card-button :entity="button"></bamboo-card-button>
    <q-separator />
    <h4>힐링 콘텐츠 ASMR</h4>
    <contents-asmr :entity="asmr"></contents-asmr>
    <q-separator />
    <h4>힐링 콘텐츠 카테고리</h4>
    <contents-category :entity="category"></contents-category>
    <q-separator />
    <h4>키워드 토글 버튼</h4>
    <base-keyword :entity="keyword"></base-keyword>
    <q-separator />
    <h4>힐링 콘텐츠 명상</h4>
    <contents-meditation :entity="meditation"></contents-meditation>
    <q-separator />
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import BaseAvatar from '@/components/common/BaseAvatar';
import BaseTextInput from '@/components/common/BaseTextInput';
import BaseButton from '@/components/common/BaseButton';
import ArticleCard from '@/components/article/ArticleCard';
import ArticleCarousel from '@/components/article/ArticleCarousel';
import ArticleCarouselItem from '@/components/article/ArticleCarouselItem';

import TheGoBackButton from '@/components/common/TheGoBackButton.vue';
import TheBottomNavigationBar from '@/components/common/TheBottomNavigationBar.vue';
import TheNotification from '@/components/common/TheNotification.vue';
import BaseKebabButton from '@/components/common/BaseKebabButton.vue';
import BaseMenu from '@/components/common/BaseMenu.vue';

import ContentsCard from '@/components/healing-content/ContentsCard';
import BambooCardButton from '@/components/bamboo/BambooCardButton';
import ContentsAsmr from '@/components/healing-content/ContentsAsmr';
import ContentsCategory from '@/components/healing-content/ContentsCategory';
import BaseKeyword from '@/components/common/BaseKeyword';
import ContentsMeditation from '@/components/healing-content/ContentsMeditation';

import { validation } from '@/mixins/validation';

import articleListPage from '@/assets/data/articleListDummy.json';

export default {
  name: 'Home',
  components: {
    TheImageHeader,
    BaseAvatar,
    BaseTextInput,
    BaseButton,
    ArticleCard,
    ArticleCarousel,
    ArticleCarouselItem,

    TheGoBackButton,
    TheBottomNavigationBar,
    TheNotification,
    BaseKebabButton,
    BaseMenu,

    ContentsCard,
    BambooCardButton,
    ContentsAsmr,
    ContentsCategory,
    BaseKeyword,
    ContentsMeditation,
  },
  // filters: {},
  mixins: [validation],
  // props: {},
  data() {
    return {
      articleImages: [
        'https://placeimg.com/200/200/any?1',
        'https://placeimg.com/200/200/any?2',
        'https://placeimg.com/200/200/any?7',
        'https://placeimg.com/200/200/any?4',
        'https://placeimg.com/200/200/any?11',
        'https://placeimg.com/200/200/any?12',
        'https://placeimg.com/200/200/any?14',
        'https://placeimg.com/200/200/any?15',
      ],
      kebabIcons: [
        ['edit', '게시글 수정'],
        ['delete', '게시글 삭제'],
      ],
      // 알림버튼 색깔
      color: 'black',
      articleList: articleListPage.content,
      contents: {
        category: '유튜브',
        title: '힐링 콘텐츠',
        // contents: 'https://media.giphy.com/media/iDJXOU0SWNCPEsa5EL/giphy.gif',
        contents: 'https://youtu.be/k3_tw44QsZQ',
        bookmarked: false,
      },
      button: {
        icon: '💻',
        title: '취업',
        size: '20px',
        color: '#55A822',
        width: '40vw',
        height: '15vh',
      },
      asmr: {
        icon: '🌿',
        title: '숲소리',
        font_color: 'white',
        color: '#2F6673',
        width: '40vw',
        height: '25vh',
      },
      category: {
        address: 'contents',
        title: 'Contents',
        img: 'contents.png',
        color: '#F2BAB7',
        font_color: 'black',
      },
      keyword: {
        keyword: '취업',
        click: false,
      },
      meditation: {
        time: 11,
        title: '아침 명상',
        background_img: 'https://cdn.quasar.dev/img/parallax2.jpg',
      },
      noti: {
        username: '하이하이',
        date: '2021.05.03',
        profile_img: 'https://cdn.quasar.dev/img/avatar.png',
      },
    };
  },
  // computed: {},
  // watch: {},
  // created() {},
  // mounted() {},
  // updated() {},
  // methods: {},
};
</script>
