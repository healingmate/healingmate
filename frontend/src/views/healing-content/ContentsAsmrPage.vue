<template>
  <div>
    <the-image-header 
      class="relative" 
      style="background-color: #00295A; height: 17vh;" 
      background-image="asmr-contents-header.png" 
      label="ASMR"
    ></the-image-header>
    <!-- TODO : 이전 페이지 이동 버튼 넣기-->
    <div class="q-pa-lg flex row wrap justify-between">
      <contents-asmr 
        v-for="(asmr, index) in asmrList" 
        :key="index" 
        :entity="asmr"
      ></contents-asmr>
    </div>
  </div>
</template>

<script>
import TheImageHeader from '@/components/common/TheImageHeader';
import ContentsAsmr from '@/components/healing-content/ContentsAsmr';
import { data } from '@/assets/data/AsmrContents.js';

export default {
  components: {
    TheImageHeader,
    ContentsAsmr,
  },
  data() {
    return {
      asmrList: data,
    }
  }
}
</script>

<style>

</style>