import Vue from 'vue';

import 'quasar/dist/quasar.css';
import lang from 'quasar/lang/ko-kr.js';
import '@quasar/extras/material-icons/material-icons.css';
import { Quasar, Notify, Cookies, Dialog, Loading, QSpinnerTail } from 'quasar';

Vue.use(Quasar, {
  config: {
    notify: {},
    loading: {
      spinner: QSpinnerTail,
      spinnerColor: 'grey-11',
      messageColor: 'white',
      message: '<b>Loading...</b>',
    },
  },
  plugins: {
    Notify,
    Cookies,
    Dialog,
    Loading,
  },
  components: {},
  lang: lang,
});
