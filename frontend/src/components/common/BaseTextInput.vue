<template>
  <div>
		<p :style="`color: ${color}; font-size: 1rem;`">{{label}}</p>
    <q-input 
			:readonly="type === 'birthYear'" 
			outlined
			v-model="text" 
			:dense="true" 
			:type="type" 
			style="margin-bottom: 20px;"
			:rules="rules"
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
							@navigation="onDateNavigation"
							minimal
							v-model="text"
							default-year-month="2002/01"
							default-view="Years"
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
		}
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
		onDateNavigation(data) {
			this.text = data.year
			this.$refs.qDateProxy.hide()
		}
	},
}
</script>

<style>
.q-field__control {
  border-radius: 14px !important;
}

.q-field__control:before {
  border: 1px solid #545FD6 !important;
}

.q-field__control {
	height: 44px !important;
}

.q-field {
	font-size: 1rem !important;
}

.q-field__append {
	padding-top: 3px;
}
</style>