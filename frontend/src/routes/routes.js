import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Component3 from '@/components/common/Meditation.vue'

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
    path: '/component3',
    name: 'Component3',
    component: Component3
  }
]