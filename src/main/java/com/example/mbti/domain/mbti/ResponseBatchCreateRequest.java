package com.example.mbti.domain.mbti;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ResponseBatchCreateRequest(
        @NotEmpty(message = "최소 하나 이상의 응답이 필요합니다.")
        @Valid
        List<ResponseWithQuestion> items
) {
}