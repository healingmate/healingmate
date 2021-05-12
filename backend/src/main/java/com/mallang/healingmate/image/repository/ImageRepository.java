package com.mallang.healingmate.image.repository;

import com.mallang.healingmate.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
