<template>
  <div @click="onClick" v-if="menuState">
    <div class="q-pa-md" style="max-width: 250px">
      <q-list bordered padding class="rounded-borders text-primary">
        <q-item clickable v-ripple @click="link = 'inbox'" active-class="my-menu-link">
          <q-item-section avatar>
            <q-icon name="edit" />
          </q-item-section>

          <q-item-section>게시글 수정</q-item-section>
        </q-item>

        <q-item clickable v-ripple @click="link = 'outbox'" active-class="my-menu-link">
          <q-item-section avatar>
            <q-icon name="delete" />
          </q-item-section>

          <q-item-section>게시글 삭제</q-item-section>
        </q-item>
      </q-list>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuState: true,
    };
  },
  props: {
    isOpened: Boolean,
  },
  methods: {
    onClick() {
      this.menuState = false;
      this.$emit('doClose', this.menuState);
      console.log('메뉴 열린 후에 클릭 동작');
    },
  },
};
</script>

<style></style>
