package com.example.mbti.application.mbti.provided;

import com.example.mbti.domain.mbti.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
record ResultModifierTest(ResultModifier resultModifier,
                          EntityManager entityManager) {

    @Test
    void calculateAndSave_20개응답으로_정확한MBTI계산_및_저장성공() {
        // Given: 20개 질문과 응답 준비 (ENFP가 나오도록 설정)
        String sessionId = "test-session-123";
        String email = "test@example.com";
        
        List<Question> questions = QuestionFixture.create20Questions();
        List<Response> responses = ResponseFixture.create20ResponsesForENFP(sessionId, email, questions);
        
        // 질문들을 DB에 저장
        questions.forEach(entityManager::persist);
        // 응답들을 DB에 저장  
        responses.forEach(entityManager::persist);
        entityManager.flush();

        // When: 결과 계산 및 저장
        Result result = resultModifier.calculateAndSave(sessionId);

        // Then: 결과 검증
        assertThat(result).isNotNull();
        assertThat(result.getSessionId()).isEqualTo(sessionId);
        assertThat(result.getMbtiType()).isEqualTo(MbtiType.ENFP);
        assertThat(result.getId()).isNotNull(); // DB에 저장되어 ID가 생성됨
        
        assertThat(result.getEScore()).isEqualTo(18);
        assertThat(result.getIScore()).isEqualTo(5);
        assertThat(result.getSScore()).isEqualTo(5);
        assertThat(result.getNScore()).isEqualTo(20);
        assertThat(result.getFScore()).isEqualTo(15);
        assertThat(result.getTScore()).isEqualTo(10);
        assertThat(result.getJScore()).isEqualTo(8);
        assertThat(result.getPScore()).isEqualTo(20);
    }

    @Test
    void calculateAndSave_다른MBTI타입_ISTJ_정확히계산된다() {
        // Given: ISTJ가 나오도록 설정된 20개 응답
        String sessionId = "istj-session";
        String email = "istj@example.com";
        
        List<Question> questions = QuestionFixture.create20Questions();
        List<Response> responses = ResponseFixture.create20ResponsesForISTJ(sessionId, email, questions);
        
        questions.forEach(entityManager::persist);
        responses.forEach(entityManager::persist);
        entityManager.flush();

        // When
        Result result = resultModifier.calculateAndSave(sessionId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getMbtiType()).isEqualTo(MbtiType.ISTJ);
        assertThat(result.getSessionId()).isEqualTo(sessionId);
    }

}
