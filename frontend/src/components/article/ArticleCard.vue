<template>
 <div>
  <div class="row justify-between">
    
    <BaseProfileCard :user="article.user"/>

    <BaseKebabButton :size="0.8">
    <!-- TODO: 해당 글을 쓴 유저가 자신인지 판단하는 로직 추가해야함 -->
      <BaseMenu v-if="true" icon="front_hand" text="차단"/>
      <div v-else>
        <BaseMenu v-if="true" icon="front_hand" text="수정"/>
        <BaseMenu v-if="true" icon="front_hand" text="삭제"/>
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
        :likeCount="article.emojiCount[emoji]"
        :isLikeEmoji="article.likedEmoji === emoji"
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
	// data() {},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	// methods: {},
}
</script>

<style>

</style>