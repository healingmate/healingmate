<template>
  <q-item style="padding: 0;">

		<q-item-section avatar>
			<BaseAvatar />
		</q-item-section>

		<q-item-section>
			<q-item-label lines="2" style="font-weight: bold; font-size: 1rem">
				{{ nickname }} <span v-if="isNotification" style="color: #959595; font-weight: bold;">님이 당신의 글에 반응을 남겼습니다.</span>
			</q-item-label>
			<q-item-label caption lines="1" style="font-weight: bold; font-size: 14px">
				{{ caption }}
			</q-item-label>
		</q-item-section>

		<q-item-section v-if="isLoginUser" side>
			<!-- 케밥 버튼이 들어갈 자리 -->
		</q-item-section>

	</q-item>
</template>

<script>
import BaseAvatar from "@/components/common/BaseAvatar"

export default {
	name: '',
	components: {
		BaseAvatar
	},
	// filters: {},
  // mixins: [],
	props: {
		nickname: {
			type: String,
			default: null
		},
		caption: {
			type: String,
			default: null
		},
		isLoginUser: {
			type: Boolean,
			default: false
		},
		isNotification: {
			type: Boolean,
			default: false
		},
	},
	// data() {
	//   return {		}
	// },
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	// methods: {},
}
</script>

<style>
.q-item__section--avatar {
    color: inherit;
    min-width: 0;
}

.q-item__section--side {
	padding-right: 12px;
}
</style>