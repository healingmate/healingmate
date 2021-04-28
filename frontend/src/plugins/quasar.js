import Vue from 'vue';

import 'quasar/dist/quasar.css';
import lang from 'quasar/lang/ko-kr.js';
import '@quasar/extras/material-icons/material-icons.css';
import { Quasar, QIcon } from 'quasar';

Vue.use(Quasar, {
  config: {},
  plugins: {},
  components: {
    QIcon,
  },
  lang: lang,
});
