package com.example.mbti.application.mbti.required;

import com.example.mbti.domain.mbti.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {

    // 세션ID로 결과 단건 조회
    Optional<Result> findBySessionId(String sessionId);

    // 세션ID 존재 여부 확인
    boolean existsBySessionId(String sessionId);
}
