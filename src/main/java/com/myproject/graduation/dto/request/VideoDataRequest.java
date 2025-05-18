package com.myproject.graduation.dto.request;

import com.myproject.graduation.domain.User;
import com.myproject.graduation.domain.VideoData;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class VideoDataRequest {
    private int vehicleId1;
    private int vehicleId2;
    private double ttc;
    private double latitude;
    private double longitude;
    private LocalDateTime processedAt;

    public VideoData toEntity(User user) {
        VideoData data = new VideoData();

        data.setUser(user);
        data.setVehicleId1(vehicleId1);
        data.setVehicleId2(vehicleId2);
        data.setTtc(ttc);
        data.setLatitude(latitude);
        data.setLongitude(longitude);
        data.setProcessedAt(processedAt);

        return data;
    }
}
