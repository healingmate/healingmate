import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Component3 from '@/views/common/Component3.vue'
import HealingContent from '@/views/healing-content/ContentsMainPage.vue'
import Contents from '@/views/healing-content/ContentsHealingPage.vue'
import Asmr from '@/views/healing-content/ContentsAsmrPage.vue'
import Meditation from '@/views/healing-content/ContentsMeditationPage.vue'

export default [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/healing-content/main',
    name: 'healing-content',
    component: HealingContent
  },
  {
    path: '/healing-content/contents',
    name: 'contents',
    component: Contents
  },
  {
    path: '/healing-content/asmr',
    name: 'asmr',
    component: Asmr
  },
  {
    path: '/healing-content/meditation',
    name: 'meditation',
    component: Meditation
  },
  {
    path: '/component3',
    name: 'Component3',
    component: Component3
  }
]