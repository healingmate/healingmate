<template>
  <div class="home">
    <h1>컴포넌트 화면</h1>
    <q-btn color="primary" icon="check" label="OK" />
    <h3>퀘이사도 불러왔읍니당</h3>
    <iframe
      width="300"
      height="300"
      src="https://www.youtube.com/embed/fMNgy1JNz6A"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
      allowfullscreen
    ></iframe>
    <iframe width="300" height="300" src="https://www.instagram.com/p/CN6XiZElT4X/embed" frameborder="0"></iframe>
    <div>
      <KebabButton />
      <GoBack />
      <Notification />
      <BottomNav />
    </div>
  </div>
</template>

<script>
import { getGoodWords } from '../api/index.js';
import GoBack from '@/components/common/GoBack.vue';
import BottomNav from '@/components/common/BottomNav.vue';
import Notification from '@/components/common/Notification.vue';
import KebabButton from '@/components/common/KebabButton.vue';

export default {
  name: 'Home',
  components: {
    GoBack,
    BottomNav,
    Notification,
    KebabButton,
  },
  created() {
    getGoodWords()
      .then(function(response) {
        console.log('호출 후: ', response);
      })
      .catch(function(error) {
        console.log(error);
      });
  },
};
</script>
