<template>
  <div>
    <TheGoBackButton />
    <div class="absolute-left text-h6 text-bold text-black q-pa-sm q-ml-lg q-my-xl">
      <p class="q-mb-none">하나의 고민 카테고리를</p>
      <p class="q-mb-none">선택해주세요 😊</p>
    </div>
    <div>
      <BambooCardButton v-for="(card, index) in categoryCards" :key="index" :entity="card" @click.native="onSelectCategory(card)" style="border-width: thick; border-style: solid;" />
    </div>
    <div>
      <BaseButton back-ground-color="#244684" text-color="#FFFFFF" label="선택하기" @click.native="onClickSelectButton" />
    </div>
  </div>
</template>

<script>
import TheGoBackButton from '@/components/common/TheGoBackButton.vue';
import BambooCardButton from '@/components/bamboo/BambooCardButton.vue';
import BaseButton from '@/components/common/BaseButton.vue';
import BambooTypeSelectPage from './BambooTypeSelectPage.vue';
import { Notify } from 'quasar';

export default {
  name: 'BambooCategoryPage',
  data() {
    return {
      categoryCards: [
        {
          id: 1,
          icon: '🌂',
          title: '취업',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#55A822',
          selectedCategory: false,
        },
        {
          id: 2,
          icon: '🌂',
          title: '학업/진로',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#C34159',
          selectedCategory: false,
        },
        {
          id: 3,
          icon: '🌂',
          title: '가족',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#DC972F',
          selectedCategory: false,
        },
        {
          id: 4,
          icon: '🌂',
          title: '대인관계',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#242E90',
          selectedCategory: false,
        },
        {
          id: 5,
          icon: '🌂',
          title: '생활정보',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#E7899A',
          selectedCategory: false,
        },
        {
          id: 6,
          icon: '🌂',
          title: '성격',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#565768',
          selectedCategory: false,
        },
        {
          id: 7,
          icon: '🌂',
          title: '직장',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#287FAF',
          selectedCategory: false,
        },
        {
          id: 8,
          icon: '🌂',
          title: '학교',
          size: '3',
          width: '139px',
          height: '95px',
          borderColor: 'white',
          color: '#8B31C1',
          selectedCategory: false,
        },
      ],
      myCategory: [],
    };
  },
  components: {
    BambooCardButton,
    BaseButton,
    TheGoBackButton,
  },
  methods: {
    onSelectCategory(card) {
      card.selectedCategory = !card.selectedCategory;
      if (card.selectedCategory === true) {
        card.borderColor = 'black';
      } else {
        card.borderColor = 'white';
      }
      console.log(card.selectedCategory);
    },
    onClickSelectButton() {
      for (let i = 0; i <= 7; i++) {
        if (this.categoryCards[i].selectedCategory === true) {
          console.log(this.categoryCards[i]);
          this.myCategory.push(this.categoryCards[i]);
        }
      }

      if (this.myCategory.length === 0) {
        Notify.create({
          position: 'top',
          color: 'negative',
          message: '카테고리를 선택해주세요',
        });
      } else {
        console.log(this.myCategory, '선택함', 'axios보내기');
        this.$router.push({ name: BambooTypeSelectPage, params: this.myCategory });
      }
    },
  },
};
</script>

<style></style>
