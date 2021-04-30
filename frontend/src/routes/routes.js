import Home from '../views/Home.vue'
import ProjectComponentsPage from '../views/ProjectComponentsPage.vue'
import AccountSignUpFirstPage from '@/views/account/AccountSignUpFirstPage'
import AccountSignUpSecondPage from '@/views/account/AccountSignUpSecondPage'
import AccountSignInPage from '@/views/account/AccountSignInPage'
import Component3 from '@/views/common/Component3.vue'
import HealingContent from '@/views/healing-content/ContentsMainPage.vue'
import Contents from '@/views/healing-content/ContentsHealingPage.vue'
import Asmr from '@/views/healing-content/ContentsAsmrPage.vue'
import Meditation from '@/views/healing-content/ContentsMeditationPage.vue'
import PageNotFoundPage from '@/views/common/PageNotFoundPage'

export default [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: ProjectComponentsPage
  },
  {
    path: '/account/sign-up/first',
    name: 'AccountSignUpFirstPage',
    component: AccountSignUpFirstPage
  },
  {
    path: '/account/sign-up/second',
    name: 'AccountSignUpSecondPage',
    component: AccountSignUpSecondPage
  },
  {
    path: '/account/sign-In',
    name: 'AccountSignInPage',
    component: AccountSignInPage
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
  },
  {
    path: '*',
    component: PageNotFoundPage
  },
]