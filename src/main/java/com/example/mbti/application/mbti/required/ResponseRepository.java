package com.example.mbti.application.mbti.required;

import com.example.mbti.domain.mbti.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {

    // 기존 방식 (N+1 문제 있음)
    List<Response> findAllBySessionId(String sessionId);

    // N+1 없는 페치조인 방식
    @Query("SELECT r FROM Response r JOIN FETCH r.question WHERE r.sessionId = :sessionId")
    List<Response> findAllWithQuestionBySessionId(@Param("sessionId") String sessionId);
}
