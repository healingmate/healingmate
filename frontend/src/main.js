import Vue from 'vue'
import App from './App.vue'
import router from './routes'
import store from './store'
import axios from 'axios'
import './plugins/quasar'

Vue.config.productionTip = false

Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
