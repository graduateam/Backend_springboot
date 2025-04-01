package com.myproject.graduation.repository;

import com.myproject.graduation.domain.VideoData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoDataRepository extends JpaRepository<VideoData, Long> {
    List<VideoData> findByUserId(Long userId);
}