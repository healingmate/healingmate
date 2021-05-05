package com.mallang.healingmate.article.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.AccountBan;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.dto.request.ArticleRequest;
import com.mallang.healingmate.article.dto.response.AccountResponse;
import com.mallang.healingmate.article.dto.response.ArticleResponse;
import com.mallang.healingmate.article.dto.response.EmojiCount;
import com.mallang.healingmate.article.dto.response.EntireArticleResponse;
import com.mallang.healingmate.article.repository.AccountBanRepository;
import com.mallang.healingmate.article.repository.ArticleRepository;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import com.mallang.healingmate.emoji.domain.Emoji;
import com.mallang.healingmate.emoji.repository.AccountArticleEmojiRepository;
import com.mallang.healingmate.image.domain.ArticleImage;
import com.mallang.healingmate.image.domain.ArticleImages;
import com.mallang.healingmate.image.domain.Image;
import com.mallang.healingmate.image.repository.ArticleImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * com.mallang.healingmate.article.domain
 * ArticleService.java
 *
 * @author 서범석
 * @date 2021-04-29 오전 2:51
 *
 * @변경이력
 * 21-05-06
 * 모든 메서드 1차 기능 확인 완료 ( 유저 프로필, 키워드 적용해서 기능테스트 필요 )
 **/
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class ArticleService {

    @Value("${file.path}")
    private String baseFilePath;

    private final ArticleRepository articleRepository;
    private final AccountBanRepository accountBanRepository;
    private final AccountArticleEmojiRepository accountArticleEmojiRepository;
    private final ArticleImageRepository articleImageRepository;

    public void saveArticle(ArticleRequest articleRequest, Account account) {
        Article article = articleRequest.to(articleRequest, account);

        if (articleRequest.getImages() != null && articleRequest.getImages().length != 0) {
            MultipartFile[] images = articleRequest.getImages();
            if (images.length > 5) log.warn("amount of images is over 5. only first 5 images will be saved");
            int saveCnt = 0;
            while (saveCnt < images.length) {
                Long id = articleRepository.findTopByOrderByIdDesc().isPresent() ? articleRepository.findTopByOrderByIdDesc().get().getId() : 1L;

                String folderPath = baseFilePath + account.getId() + "\\article-images\\";
                String imagePath = id + "_" + images[saveCnt].getOriginalFilename();
                try {
                    File folder = new File(folderPath);
                    folder.mkdirs();
                    Files.write(Path.of(folderPath + imagePath), images[saveCnt].getBytes()); // 하드디스크 기록
                } catch (IOException e) {
                    log.error("failed to save images. error-messages" + e);
                    e.printStackTrace();
                    // TODO : 파일 저장 실패 exception
                    return;
                }
                Image image = Image.builder().path(account.getId() + "\\article-images\\" + imagePath).build();
                article.addArticleImages(new ArticleImage(article, image));
                saveCnt++;
            }
        }
        articleRepository.save(article);
        log.info("success to save");
    }

    public void updateArticle(String content, Long articleId, Account account) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (!articleOptional.isPresent()) {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }

        Article article = articleOptional.get();

        if (!article.getAccount().getId().equals(account.getId())) {
            log.error("No permission");
            throw new EntityException(ErrorCode.UNAUTHORIZED_ACCOUNT);
        }
        article.update(content);
    }

    public void deleteArticle(Long articleId, Account account) {

        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (!articleOptional.isPresent()) {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }

        Article article = articleOptional.get();

        if (article.getAccount().getId() != account.getId()) {
            log.error("No permission");
            throw new EntityException(ErrorCode.UNAUTHORIZED_ACCOUNT);
        }
        // TODO : 연관관계를 가진 모든 요소 제거
        List<ArticleImage> articleImages = articleImageRepository.findByArticle(article);
        List<Image> images = ArticleImages.toImages(articleImages);
        for (int i = 0; i < images.size(); i++) {
            File file = new File(baseFilePath + "\\" + images.get(i).getPath());
            if (file.exists()) {
                file.delete();
            } else {
                log.warn("file not exist");
            }
        }
        // TODO : 연관된 나머지 항목은 연쇄적으로 삭제된다
        articleRepository.delete(article);
    }

    public EntireArticleResponse findAllArticles(int page, int size, Account account) {
        // 페이징을 구현하기 위한 Pageable객체 생성
        Pageable pageable = PageRequest.of(page, size);
        List<AccountBan> accountBans = accountBanRepository.findByBanFrom(account);
        List<Account> accounts = accountBans.stream().map(accountBan -> accountBan.getBanTo())
                .collect(Collectors.toList());
        Page<Article> articlePage = articleRepository.findAllByAccountNotIn(accounts, pageable);
        if (page >= articlePage.getTotalPages()) {
            log.error("유효하지 않은 page값입니다");
            throw new EntityException(ErrorCode.INVALID_INPUT_VALUE);
        }

        List<Article> articles = articlePage.getContent();

        List<ArticleResponse> articleResponses = new ArrayList<>();
        // TODO: 해당 유저가 반응을 남겼는지 파악
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            // 게시글 기본 정보 입력
            ArticleResponse articleResponse = ArticleResponse.of(article);
            // User정보에 필요한 정보 입력
            articleResponse.setAccountResponse(AccountResponse.of(account));
            // 현재 Account정보와 게시글 작성자를 비교해서 본인의 게시물인지 여부를 입력
            articleResponse.setSelf(article.getAccount().getId() == account.getId());
            // 내가 반응을 남긴 글인지 여부 반환
            Optional<AccountArticleEmoji> accountArticleEmojiOptional = accountArticleEmojiRepository.findByArticleAndAccount(article, account);
            if (accountArticleEmojiOptional.isPresent()) {
                articleResponse.setLikedEmoji(accountArticleEmojiOptional.get().getEmoji());
            }
            // 해당 게시글의 이미지 정보 반환
            List<ArticleImage> articleImages = articleImageRepository.findByArticle(article);
            if (articleImages != null && articleImages.size() > 0) {
                // TODO : Article와 ArticleImages, 또는 따로 참조하는 Images
                articleResponse.setArticleImages(ArticleImages.toStrings(articleImages));
            }
            // 해당 글에 남긴 반응 갯수 파악
            articleResponse.setEmojiCount(
                    EmojiCount.builder()
                            .amazed(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.AMAZED))
                            .crying(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.CRYING))
                            .grinning(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.GRINNING))
                            .lovely(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.LOVELY))
                            .smile(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.SMILE))
                            .build()
            );
            articleResponses.add(articleResponse);
        }

        return EntireArticleResponse.builder()
                .articleResponses(articleResponses)
                .isLast(articlePage.isLast())
                .build();
    }
}
