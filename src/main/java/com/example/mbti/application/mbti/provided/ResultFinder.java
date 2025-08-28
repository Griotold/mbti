package com.example.mbti.application.mbti.provided;

import com.example.mbti.domain.mbti.Result;

public interface ResultFinder {

    /**
     * 세션 ID 로 결과 조회
     * @param sessionId
     * @return Result
     */
    Result findBySessionId(String sessionId);

    /**
     * 결과 존재 여부 확인(이미 해당 세션에 결과가 있나?)
     * @param sessionId
     * @return boolean
     */
    boolean existsBySessionId(String sessionId);

    /**
     * 결과 조회 또는 생성
     * 기존 결과가 있으면 반환, 없으면 새로 계산해서 반환
     * @param sessionId
     * @return Result
     */
    Result getOrCreateResult(String sessionId);
}
