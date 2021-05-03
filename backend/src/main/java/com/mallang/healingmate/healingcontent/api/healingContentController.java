package com.mallang.healingmate.healingcontent.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "healing-contents", description = "힐링컨텐츠 관련 컨트롤러")
@RestController
@RequestMapping("/healing-content/content")
public class healingContentController {
//    @PostMapping("/content/")
}
