package com.example.mbti.application.mbti.provided;

import com.example.mbti.domain.mbti.Result;

public interface ResultModifier {

    /**
     * 세션ID로 Response(20개) 읽어서 점수 합산, MBTI 타입 계산 및 결과 저장
     * @param sessionId
     * @return Result
     */
    Result calculateAndSave(String sessionId);

}

