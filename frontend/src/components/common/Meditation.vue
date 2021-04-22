<template>
  <div class="q-pa-lg">
    <p>component3 모음</p>
    <!-- 명상 card -->
    <q-card class="my-card">
      <q-img style="height: 27vh;" src="https://cdn.quasar.dev/img/parallax2.jpg">
        <div class="absolute-bottom text-right transparent">
          <p class="q-ma-none text-caption text-blue-grey-2">10 min</p>
          <p class="q-ma-none text-subtitle2 text-bold">아침 명상</p>
        </div>
      </q-img>  
    </q-card>
    <!-- 콘텐츠 메인 카테고리 card -->
    <q-card class="my-card row items-center bg-primary">
      <div class="col-4 transparent justify-center q-ml-lg text-subtitle2 text-bold">Contents</div>
      <q-img class="col-6" style="height: 13vh;" src="https://cdn.quasar.dev/img/parallax2.jpg" />
    </q-card>
    <!-- 키워드 -->
    <q-chip outline square color="indigo-9" text-color="white">
      # 생활
    </q-chip>
    <!-- 기본 버튼 -->
    <q-btn
      size="22px"
      class="q-px-xl q-py-sm"
      color="green-6"
    >
      <div>
        <div>💻</div>
        <div class="text-caption text-bold">취업</div>
      </div>
    </q-btn>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>