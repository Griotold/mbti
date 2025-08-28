package com.example.mbti.application.mbti.provided;


import com.example.mbti.application.mbti.required.ResultRepository;
import com.example.mbti.domain.mbti.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
record ResultFinderTest(ResultFinder resultFinder,
                        ResultRepository resultRepository,
                        EntityManager entityManager) {

    @Test
    void findBySessionId() {
        Result result = resultRepository.save(ResultFixture.createResult());

        entityManager.flush();
        entityManager.clear();

        Result found = resultFinder.findBySessionId(result.getSessionId());

        assertThat(found.getId()).isEqualTo(result.getId());
        assertThat(found.getSessionId()).isEqualTo(result.getSessionId());
        assertThat(found.getMbtiType()).isEqualTo(result.getMbtiType());
        assertThat(found.getEScore()).isEqualTo(result.getEScore());
    }

    @Test
    void findBySessionId_없는_sessionId() {

        assertThatThrownBy(() -> resultFinder.findBySessionId("없는 sessionId"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void existsBySessionId() {
        Result result = resultRepository.save(ResultFixture.createResult());

        entityManager.flush();
        entityManager.clear();

        boolean value = resultFinder.existsBySessionId(result.getSessionId());
        assertThat(value).isTrue();
    }

    @Test
    void existsBySessionId_없는_sessionId() {

        boolean value = resultFinder.existsBySessionId("없는 sessionId");
        assertThat(value).isFalse();
    }

    @Test
    void getOrCreateResult_기존결과있음() {
        // Given: 이미 결과가 존재하는 상황
        Result existingResult = resultRepository.save(ResultFixture.createResult());

        entityManager.flush();
        entityManager.clear();

        // When: getOrCreateResult 호출
        Result result = resultFinder.getOrCreateResult(existingResult.getSessionId());

        // Then: 기존 결과를 그대로 반환
        assertThat(result.getId()).isEqualTo(existingResult.getId());
        assertThat(result.getSessionId()).isEqualTo(existingResult.getSessionId());
        assertThat(result.getMbtiType()).isEqualTo(existingResult.getMbtiType());
        assertThat(result.getEScore()).isEqualTo(existingResult.getEScore());
    }

    @Test
    void getOrCreateResult_기존결과없음_새로계산() {
        // Given: 결과가 없고, 20개 응답은 있는 상황
        String sessionId = "new-session-for-calculation";

        // 20개 질문 생성 및 저장
        List<Question> questions = QuestionFixture.create20Questions();
        questions.forEach(entityManager::persist);

        // 20개 응답 생성 및 저장 (ENFP가 나오도록)
        List<Response> responses = ResponseFixture.create20ResponsesForENFP(sessionId, questions);
        responses.forEach(entityManager::persist);

        entityManager.flush();

        // When: getOrCreateResult 호출
        Result result = resultFinder.getOrCreateResult(sessionId);

        // Then: 새로 계산된 결과 반환
        assertThat(result).isNotNull();
        assertThat(result.getSessionId()).isEqualTo(sessionId);
        assertThat(result.getMbtiType()).isEqualTo(MbtiType.ENFP);
        assertThat(result.getId()).isNotNull(); // DB에 저장되어 ID 생성됨

        // 실제로 DB에 저장되었는지 확인
        boolean exists = resultFinder.existsBySessionId(sessionId);
        assertThat(exists).isTrue();
    }

}