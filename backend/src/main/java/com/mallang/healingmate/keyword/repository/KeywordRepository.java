package com.mallang.healingmate.keyword.repository;

import com.mallang.healingmate.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword,Long> {
    List<Keyword> findByKeywordIn(List<String> keywords);
}
