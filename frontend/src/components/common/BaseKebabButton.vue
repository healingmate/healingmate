<template>
  <div>
    <div v-if="!isOpened">
      <q-btn class="back" round icon="more_vert" @click="onClick" />
    </div>
    <div v-else>
      <BaseMenu :isOpened="true" @doClose="onClose" :flag="flag" />
    </div>
  </div>
</template>

<script>
import BaseMenu from '@/components/common/BaseMenu.vue';
export default {
  components: {
    BaseMenu,
  },
  data() {
    return {
      isOpened: false,
      flag: 1,
    };
  },

  methods: {
    onClick() {
      console.log('clicked');
      this.isOpened = true;
      console.log(this.isOpened);
    },
    onClose(state) {
      console.log('닫기');
      this.isOpened = state;
    },
  },
  created() {},
};
</script>

<style>
.back {
  background-color: transparent;

  margin: auto;
  /* backface-visibility: visible; */
}
</style>
