<template>
  <q-avatar 
    :size="`${size}rem`" 
    style="box-shadow: 0 2px 4px -1px rgba(0,0,0,.2),0 4px 5px 0 rgba(0,0,0,.14),0 1px 10px 0 rgba(0,0,0,.12)!important;"
    @click="goToProfile"
  >
    <img :src="require(`@/assets/images/character/${this.profileImagePath}.png`)">
  </q-avatar>
</template>

<script>


export default {

name: 'BaseAvatar',
// components: {},
// filters: {},
props: {
  profileImagePath: {
    type: String,
    default: "RABBIT"
  },
  size: {
    type: Number,
    default: 2,
  },
  goProfile: {
    type: Boolean,
    default: false,
  }
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
    goToProfile() {
      if (this.goProfile) {
        this.$router.push('/profile');
      }
    },
  },
}
</script>

<style>

</style>