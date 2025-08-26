package com.example.mbti.domain.mbti;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Direction {
    // 외향성 vs 내향성
    E("외향성", "에너지를 외부 세계에서 얻음"),
    I("내향성", "에너지를 내면 세계에서 얻음"),

    // 감각 vs 직관
    S("감각", "현재와 구체적 사실에 집중"),
    N("직관", "미래와 가능성에 집중"),

    // 감정 vs 사고
    F("감정", "개인적 가치와 감정 중심 판단"),
    T("사고", "논리적이고 객관적 판단"),

    // 판단 vs 인식
    J("판단", "계획적이고 체계적인 삶"),
    P("인식", "융통성 있고 자발적인 삶");

    private final String displayName;
    private final String description;

    // 해당 방향이 속한 차원 반환
    public Dimension getDimension() {
        return switch (this) {
            case E, I -> Dimension.EI;
            case S, N -> Dimension.SN;
            case F, T -> Dimension.FT;
            case J, P -> Dimension.JP;
        };
    }

    // 차원과 일치하는지 검증
    public boolean isValidForDimension(Dimension dimension) {
        return this.getDimension() ==  dimension;
    }
}
