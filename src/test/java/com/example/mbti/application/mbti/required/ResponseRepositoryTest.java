package com.example.mbti.application.mbti.required;

import com.example.mbti.domain.mbti.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles
@SpringBootTest
@Transactional
record ResponseRepositoryTest(ResponseRepository responseRepository,
                              EntityManager em) {

    @BeforeEach
    void setUp() {
        // 질문 2개(EI, SN)와 응답 2개 insert
        Question q1 = QuestionFixture.createQuestion("q1", Dimension.EI, Direction.E, 1);
        Question q2 = QuestionFixture.createQuestion("q2", Dimension.SN, Direction.N, 2);
        em.persist(q1);
        em.persist(q2);

        String sessionId = "sessionId";
        Response r1 = ResponseFixture.createResponse("user1@test", sessionId, 7, q1);
        Response r2 = ResponseFixture.createResponse("user2@test", sessionId, 5, q2);
        em.persist(r1);
        em.persist(r2);
        em.flush();
        em.clear();
    }

    @Test
    void findAllBySessionId_N플러스1_확인() {
        String sessionId = "sessionId";
        List<Response> responses = responseRepository.findAllBySessionId(sessionId);

        // 첫 번째 Response에서 getQuestion를 호출하면(== LAZY) select가 추가로 한번 더 발생
        Question q1 = responses.get(0).getQuestion();
        Question q2 = responses.get(1).getQuestion();
        System.out.println(q1.getClass());
        System.out.println(q1.getClass());

        assertThat(q1).isNotNull();
        assertThat(q2).isNotNull();
        // 테스트 실행 시 SQL 쿼리가 response 단건 조회 후 question 각각 1번씩 추가로 나가는지 로그에서 직접 확인!
    }

    @Test
    void findAllWithQuestionBySessionId_fetchJoin_확인() {
        String sessionId = "sessionId";
        List<Response> responses = responseRepository.findAllWithQuestionBySessionId(sessionId);

        // fetch join이므로 getQuestion 호출해도 추가 select 없음
        Question q1 = responses.get(0).getQuestion();
        Question q2 = responses.get(1).getQuestion();
        System.out.println(q1.getClass());
        System.out.println(q1.getClass());

        assertThat(q1).isNotNull();
        assertThat(q2).isNotNull();
    }

}