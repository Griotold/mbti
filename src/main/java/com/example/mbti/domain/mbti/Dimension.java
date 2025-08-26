package com.example.mbti.domain.mbti;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Dimension {
    EI("외향성 vs 내향성"),
    SN("감각 vs 직관"),
    FT("감정 vs 사고"),
    JP("판단 vs 인식"),

    ;

    private final String description;
}
