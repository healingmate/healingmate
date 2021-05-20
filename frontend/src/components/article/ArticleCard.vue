<template>
 <div>
  <div class="row justify-between">
    
    <BaseProfileCard :user="article.user"/>

    <BaseKebabButton :size="0.8">
      <BaseMenu v-if="article.user.userId === this.$store.state.userId" icon="delete_outline" text="삭제" @click.native="onPostDelete" />
      <BaseMenu v-else icon="remove_circle_outline" text="차단" @click.native="onPostBanUser" />
    </BaseKebabButton>
  
  </div>
   
  <ArticleCardContent :content="article.content" />

  <ArticleCarousel 
    v-if="article.articleImages"
    :number="article.articleImages.length > 1 ? 2 : 1">
    <ArticleCarouselItem 
      v-for="(articleImage, index) in article.articleImages" :key="index" 
      :article-image="baseUrl+'healingmate-image/' + articleImage"
    />
  </ArticleCarousel>
  
  <div class="row justify-between">
    <div>
      <ArticleEmoji
        v-for="(emoji, index) in Object.keys(article.emojiCount)" :key="index"
        :emoji="emoji"
        :likeCount="currentLikeCounts"
        :likedEmoji="currentLikeEmoji"
        :articleId="article.articleId"
        @emoji-modified="onEmojiModified"
        @emoji-deleted="onEmojiDeleted"
        @emoji-posted="onEmojiPosted"
      />
    </div>
    <span class="self-center" style="padding-right: 4px; color: #959595;">{{ article.createdDate | formatDate }}</span>
  </div>

 </div>
</template>

<script>
import BaseProfileCard from '@/components/common/BaseProfileCard'
import BaseKebabButton from '@/components/common/BaseKebabButton'
import BaseMenu from '@/components/common/BaseMenu'
import ArticleCardContent from '@/components/article/ArticleCardContent'
import ArticleCarousel from '@/components/article/ArticleCarousel'
import ArticleCarouselItem from '@/components/article/ArticleCarouselItem'
import ArticleEmoji from '@/components/article/ArticleEmoji'
import { deleteArticle } from '@/api/article'
import { banUser } from '@/api/user'
import { formatDate } from '@/utils/filters'

const BASE_URL = process.env.VUE_APP_BASE_URL

export default {
	name: 'ArticleCard',
	components: {
    BaseProfileCard,
    BaseKebabButton,
    BaseMenu,
    ArticleCardContent,
    ArticleCarousel,
    ArticleCarouselItem,
    ArticleEmoji,
  },
	filters: {
    formatDate(value) {
      return formatDate(value);
    },
  },
  // mixins: [],
	props: {
    article: {
			type: Object,
			default: null,
      baseUrl: null,
		},
  },
	data() {
    return {
      currentLikeEmoji: this.article.likedEmoji,
      currentLikeCounts: this.article.emojiCount,
    }
  },
	// computed: {},
	// watch: {},
	created() {
    this.baseUrl = BASE_URL
  },
	// mounted() {},
	// updated() {},
	methods: {
    // 이모지가 수정됬다는 신호가 오면
    onEmojiModified(modifiedEmoji) {
      // console.log("이모지 수정 에밋 왔음", modifiedEmoji)
      // 기존 사용자가 찍었던 이모지의 좋아요 카운트를 하나 줄이고
      this.currentLikeCounts[this.currentLikeEmoji] -= 1
      // 현재 좋아요 하는 이모지도 수정된 이모지로 바꾼다.
      this.currentLikeEmoji = modifiedEmoji
    },
    onEmojiDeleted() {
      // console.log("이모지 삭제 에밋 왔음")
      this.currentLikeEmoji = null
    },
    onEmojiPosted(postedEmoji) {
      // console.log("이모지 추가 에밋 왔음")
      this.currentLikeEmoji = postedEmoji
    },
    onPostDelete() {
      this.$q.dialog({
        message: '게시글을 정말 삭제하시겠습니까?',
        cancel: true,
        html: true,
        persistent: true,
        position: 'bottom',
      }).onOk(() => {
        deleteArticle(this.article.articleId)
        .then(() => {
          this.$emit('on-delete', this.article)
          this.$q.notify({
            position: 'top',
            color: 'primary',
            message: '게시글이 삭제되었습니다..'
          })
        })
        .catch(err => {
          console.log(err)
        })
      }).onCancel(() => {
      }).onDismiss(() => {
      })
    },
    onPostBanUser() {
      this.$q.dialog({
        message: '해당 유저와는 서비스 내에서 만날 수 없습니다. <br> 정말 차단하시겠습니까?',
        cancel: true,
        html: true,
        persistent: true,
        position: 'bottom',
      }).onOk(() => {
        banUser(this.article.user.userId)
        .then(() => {
          this.$q.notify({
            position: 'top',
            color: 'primary',
            message: '해당 유저가 차단되었습니다.'
          })
        })
        .catch(err => {
          console.log(err)
        })
      }).onCancel(() => {
      }).onDismiss(() => {
      })
    }
  },
}
</script>

<style>

</style>