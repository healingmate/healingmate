import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Component3 from '@/views/common/Component3.vue'
import HealingContents from '@/views/healingcontents/ContentsMainPage.vue'
import Healing from '@/views/healingcontents/ContentsHealingPage.vue'
import Asmr from '@/views/healingcontents/ContentsAsmrPage.vue'
import Meditation from '@/views/healingcontents/ContentsMeditationPage.vue'

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
    path: '/healingcontents/main',
    name: 'healingcontents',
    component: HealingContents
  },
  {
    path: '/healingcontents/healing',
    name: 'healing',
    component: Healing
  },
  {
    path: '/healingcontents/asmr',
    name: 'asmr',
    component: Asmr
  },
  {
    path: '/healingcontents/meditation',
    name: 'meditation',
    component: Meditation
  },
  {
    path: '/component3',
    name: 'Component3',
    component: Component3
  }
]