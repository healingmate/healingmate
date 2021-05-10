<template>
  <div>
		<p :style="`color: ${ color }; font-size: 1rem;`">{{ label }}</p>
    <q-input
			v-model="text"
			ref="input"
			:readonly="readonly"
			outlined
			bg-color="white"
			:dense="true"
			:type="type"
			:rules="rules"
			@input="emitInputData()"
		> 
			<template 
				v-if="type === 'birthYear'" 
				v-slot:append
			>
        <q-icon 
					:style="`color: ${color};`" 
					name="calendar_today" 
					class="cursor-pointer"
				>
          <q-popup-proxy 
						ref="qDateProxy" 
					>
            <q-date
							v-model="text"
							minimal
							default-year-month="2002/01"
							default-view="Years"
							@navigation="onDateNavigation"
							@input="emitInputData()"
						/>
          </q-popup-proxy>
        </q-icon>
      </template>
		</q-input>
  </div>
</template>

<script>

export default {
	name: 'BaseTextInput',
	// components: {},
	// filters: {},
	props: {
		color: {
			type: String,
			default: null
		},
		label: {
			type: String,
			default: null,
		},
		type: {
			tpye: String,
			default: null,
		},
		rules: {
			type: Array,
			default: null,
		},
		readonly: {
			type: Boolean,
			default: false,
		},
	},
	data() {
		return {
			text: '',
		}
	},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		// 달력 선택시 연도를 선택하자 마자 캘린더를 꺼버리는 함수
		onDateNavigation(data) {
			this.text = data.year
			this.emitInputData()
			this.$refs.qDateProxy.hide()
		},
		emitInputData() {
			this.$emit('onInputValue', this.text)
			this.$emit('onValidate', this.$refs.input)
		}
	},
}
</script>

<style scoped>
.q-field {
	font-size: 1rem !important;
}

.q-field >>> .q-field__control {
  border-radius: 14px !important;
	height: 44px !important;
}

.q-field >>> .q-field__control:before {
  border: 1px solid #545FD6 !important;
}

.q-field >>> .q-field__append {
	padding-top: 3px;
}
</style>