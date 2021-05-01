<template>
  <div :class="isNight ? 'home home--night-backgound-color' : 'home home--day-backgroud-color'">
    <img
      :src="require(isNight ? '@/assets/images/image-header/night_1x1.jpg' : '@/assets/images/image-header/day_1x1.jpg')"
      alt="사진을 불러오지 못했어요."
      style="width: 100%;"
      class="layered-image"
    />
    <div class="home_saying home--night-font-color p-y-28">
      <p class="home_saying home--night-font-color" style="font-size: 40px">Bamboo Forest</p>
      <p class="home_saying home--night-font-color" style="text-align: right; font-size: 15px">고민을 나누는 익명의 공간</p>
    </div>
    <div class="absolute p-y-28" style="bottom: 20px; width: 100%;">
      <BaseButton style="padding: 0 28px;" back-ground-color="#ffffff" text-color="#244684" label="입장하기" @click.native="onEnterButton" />
    </div>
    <TheBottomNavigationBar style="width: 100%;" />
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
  // data() {
  //   return {}
  // },
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
  background-color: #000934;
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
