<template>
  <div>
    <div v-if="!isOpened">
      <q-btn class="back" round icon="more_vert" @click="onClick" />
    </div>
    <div v-else>
      <Menu :isOpened="true" @doClose="onClose" />
    </div>
  </div>
</template>

<script>
import Menu from '@/components/common/Menu.vue';
export default {
  components: {
    Menu,
  },
  data() {
    return {
      isOpened: false,
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
