<template>
  <div class="home">
    <h1>컴포넌트 화면</h1>
    <q-btn color="primary" icon="check" label="OK"/>
    <h3>퀘이사도 불러왔읍니당</h3>
  </div>
</template>

<script>
import { getGoodWords } from '../api/index.js';

export default {
  name: 'Home',
  components: {
  },
  created() {
    getGoodWords()
    .then(function(response) {
      console.log('호출 후: ', response);
    })
    .catch(function(error) { 
      console.log(error);
    });
  }

}
</script>
