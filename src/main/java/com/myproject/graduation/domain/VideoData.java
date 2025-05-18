package com.myproject.graduation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "vehicle_id_1", nullable = false)
    private int vehicleId1;

    @Column(name = "vehicle_id_2", nullable = false)
    private int vehicleId2;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "ttc", nullable = false)
    private double ttc;

    @Column(name = "processed_at", nullable = false)
    private LocalDateTime processedAt;
}