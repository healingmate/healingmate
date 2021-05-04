<template>
  <div>
    <the-image-header 
      style="background-color: #00295A;" 
      background-image="asmr-contents-header.png" 
      label="ASMR"
      :brightness="50"
    ></the-image-header>
    <!-- 이전 페이지 이동 -->
    <the-go-back-button></the-go-back-button>
    <div 
      class="q-pa-lg flex justify-between"
      style="max-width: 100%;"
    >
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
import TheGoBackButton from '@/components/common/TheGoBackButton';
import { data } from '@/assets/data/AsmrContents.js';

export default {
  components: {
    TheImageHeader,
    ContentsAsmr,
    TheGoBackButton,
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