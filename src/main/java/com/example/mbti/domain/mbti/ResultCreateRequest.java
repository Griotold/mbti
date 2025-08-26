package com.example.mbti.domain.mbti;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResultCreateRequest(
        String email,

        @NotBlank(message = "세션 ID는 필수입니다.")
        String sessionId,

        @NotNull(message = "MBTI 유형은 필수입니다")
        MbtiType mbtiType,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer eScore,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer iScore,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer sScore,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer nScore,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer fScore,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer tScore,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer jScore,

        @NotNull(message = "점수는 필수입니다")
        @Min(value = 1, message = "점수는 0 이상이어야 합니다")
        Integer pScore
) {
}
