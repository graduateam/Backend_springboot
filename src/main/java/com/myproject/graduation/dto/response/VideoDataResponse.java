package com.myproject.graduation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoDataResponse {

    @Schema(description = "유저 ID", example = "1")
    private Long userId;

    @Schema(description = "사고 확률", example = "83.xx%")
    private double crashPercent;

    @Schema(description = "영상 처리 결과 데이터 (JSON)", example = "{\"result\": \"processed\"}")
    private String processedData;

    @Schema(description = "처리 시간", example = "2025-03-31T12:00:00")
    private String processedAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getCrashPercent() {
        return crashPercent;
    }

    public void setCrashPercent(double crashPercent) {
        this.crashPercent = crashPercent;
    }

    public String getProcessedData() {
        return processedData;
    }

    public void setProcessedData(String processedData) {
        this.processedData = processedData;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }
}
