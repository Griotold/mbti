package com.example.mbti.application.mbti.provided;


import com.example.mbti.application.mbti.required.ResultRepository;
import com.example.mbti.domain.mbti.Result;
import com.example.mbti.domain.mbti.ResultFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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
}