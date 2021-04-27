import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Component3 from '@/views/common/Component3.vue'
import HealingContents from '@/views/healingcontents/ContentsMainPage.vue'

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
    path: '/component3',
    name: 'Component3',
    component: Component3
  }
]