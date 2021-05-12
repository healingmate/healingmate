<template>
  <div :class="'home home--night-backgound-color'">
    <img :src="require('@/assets/images/image-header/bamboo-main.jpg')" alt="사진을 불러오지 못했어요." style="width: 100%;" />

    <div class="home_saying home--night-font-color p-x-28">
      <p class="home_saying home--night-font-color" style="font-size: 40px">대나무숲</p>
      <p class="home_saying home--night-font-color" style="text-align: right; font-size: 15px">고민을 나누는 익명의 공간🌳</p>
    </div>

    <div class="absolute p-x-28" style="bottom: 48px; width: 100%;">
      <BaseButton back-ground-color="#ffffff" text-color="#244684" label="입장하기" @click.native="onEnterButton" />
    </div>

    <TheBottomNavigationBar style="position: absolute;" />
  </div>
</template>

<script>
import BaseButton from '@/components/common/BaseButton';
import TheBottomNavigationBar from '@/components/common/TheBottomNavigationBar';

export default {
  name: 'BambooEnterPage',
  components: {
    BaseButton,
    TheBottomNavigationBar,
  },
  // filters: {},
  // mixins: [],
  props: {
    isNight: Boolean,
  },
  data() {
    return {
      width: '100%',
    };
  },
  // computed: {},
  // watch: {},
  // created() {},
  // mounted() {},
  // updated() {},
  methods: {
    onEnterButton() {
      this.$router.push({ name: 'BambooCategoryPage' });
    },
  },
};
</script>

<style scoped>
.home {
  height: 100vh;
}

.home_saying {
  text-align: center;
  font-size: 24px;
  font-weight: 300;
}

.home--night-backgound-color {
  background-color: #171e3a;
}

.home--night-font-color {
  color: #ffffff;
}

.home--day-backgroud-color {
  background-color: #ffd89d;
}

.home--day-font-color {
  color: #000000;
}
</style>
