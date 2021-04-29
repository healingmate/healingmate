<template>
  <div class="row justify-center">
    <div class="col-xs-12 col-sm-6 col-md-4" style="height: 100vh; position: relative;">
      <router-view/>
    </div>
  </div>
</template>

<script>

export default {
  name: 'App',

  components: {
  },
}
</script>

<style>
@import url('./assets/styles/global.css');
</style>
