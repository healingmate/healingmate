<template>
  <q-item clickable style="padding: 0;">
    <q-item-section style="text-align: center;">
      <q-icon :name="kebabIcon[0]" class="text-caption" style="padding-left: 10px;">{{ kebabIcon[1] }}</q-icon>
    </q-item-section>
  </q-item>
</template>

<script>
export default {
  name: 'BaseMenu',
  data() {
    return {
      menuState: true,
    };
  },
  props: {
    kebabIcon: {
      type: Array,
      default: null,
    },
  },
  methods: {
    onClick() {
      console.log('메뉴 열린 후에 클릭 동작');
    },
  },
};
</script>

<style></style>
