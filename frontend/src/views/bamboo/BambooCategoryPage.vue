<template>
  <div>
    <TheGoBackButton color="dark"/>

    <div class="absolute-left text-h6 text-bold text-black q-pa-sm q-ml-lg q-my-xl">
      <p class="q-mb-none">고민 카테고리를</p>
      <p class="q-mb-none">선택해주세요 😊</p>
    </div>

    <div class="p-y-28 row" style="margin-top: 130px;">
      <div
        v-for="(card, index) in categoryCards"
        :key="index"
        style="width: 50%; padding: 0 6px;"
        class="row justify-center"
      >
        <BambooCardButton
          :entity="card"
          class="category"
          :style="`border-style: ${card.selectedCategory ? 'solid;' : 'none; filter: drop-shadow(0px 0px 2px black);'};`"
          @click.native="onSelectCategory(card)"
        />
      </div>
    </div>

    <div class="p-y-28 absolute" style="bottom: 20px; width: 100%">
      <BaseButton back-ground-color="#244684" text-color="#FFFFFF" label="선택하기" @click.native="onClickSelectButton" />
    </div>

  </div>
</template>

<script>
import TheGoBackButton from '@/components/common/TheGoBackButton.vue';
import BambooCardButton from '@/components/bamboo/BambooCardButton.vue';
import BaseButton from '@/components/common/BaseButton.vue';
import { Notify } from 'quasar';

import categoryData from '@/assets/data/bambooCategory.json';
export default {
  name: 'BambooCategoryPage',
  data() {
    return {
      categoryCards: categoryData.content,
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
      if (this.myCategory.length < 3) {
        if (card.selectedCategory) {
          this.myCategory.push(card)
        } else {
          this.myCategory.splice(this.myCategory.indexOf(card), 1)
        }
      } else {
        if (!card.selectedCategory) {
          this.myCategory.splice(this.myCategory.indexOf(card), 1)
        } else {
          this.$q.notify({
            position: 'top',
            color: 'negative',
            message: '키워드는 최대 3개까지 선택할 수 있습니다.'
          })
          card.selectedCategory = !card.selectedCategory;
        }
      }
    },
    onClickSelectButton() {
      if (this.myCategory.length) {
        console.log("선택된 카테고리", this.myCategory);
        this.$router.push({ name: 'BambooTypeSelectPage', params: { myCategory: this.myCategory } });
      } else {
        Notify.create({
          position: 'top',
          color: 'negative',
          message: '카테고리를 선택해주세요',
        });
      }
    },
  },
};
</script>

<style scoped>
.category {
  margin: 6px 0;
  width: 50%;
  border-color: black !important; 
  border-width: medium;
}
</style>
