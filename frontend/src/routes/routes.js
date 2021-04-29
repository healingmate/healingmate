import Home from '../views/Home.vue';
import About from '../views/About.vue';
import Temp from '../views/bamboo/Temp.vue';

export default [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    component: About,
  },
  {
    path: '/temp',
    name: 'Temp',
    component: Temp,
  },
];
