import Vue from 'vue';

import 'quasar/dist/quasar.css';
import lang from 'quasar/lang/ko-kr.js';
import '@quasar/extras/material-icons/material-icons.css';
import { Quasar, Notify } from 'quasar';

Vue.use(Quasar, {
  config: {
    notify: {}
  },
  plugins: {
    Notify
  },
  components: {},
  lang: lang,
});
