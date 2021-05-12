<template>
  <q-tabs
    id="nav"
    v-model="tab"
    indicator-color="transparent"
    @input="onTabs"
  >
    <q-tab name="feed" :icon="`img:${require('@/assets/images/bottom-navigation/feed.png')}`"/>
    <q-tab name="bamboo" :icon="`img:${require('@/assets/images/bottom-navigation/bamboo.png')}`" />
    <q-tab name="contents" :icon="`img:${require('@/assets/images/bottom-navigation/contents.png')}`" />
  </q-tabs>
</template>

<script>
export default {
	name: 'TheBottomNavigationBar',
	// components: {},
	// filters: {},
  // mixins: [],
	data() {
    return {
      tab: null,
    };
  },
	// computed: {},
	// watch: {},
	created() {
    if (this.$route.name === 'ArticleFeedPage') {
      this.tab = 'feed'
    } else if (this.$route.name === 'BambooEnterPage') {
      this.tab = 'bamboo'
    } else if (this.$route.name === 'Healing-content') {
      this.tab = 'contents'
    }
  },
	// mounted() {},
	// updated() {},
	methods: {
    onTabs(tabName) {
      if (tabName === 'feed' && this.$route.name !== 'ArticleFeedPage') {
        this.$router.push({name: 'ArticleFeedPage'})
      } else if (tabName === 'bamboo' && this.$route.name !== 'BambooEnterPage') {
        this.$router.push({name: 'BambooEnterPage'})
      } else if (tabName === 'contents' && this.$route.name !== 'Healing-content') {
        this.$router.push({name: 'Healing-content'})
      }
    }
  },
}
</script>

<style >
#nav {
  position: sticky;
  width: 100%;
  background-color: #244684;
  bottom: 0;
  z-index: 2;
}

.q-tab__icon {
  height: 32px !important;
  width: 32px !important;
}

.q-tab--active {
  filter: brightness(100);
}
</style>