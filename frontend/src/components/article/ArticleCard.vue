<template>
 <div>
   
  <BaseProfileCard :user="article.user"/>
   
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
import BaseProfileCard from "@/components/common/BaseProfileCard"
import ArticleCardContent from "@/components/article/ArticleCardContent"
import ArticleCarousel from "@/components/article/ArticleCarousel"
import ArticleCarouselItem from "@/components/article/ArticleCarouselItem"
import ArticleEmoji from "@/components/article/ArticleEmoji"

export default {
	name: 'ArticleCard',
	components: {
    BaseProfileCard,
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
	created() {
    console.log(this.article.emojiCount)
  },
	// mounted() {},
	// updated() {},
	// methods: {},
}
</script>

<style>

</style>