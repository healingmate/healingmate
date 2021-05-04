<template>
  <div>
    <TheGoBackButton />
    <div class="q-pa-lg">
      <div class="absolute-left text-h6 text-bold text-black q-pa-sm q-ml-lg q-my-xl">
        <p class="q-mb-none">상담 규모를</p>
        <p class="q-mb-none">정해주세요 😄</p>
      </div>
    </div>
    <div>
      <BambooCardButton v-for="(card, index) in typeCards" :key="index" :entity="card" @click.native="onSelectType(card)" :id="`${card.isSelected ? 'selected' : 'non-selected'}`" />
    </div>
    <div>
      <BaseButton back-ground-color="#244684" text-color="#FFFFFF" label="참여하기" @click.native="onClickSelectButton" />
    </div>
  </div>
</template>

<script>
import TheGoBackButton from '@/components/common/TheGoBackButton.vue';
import BambooCardButton from '@/components/bamboo/BambooCardButton.vue';
import BaseButton from '@/components/common/BaseButton.vue';
import { Notify } from 'quasar';

export default {
  name: 'BamboTypeSelectPage',
  props: {
    category: Object,
  },
  data() {
    return {
      typeCards: [
        {
          id: 0,
          icon: '👨',
          title: '1 대 1',
          size: '3',
          width: '139px',
          height: '207px',
          borderColor: '#244684',
          textColor: '#244684',
          color: '#white',
          isSelected: false,
        },
        {
          id: 1,
          icon: '👬',
          title: '단체',
          size: '3',
          width: '139px',
          height: '207px',
          borderColor: '#244684',
          textColor: '#244684',
          color: '#white',
          isSelected: true,
        },
      ],
      myType: 1,
    };
  },
  components: {
    BambooCardButton,
    BaseButton,
    TheGoBackButton,
  },
  methods: {
    onSelectType(card) {
      card.isSelected = !card.isSelected;

      if (card.isSelected === true) {
        this.myType = card.id;
      }
      console.log(this.myType);
    },
    onClickSelectButton() {
      if (this.myType.length === 0) {
        Notify.create({
          position: 'top',
          color: 'negative',
          message: '상담 규모를 선택해주세요',
        });
      } else {
        console.log(this.myType, '선택함', 'axios보내기');
      }
    },
  },
};
</script>

<style scoped>
#selected {
  color: white;
  background-color: #244684;
  border-color: #244684;
  border-style: solid;
  border-width: medium;
}
#non-selected {
  color: #244684;
  background-color: white;
  border-color: #244684;
  border-style: solid;
  border-width: medium;
}
</style>
