<template>
 <div>
  <div class="row justify-between">
    
    <BaseProfileCard :user="article.user"/>

    <BaseKebabButton :size="0.8">
    <!-- TODO: 해당 글을 쓴 유저가 자신인지 판단하는 로직 추가해야함 -->
      <BaseMenu v-if="false" icon="front_hand" text="차단"/>
      <div v-else>
        <BaseMenu v-if="true" icon="front_hand" text="수정"/>
        <BaseMenu v-if="true" icon="front_hand" text="삭제" @click.native="onPostDelete"/>
      </div>
    </BaseKebabButton>
  
  </div>
   
  <ArticleCardContent :content="article.content" />

  <ArticleCarousel 
    v-if="article.articleImages"
    :number="article.articleImages.length > 1 ? 2 : 1">
    <ArticleCarouselItem 
      v-for="(articleImage, index) in article.articleImages" :key="index" 
      :article-image="articleImage"
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
    <span class="self-center" style="padding-right: 4px; color: #959595;">{{ article.createdDate }}</span>
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
	// filters: {},
  // mixins: [],
	props: {
    article: {
			type: Object,
			default: null
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
	// created() {},
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
      // TODO: dialog로 바꿔야함
      this.$q.notify({
        position: 'top',
        message: '정말 삭제하시겠어요?',
        color: 'primary',
        actions: [
          { label: '아니오', color: 'white', handler: () => { /* ... */ } },
          { label: '네', color: 'white', handler: () => { /* ... */ } }
        ]
      })
    },
  },
}
</script>

<style>

</style>