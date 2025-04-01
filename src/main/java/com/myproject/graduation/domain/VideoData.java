package com.myproject.graduation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video_data")
@Getter
@Setter
@NoArgsConstructor
public class VideoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "processed_data", nullable = false, columnDefinition = "TEXT")
    private String processedData;

    @Column(name = "processed_at", nullable = false)
    private String processedAt;
}