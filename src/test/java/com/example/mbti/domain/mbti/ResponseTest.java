package com.example.mbti.domain.mbti;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    @DisplayName("로그인 사용자의 응답 생성 성공")
    void createResponse_LoginUser_Success() {
        // given
        ResponseCreateRequest request = ResponseFixture.createResponseCreateRequest();
        Question question = QuestionFixture.createQuestion();

        // when
        Response response = Response.create(request, question);

        // then
        assertThat(response.getEmail()).isEqualTo("user@example.com");
        assertThat(response.getSessionId()).isEqualTo("session123");
        assertThat(response.getQuestion()).isEqualTo(question);
        assertThat(response.getScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("비로그인 사용자의 응답 생성 성공")
    void createResponse_GuestUser_Success() {
        // given
        ResponseCreateRequest request = ResponseFixture.createResponseCreateRequest(null, "session456", 3);
        Question question = QuestionFixture.createQuestion();

        // when
        Response response = Response.create(request, question);

        // then
        assertThat(response.getEmail()).isNull();
        assertThat(response.getSessionId()).isEqualTo("session456");
        assertThat(response.getQuestion()).isEqualTo(question);
        assertThat(response.getScore()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    @DisplayName("유효한 점수로 응답 생성 성공")
    void createResponse_ValidScore_Success(int score) {
        // given
        ResponseCreateRequest request = ResponseFixture.createResponseCreateRequest("session123", score);
        Question question = QuestionFixture.createQuestion();

        // when & then
        assertDoesNotThrow(() -> Response.create(request, question));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 8, -1, 10, 100})
    @DisplayName("유효하지 않은 점수로 응답 생성 실패")
    void createResponse_InvalidScore_ThrowsException(int score) {
        // given
        ResponseCreateRequest request = ResponseFixture.createResponseCreateRequest("session123", score);
        Question question = QuestionFixture.createQuestion();

        // when & then
        assertThatThrownBy(() -> Response.create(request, question))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("score가 null일 때 예외 발생")
    void createResponse_ScoreIsNull_ThrowsException() {
        // given
        ResponseCreateRequest request = new ResponseCreateRequest(
                "user@example.com",
                "session123",
                null
        );
        Question question = QuestionFixture.createQuestion();

        // when & then
        assertThatThrownBy(() -> Response.create(request, question))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("question이 null일 때 예외 발생")
    void createResponse_QuestionIsNull_ThrowsException() {
        // given
        ResponseCreateRequest request = new ResponseCreateRequest(
                "user@example.com",
                "session123",
                5
        );

        assertThatThrownBy(() -> Response.create(request, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("isValidScore 메서드 테스트")
    void isValidScore_Test() {
        // when & then
        assertThat(Response.isValidScore(1)).isTrue();
        assertThat(Response.isValidScore(4)).isTrue();
        assertThat(Response.isValidScore(7)).isTrue();

        assertThat(Response.isValidScore(0)).isFalse();
        assertThat(Response.isValidScore(8)).isFalse();
        assertThat(Response.isValidScore(-1)).isFalse();
        assertThat(Response.isValidScore(null)).isFalse();
    }
}