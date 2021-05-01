import Home from '../views/Home.vue'
import ProjectComponentsPage from '../views/ProjectComponentsPage.vue'
import AccountSignUpFirstPage from '@/views/account/AccountSignUpFirstPage'
import AccountSignUpSecondPage from '@/views/account/AccountSignUpSecondPage'
import AccountSignInPage from '@/views/account/AccountSignInPage'
import HealingContent from '@/views/healing-content/ContentsMainPage.vue'
import Contents from '@/views/healing-content/ContentsHealingPage.vue'
import Asmr from '@/views/healing-content/ContentsAsmrPage.vue'
import Meditation from '@/views/healing-content/ContentsMeditationPage.vue'
import PageNotFoundPage from '@/views/common/PageNotFoundPage'
// import MeditationDetail from '@/views/healing-content/ContentsMusicPage.vue'
import Profile from '@/views/user/UserProfilePage.vue'

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
    path: '/account/sign-in',
    name: 'AccountSignInPage',
    component: AccountSignInPage
  },
  {
    path: '/healing-content/main',
    name: 'Healing-content',
    component: HealingContent,
    meta: {authRequired: true}
  },
  {
    path: '/healing-content/contents',
    name: 'Contents',
    component: Contents,
    meta: {authRequired: true}
  },
  {
    path: '/healing-content/asmr',
    name: 'Asmr',
    component: Asmr,
    meta: {authRequired: true}
  },
  {
    path: '/healing-content/meditation',
    name: 'Meditation',
    component: Meditation,
    meta: {authRequired: true}
  },
  // {
  //   path: '/healing-content/meditation/:id',
  //   name: 'MeditationDetail',
  //   component: MeditationDetail
  // },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    meta: {authRequired: true}
  },
  {
    path: '*',
    component: PageNotFoundPage
  },
]