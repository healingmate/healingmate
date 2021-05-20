<template>
  <div>

    <!-- 이미지 등록 버튼 -->
    <div style="margin-top: 20px; width: 100%;">
      <q-btn
        class="image-input-button"
        stack
        @click="onClickPostImageButton"
      >
        <q-icon name="image" style="color: #545FD6"/>
        <span style="font-size:0.5rem">
          클릭해서 이미지를 최대 5장 까지 업로드 할 수 있어요
        </span>
      </q-btn>
    </div>

    <!-- 이미지 등록 버튼 시 등장하는 다이얼로그 -->
    <q-dialog
      ref="dialog"
      v-model="showDialog"
      @show="onShowDialog"
    >
      <div style="box-shadow: none;">
        <q-card>
          <Croppa
            v-if="showDialog"
            v-model="croppedImage"
            ref="croppa"
            initial-size="cover"
            placeholder="사진을 선택해주세요."
            :width="300"
            :height="300"
            :show-remove-button="false"
            :file-size-limit="1024 * 1024 * 20"
            :prevent-white-space="true"
            :zoom-speed="10"
            style="line-height: 0;"
          />
        </q-card>

        <span style="font-size: 10px; color: #ffffff;">사진을 확대/축소할 수 있습니다.</span>

        <div class="row justify-around" style="margin-top: 50px;">
            <BaseButton width="40%" text-color="#c10015" back-ground-color="#ffffff" icon="close" @click.native="closeDialog"/>
            <BaseButton width="40%" text-color="#545FD6" back-ground-color="#ffffff" icon="check" @click.native="onClickSelect"/>
        </div>
      </div>
    </q-dialog>

    <!-- 등록할 이미지를 미리 볼 수 있는 캐러셀 -->
    <div style="padding: 20px 0;">
      <ArticleCarousel v-if="articleImages" :key="rerenderTriggerIndex" :number="5">
        <ArticleCarouselItem
          v-for="(articleImage, index) in articleImages" :key="index" 
          :article-image="articleImage.generateDataUrl('image/jpeg', 0.8)"
          :index="index"
          :deletable="true"
          @onDeleteItemIndex="onDeleteItem"
        />
      </ArticleCarousel>
    </div>
   
    <!-- <div class="absolute" style="bottom: 20px; width: 100%;">
     <BaseButton label="작성하기" />
    </div> -->

    <div style="width: 100%;">
			<BaseButton label="작성하기" @click.native="onClickPost"/>
		</div>

  </div>
</template>

<script>
import BaseButton from '@/components/common/BaseButton'
import ArticleCarousel from '@/components/article/ArticleCarousel'
import ArticleCarouselItem from '@/components/article/ArticleCarouselItem'
import Croppa from 'vue-croppa'
import { Notify } from 'quasar'

export default {
	name: 'ArticleImageInput',
	components: {
    'Croppa' : Croppa.component,
    BaseButton,
    ArticleCarousel,
    ArticleCarouselItem,
  },
	// filters: {},
  // mixins: [],
	// props: {},
	data() {
		return {
			croppedImage: null,
      showDialog: false,
      articleImages: [],
      rerenderTriggerIndex: 0,
		}
	},
	// computed: {},
	// watch: {},
	// created() {},
	// mounted() {},
	// updated() {},
	methods: {
		onClickPostImageButton() {
      if (this.articleImages.length > 4) {
        Notify.create({
          position: 'top',
          color: 'negative',
          message: '사진을 5장 이상 올릴 수 없습니다.'
        })
      } else {
        this.showDialog = true
      }
    },
    onShowDialog() {
      this.$refs.croppa.chooseFile()
    },
    closeDialog() {
      this.$refs.dialog.hide()
    },
    onClickSelect() {
      this.articleImages.push(this.croppedImage)
      this.rerenderTriggerIndex += 1
      this.closeDialog()
    },
    onDeleteItem(index) {
      this.articleImages.splice(index, 1)
      this.rerenderTriggerIndex += 1
    },
    onClickPost() {
      this.$emit('on-post', this.articleImages)
    },
	},
}
</script>

<style scoped>
.q-btn--rectangle {
  border-radius: 8px !important;
}

.image-input-button {
  background: #ffffff; 
  color: #9C9C9C; 
  width: 100%; 
  height: 80px; 
  margin-bottom: 
  0px; border: 1px 
  dashed #545FD6;
}

</style>