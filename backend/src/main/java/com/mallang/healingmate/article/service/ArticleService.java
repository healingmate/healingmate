package com.mallang.healingmate.article.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.AccountBan;
import com.mallang.healingmate.account.dto.response.AccountResponse;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.article.domain.AccountArticles;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.dto.request.ArticleRequest;
import com.mallang.healingmate.article.dto.response.ArticleResponse;
import com.mallang.healingmate.article.dto.response.EmojiCount;
import com.mallang.healingmate.article.dto.response.EntireArticleResponse;
import com.mallang.healingmate.account.repository.AccountBanRepository;
import com.mallang.healingmate.article.repository.ArticleRepository;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.common.exception.InputValueException;
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
 * @author ?????????
 * @date 2021-04-29 ?????? 2:51
 * @???????????? 21-05-06
 * ?????? ????????? 1??? ?????? ?????? ?????? ( ?????? ?????????, ????????? ???????????? ??????????????? ?????? )
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

    public void saveArticle(ArticleRequest articleRequest, Account account) {
        Article article = articleRequest.to(account);

        Long articleId = articleRepository.save(article).getId();
        log.info("Article is successfully saved");

        // TODO : ????????? ????????? ?????????
        if (articleRequest.getImages() != null && articleRequest.getImages().length != 0) {
            MultipartFile[] images = articleRequest.getImages();
            if (images.length > 5) {
                log.error("Number of images is over 5. Please reduce number of images");
                throw new InputValueException(ErrorCode.INCORRECT_IMAGE_LENGTH);
            }

            Integer saveCnt = 0;
            while (saveCnt < images.length) {
                String folderPath = baseFilePath + account.getId() + "/article-images/";
                String imagePath = articleId + "_" + saveCnt;
                try {
                    File folder = new File(folderPath);
                    log.info("Is folder created? : " + folder.mkdirs());
                    // ??????????????? ??????
                    log.info("Image saved successfully. image info : " + Files.write(Path.of(folderPath + imagePath), images[saveCnt].getBytes()));
                } catch (IOException e) {
                    log.error("Failed to save images. error-messages" + e);
                    e.printStackTrace();
                    return;
                }
                Image image = Image.builder().path(account.getId() + "/article-images/" + imagePath).build();
                article.addArticleImages(image);
                saveCnt++;
            }
            log.info("All requested images are saved");
        } else {
            log.info("Image List is null or Empty");
        }
    }

    public void updateArticle(String content, Long articleId, Account account) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        });

        if (!article.getAccount().getId().equals(account.getId())) {
            log.error("No permission");
            throw new EntityException(ErrorCode.UNAUTHORIZED_ACCOUNT);
        }
        article.update(content);
    }

    public void deleteArticle(Long articleId, Account account) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        });

        if (!article.getAccount().getId().equals(account.getId())) {
            log.error("No permission");
            throw new EntityException(ErrorCode.UNAUTHORIZED_ACCOUNT);
        }
        ArticleImages articleImages = article.getArticleImages();
        List<String> images = articleImages.toImagePaths();
        for (int i = 0; i < articleImages.getArticleImages().size(); i++) {
            File file = new File(baseFilePath + "/" + images.get(i));
            if (file.exists()) {
                log.info("Is file deleted? : " + file.delete());
            } else {
                log.error("file not exist");
            }
        }
        articleRepository.delete(article);
    }

    public EntireArticleResponse findAllArticles(Long cursorId, Integer size, Account account) {
        // ???????????? ???????????? ?????? Pageable?????? ??????
        Pageable pageable = PageRequest.of(0, size);
        List<AccountBan> accountBans = accountBanRepository.findByBanFrom(account);
        List<Account> accounts = accountBans.stream().map(accountBan -> accountBan.getBanTo())
                .collect(Collectors.toList());
        Page<Article> articlePage = null;
        if(cursorId == 0){
            if(!accounts.isEmpty()){
                articlePage = articleRepository.findAllByAccountNotInOrderByIdDesc(accounts, pageable);
            }else{
                articlePage = articleRepository.findAllByOrderByIdDesc(pageable);
            }
        }else{
            if(!accounts.isEmpty()){
                articlePage = articleRepository.findAllByIdLessThanAndAccountNotInOrderByIdDesc(cursorId, accounts, pageable);
            }else{
                articlePage = articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
            }
        }

        List<Article> articles = articlePage.getContent();

        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            // ????????? ?????? ?????? ??????
            ArticleResponse articleResponse = ArticleResponse.of(article);
            // User????????? ????????? ?????? ??????
            articleResponse.setAccountResponse(AccountResponse.of(article.getAccount()));
            // ?????? Account????????? ????????? ???????????? ???????????? ????????? ??????????????? ????????? ??????
            articleResponse.setSelf(article.getAccount().getId().equals(account.getId()));
            // ?????? ????????? ?????? ????????? ?????? ?????? ( find in the list )
            Optional<AccountArticleEmoji> accountArticleEmojiOptional = accountArticleEmojiRepository.findByArticleAndAccount(article, account);
            if (accountArticleEmojiOptional.isPresent()) {
                articleResponse.setLikedEmoji(accountArticleEmojiOptional.get().getEmoji());
            }
            // ?????? ?????? ?????? ?????? ?????? ??????
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
