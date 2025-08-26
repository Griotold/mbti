package com.example.mbti.domain.mbti;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseCreateRequest(
        String email,

        @NotBlank(message = "세션 ID는 필수입니다.")
        String sessionId,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 1 이상이어야 합니다")
        @Max(value = 7, message = "점수는 7 이하여야 합니다")
        Integer score
) {
}
