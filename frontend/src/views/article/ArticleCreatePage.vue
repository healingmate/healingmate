<template>
	<div class="vh-100 p-y-28">

		<!-- 뒤로가기 버튼 -->
		<TheGoBackButton color="dark"/>
		
		<!-- 헤더 문구 -->
		<div class="text-h6 text-bold q-pa-sm q-py-xl">
        <p class="q-mb-none">오늘은 어떤 방법으로</p>
        <p class="q-mb-none">힐링하셨나요?🍀</p>
    </div>

		<!-- 텍스트 인풋 -->
		<q-input 
			v-model="text"
			placeholder="힐링 방법을 공유해주세요" 
			outlined
			type="textarea"
			rows="8"
		/>

		<ArticleImageInput @on-post="onPost"/>

	</div>
</template>

<script>
import TheGoBackButton from '@/components/common/TheGoBackButton'
import ArticleImageInput from '@/components/article/ArticleImageInput'
import { postArticle } from '@/api/article'
import { Notify } from 'quasar'

export default {
	name: 'ArticleCreatePage',
	components: {
		TheGoBackButton,
		ArticleImageInput,
	},
	// filters: {},
  // mixins: [],
	// props: {},
	data() {
		return {
			text: null,
		}
	},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		onPost(aritcleImages) {
			// 작성 게시글의 사진을 FormData에 담아 보냄
			const formData = new FormData()

			aritcleImages.forEach(image => formData.append('image', image))

			const param = {
				'images': formData,
				'content' : this.text
			}
			// TODO: 게시글 또는 사진 둘중 하나라도 있어야 POST 요청을 할수 있게 해야 함
			postArticle(param)
			.then(() => {
				Notify.create({
          position: 'top',
          color: 'primary',
          message: '힐링 방법이 공유되었습니다.'
        })
				this.$router.push({name: 'ArticleFeedPage'})
			})
			.catch(err => {
				console.log(err.response)
			})
		}
	},
}
</script>

<style scoped>
.q-field {
	font-size: 12px !important;
}

.q-field >>> .q-field__control {
  border-radius: 8px !important;
}

.q-field >>> .q-field__control:before {
  border: 1px solid #545FD6 !important;
}

.q-textarea >>> .q-field__native {
	padding-top: 6px;
}
</style>