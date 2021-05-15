import Home from '../views/Home.vue'
import ProjectComponentsPage from '../views/ProjectComponentsPage.vue'
import AccountSignUpFirstPage from '@/views/account/AccountSignUpFirstPage'
import AccountSignUpSecondPage from '@/views/account/AccountSignUpSecondPage'
import AccountSignInPage from '@/views/account/AccountSignInPage'
import ArticleFeedPage from '@/views/article/ArticleFeedPage'
import ArticleCreatePage from '@/views/article/ArticleCreatePage'
import HealingContent from '@/views/healing-content/ContentsMainPage.vue'
import Contents from '@/views/healing-content/ContentsHealingPage.vue'
import Asmr from '@/views/healing-content/ContentsAsmrPage.vue'
import Meditation from '@/views/healing-content/ContentsMeditationPage.vue'
import PageNotFoundPage from '@/views/common/PageNotFoundPage'
import Profile from '@/views/user/UserProfilePage.vue'
import UpdateInformation from '@/views/user/UserProfileUpdatePage.vue'
import NotificationPage from '@/views/user/UserNotificationPage.vue'
import UpdatePassword from '@/views/account/AccountUpdatePasswordPage.vue'
import BambooEnterPage from '@/views/bamboo/BambooEnterPage.vue'
import BambooCategoryPage from '@/views/bamboo/BambooCategoryPage.vue'
import BambooTypeSelectPage from '@/views/bamboo/BambooTypeSelectPage.vue'
import BambooForestPage from '@/views/bamboo/BambooForestPage.vue'
import Bamboo from '@/views/bamboo/Bamboo.vue'
import Blockland from '@/views/bamboo/Blockland.vue'

export default [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: { anonymRequired: true },
  },
  {
    path: '/about',
    name: 'About',
    component: ProjectComponentsPage,
  },
  {
    path: '/account/sign-up/first',
    name: 'AccountSignUpFirstPage',
    component: AccountSignUpFirstPage,
    meta: { anonymRequired: true },
  },
  {
    path: '/account/sign-up/second',
    name: 'AccountSignUpSecondPage',
    component: AccountSignUpSecondPage,
    meta: { anonymRequired: true },
  },
  {
    path: '/account/sign-in',
    name: 'AccountSignInPage',
    component: AccountSignInPage,
    meta: { anonymRequired: true },
  },
  {
    path: '/articles',
    name: 'ArticleFeedPage',
    component: ArticleFeedPage,
    meta: {authRequired: true}
  },
  {
    path: '/article/create',
    name: 'ArticleCreatePage',
    component: ArticleCreatePage,
    meta: {authRequired: true}
  },
  {
    path: '/healing-content/main',
    name: 'Healing-content',
    component: HealingContent,
    meta: { authRequired: true },
  },
  {
    path: '/healing-content/contents',
    name: 'Contents',
    component: Contents,
    meta: { authRequired: true },
  },
  {
    path: '/healing-content/asmr',
    name: 'Asmr',
    component: Asmr,
    meta: { authRequired: true },
  },
  {
    path: '/healing-content/meditation',
    name: 'Meditation',
    component: Meditation,
    meta: { authRequired: true },
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    meta: { authRequired: true },
  },
  {
    path: '/update-information',
    name: 'UpdateInformation',
    component: UpdateInformation,
    meta: {authRequired: true}
  },
  {
    path: '/update-password',
    name: 'UpdatePassword',
    component: UpdatePassword,
    meta: {authRequired: true}
  },
  {
    path: '/notification',
    name: 'NotificationPage',
    component: NotificationPage,
    meta: {authRequired: true}
  },
  {
    path: '/bamboo/main',
    name: 'BambooEnterPage',
    component: BambooEnterPage,
    meta: { authRequired: true },
  },
  {
    path: '/bamboo/category',
    name: 'BambooCategoryPage',
    component: BambooCategoryPage,
    props: true,
    meta: { authRequired: true },
  },
  {
    path: '/bamboo/type',
    name: 'BambooTypeSelectPage',
    component: BambooTypeSelectPage,
    meta: { authRequired: true },
  },
  {
    path: '/bamboo/:roomType',
    name: 'BambooForestPage',
    component: BambooForestPage,
    meta: { authRequired: true },
    props: true,
  },
  {
    path: '/bamboo',
    name: 'Bamboo',
    component: Bamboo,
    // meta: { authRequired: true },
  },
  {
    path: '/blockland',
    name: 'Blockland',
    component: Blockland,
    // meta: { authRequired: true },
  },
  {
    path: '*',
    component: PageNotFoundPage,
  },
];
