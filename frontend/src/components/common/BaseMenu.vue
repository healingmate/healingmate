<template>
  <div @click="onClick" v-if="menuState">
    <div class="q-pa-md" style="max-width: 250px">
      <!-- 게시글 CRUD -->
      <q-list v-if="flag == 0" bordered padding class="rounded-borders text-primary">
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

      <!-- 힐링컨텐츠 -->
      <q-list v-if="flag == 1" bordered padding class="rounded-borders text-primary">
        <q-item clickable v-ripple @click="link = 'inbox'" active-class="my-menu-link">
          <q-item-section avatar>
            <q-icon name="smart_display" />
          </q-item-section>

          <q-item-section>유튜브</q-item-section>
        </q-item>

        <q-item clickable v-ripple @click="link = 'outbox'" active-class="my-menu-link">
          <q-item-section avatar>
            <q-icon name="facebook" />
          </q-item-section>

          <q-item-section>페이스북</q-item-section>
        </q-item>

        <q-item clickable v-ripple @click="link = 'outbox'" active-class="my-menu-link">
          <q-item-section avatar>
            <q-icon name="image" />
          </q-item-section>

          <q-item-section>이미지</q-item-section>
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
    flag: Number,
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
