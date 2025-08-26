package com.example.mbti.domain.mbti;

import jakarta.validation.constraints.*;

public record QuestionCreateRequest(
        @NotBlank(message = "질문 내용은 필수입니다")
        @Size(max = 200, message = "질문 내용은 500자 이하로 입력해주세요")
        String content,

        @NotNull(message = "차원 선택은 필수입니다")
        Dimension dimension,

        @NotNull(message = "방향 선택은 필수입니다")
        Direction direction,

        @NotNull(message = "질문 순서는 필수입니다")
        @Min(value = 1, message = "질문 순서는 1 이상이어야 합니다")
        @Max(value = 20, message = "질문 순서는 20 이하여야 합니다")
        Integer sortOrder
) {
}
