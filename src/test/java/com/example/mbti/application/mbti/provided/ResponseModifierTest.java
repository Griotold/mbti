package com.example.mbti.application.mbti.provided;

import com.example.mbti.application.mbti.required.QuestionRepository;
import com.example.mbti.application.mbti.required.ResponseRepository;
import com.example.mbti.domain.mbti.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
record ResponseModifierTest(ResponseModifier responseModifier,
                            ResponseRepository responseRepository,
                            QuestionRepository questionRepository,
                            EntityManager entityManager) {

    @Test
    void create() {
        Question question = questionRepository.save(QuestionFixture.createQuestion());
        ResponseCreateRequest createRequest = ResponseFixture.createResponseCreateRequest();

        Response response = responseModifier.create(createRequest, question.getId());

        assertThat(response.getQuestion()).isEqualTo(question);
        assertThat(response.getEmail()).isEqualTo(createRequest.email());
        assertThat(response.getScore()).isEqualTo(createRequest.score());
    }

    @Test
    void create_없는_questionId_일때() {
        ResponseCreateRequest createRequest = ResponseFixture.createResponseCreateRequest();

        assertThatThrownBy(()  -> responseModifier.create(createRequest, 999L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createBatch_성공() {
        // given
        Question question1 = questionRepository.save(QuestionFixture.createQuestion("질문1", Dimension.EI, Direction.E, 1));
        Question question2 = questionRepository.save(QuestionFixture.createQuestion("질문2", Dimension.SN, Direction.S, 2));

        List<ResponseWithQuestion> items = List.of(
                new ResponseWithQuestion(question1.getId(), "user@test.com", "session123", 5),
                new ResponseWithQuestion(question2.getId(), "user@test.com", "session123", 3)
        );
        ResponseBatchCreateRequest batchRequest = new ResponseBatchCreateRequest(items);

        // when
        List<Response> responses = responseModifier.createBatch(batchRequest);

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getQuestion()).isEqualTo(question1);
        assertThat(responses.get(0).getScore()).isEqualTo(5);
        assertThat(responses.get(1).getQuestion()).isEqualTo(question2);
        assertThat(responses.get(1).getScore()).isEqualTo(3);
    }

    @Test
    void createBatch_없는_questionId_일때() {
        // given
        Question question1 = questionRepository.save(QuestionFixture.createQuestion());

        List<ResponseWithQuestion> items = List.of(
                new ResponseWithQuestion(question1.getId(), "user@test.com", "session123", 5),
                new ResponseWithQuestion(999L, "user@test.com", "session123", 3) // 존재하지 않는 ID
        );
        ResponseBatchCreateRequest batchRequest = new ResponseBatchCreateRequest(items);

        // when & then
        assertThatThrownBy(() -> responseModifier.createBatch(batchRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("질문을 찾을 수 없습니다: 999");
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void createBatch_트랜잭션_롤백_확인() {
        // given
        Question question1 = questionRepository.save(QuestionFixture.createQuestion());

        List<ResponseWithQuestion> items = List.of(
                new ResponseWithQuestion(question1.getId(), "user@test.com", "session123", 5),
                new ResponseWithQuestion(999L, "user@test.com", "session123", 3) // 실패할 항목
        );
        ResponseBatchCreateRequest batchRequest = new ResponseBatchCreateRequest(items);

        // when & then
        assertThatThrownBy(() -> responseModifier.createBatch(batchRequest))
                .isInstanceOf(IllegalArgumentException.class);

        // 롤백 확인 - 첫 번째 항목도 저장되지 않았는지 검증
        long savedCount = responseRepository.count();
        assertThat(savedCount).isEqualTo(0);
    }

    @Test
    void createBatch_빈_리스트_일때() {
        // given
        List<ResponseWithQuestion> items = List.of();
        ResponseBatchCreateRequest batchRequest = new ResponseBatchCreateRequest(items);

        // when
        List<Response> responses = responseModifier.createBatch(batchRequest);

        // then
        assertThat(responses).isEmpty();
    }
}