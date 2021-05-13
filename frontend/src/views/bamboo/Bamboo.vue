<template>
  <div>bamboo</div>
</template>

<script>
import Game from './game.js';

export default {
  name: 'Bamboo',
  data() {
    return {};
  },
  methods: {},
  mounted() {
    window.addEventListener('load', () => {
      const game = new Game();
      console.log(game);
    });
  },
};
</script>

<style></style>
