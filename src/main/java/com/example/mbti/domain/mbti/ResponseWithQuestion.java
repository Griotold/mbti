package com.example.mbti.domain.mbti;

import jakarta.validation.constraints.*;

public record ResponseWithQuestion(
        @NotNull(message = "질문 ID는 필수입니다.")
        Long questionId,

        String email,

        @NotBlank(message = "세션 ID는 필수입니다.")
        String sessionId,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 1 이상이어야 합니다")
        @Max(value = 7, message = "점수는 7 이하여야 합니다")
        Integer score
) {
}