package com.myproject.graduation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class VideoDataResponse {

    @Schema(description = "사고 차량 1 ID", example = "74")
    private int vehicleId1;

    @Schema(description = "사고 차량 2 ID", example = "92")
    private int vehicleId2;

    @Schema(description = "충돌 예상 시간까지의 남은 시간 (초)", example = "2.8")
    private double ttc;

    @Schema(description = "충돌 위치 위도", example = "37.6768")
    private double latitude;

    @Schema(description = "충돌 위치 경도", example = "126.74585")
    private double longitude;

    @Schema(description = "충돌 예측 시간", example = "2025-04-05T04:33:41.587")
    private LocalDateTime processedAt;

    public int getVehicleId1() {
        return vehicleId1;
    }

    public void setVehicleId1(int vehicleId1) {
        this.vehicleId1 = vehicleId1;
    }

    public int getVehicleId2() {
        return vehicleId2;
    }

    public void setVehicleId2(int vehicleId2) {
        this.vehicleId2 = vehicleId2;
    }

    public double getTtc() {
        return ttc;
    }

    public void setTtc(double ttc) {
        this.ttc = ttc;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
}
