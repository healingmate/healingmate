<template>
  <div class="p-y-28 flex flex-center q-mt-lg">
    <div 
      class="q-py-md q-px-xl"
      style="width: 100%; border: 2px solid black; border-radius: 10px; text-align: center;">
      <div class="q-pt-xs text-subtitle2 text-weight-bold">당신의 말랑지수는?</div>
      <q-knob
        show-value
        :instant-feedback="true"
        :readonly="true"
        font-size="16px"
        class="text-red q-ma-md"
        v-model="value"
        size="80px"
        :thickness="0.05"
        color="red"
        track-color="grey-3"
      >
        <q-icon name="favorite" class="q-mr-xs" />
        {{ value }}
      </q-knob>
    </div>
  </div>  
</template>

<script>
export default {
  data() {
    return {
      value: 70,
    }
  }
}
</script>

<style>

</style>