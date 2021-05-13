<template>
  <span>
		<q-btn flat @click="onPressEmoji">
			<!-- 사용자가 좋아요 누른 이모지 이면 배경색이 검정! -->
      <q-avatar size="20px" :style="`${currentLikeEmoji === this.emoji ? 'filter: drop-shadow(1px 2px 5px blue);' : ''}`">
        <img :src="require(`../../assets/images/article-emoji-list/${emoji}.png`)">
      </q-avatar>
    </q-btn>
		<span>{{currentlikeCount[this.emoji]}}</span>
  </span>
</template>

<script>
import { postEmoji, modifyEmoji, deleteEmoji } from '@/api/article'


export default {
	name: 'ArticleEmoji',
	// components: {},
	// filters: {},
  // mixins: [],
	props: {
		likedEmoji: String,
		emoji:{
			type: String,
			default: null
		},
		likeCount: {
			type: Object,
			required: true,
		},
		articleId: {
			typeof: Number,
			required: true,
		}
	},
	data() {
		return {	
			currentLikeEmoji: this.likedEmoji,
			currentlikeCount: this.likeCount,
		}
	},
	// computed: {},
	watch: {
		likedEmoji() {
			this.currentLikeEmoji = this.likedEmoji
		},
		likeCount() {
			this.currentlikeCount = this.likeCount
		}
	},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		onPressEmoji() {
			const param = {
				'emoji' : this.emoji
			}

			if (this.currentLikeEmoji) {
				// 이모지 삭제
				if( this.currentLikeEmoji === this.emoji) {
					// console.log(this.emoji, "삭제")
					this.currentLikeEmoji = null
					this.currentlikeCount[this.emoji] -= 1
					this.$emit('emoji-deleted')
					deleteEmoji(this.articleId)
					// 이모지 수정
				} else {
					// console.log(this.emoji, "수정")
					this.currentLikeEmoji = this.emoji
					this.currentlikeCount[this.emoji] += 1
					this.$emit('emoji-modified', this.emoji)
					modifyEmoji(this.articleId, param)
					// .then(() => {
					// 	this.$emit('emoji-modified', this.emoji)
					// })
				}
			}	else {
				// 이모지 추가
				// console.log(this.emoji, "추가")
				this.currentLikeEmoji = this.emoji
				this.currentlikeCount[this.emoji] += 1
				this.$emit('emoji-posted', this.emoji)
				postEmoji(this.articleId, param)
			}
		}
	},
}
</script>

<style>
.emoji {
  height: 24px;
  width: 24px;
}

.q-btn__wrapper{
	padding: 4px 4px 5px 4px !important;
}

</style>