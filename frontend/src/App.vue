<template>
  <div class="row justify-center">
    <div class="col-xs-12 col-sm-6 col-md-4" style="height: 100vh; position: relative;">
      <!-- 테스트용 로그아웃 버튼 시작 -->
      <div v-if="this.$store.state.accessToken" style="position: absolute; left: 100px; z-index: 3;">
        <q-btn color="primary" label="테스트용 로그아웃 버튼" @click="onClick" />
      </div>
      <!-- 테스트용 로그아웃 버튼 끝 -->
      <router-view :is-night="isNight" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  // components: {},
  // filters: {},
  // mixins: [],
  // props: {},
  // data() {
  //   return {}
  // },
  computed: {
    // 현재 시간이 낮인지 밤인지 구하는 boolean 타입의 isNight 밤이면 true 낮이면 false
    isNight() {
      const hour = new Date().getHours();
      return 18 <= hour || hour <= 6;
    },
  },
  // watch: {},
  // created() {},
  // mounted() {},
  // updated() {},
  methods: {
    onClick() {
      this.$store.dispatch('removeToken');
    },
  },
};
</script>

<style>
@import url('./assets/styles/global.css');
html,
body {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
body {
  margin: 0px;
}
#app {
  height: 100%;
}
</style>
