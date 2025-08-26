package com.example.mbti.domain.mbti;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    @DisplayName("올바른 파라미터로 Question 생성 성공")
    void createQuestion_Success() {
        // given
        QuestionCreateRequest request = QuestionFixture.createRequest();

        // when
        Question question = Question.create(request);

        // then
        assertThat(question.getContent()).isEqualTo("나는 사람들과 함께 있을 때 에너지를 얻는다");
        assertThat(question.getDimension()).isEqualTo(Dimension.EI);
        assertThat(question.getDirection()).isEqualTo(Direction.E);
        assertThat(question.getSortOrder()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("validDimensionDirectionPairs")
    @DisplayName("올바른 차원-방향 조합으로 Question 생성 성공")
    void createQuestion_ValidDimensionDirectionPairs_Success(Dimension dimension, Direction direction) {
        // given
        QuestionCreateRequest request = QuestionFixture.createRequest(dimension, direction);

        // when & then
        assertDoesNotThrow(() -> Question.create(request));
    }

    @ParameterizedTest
    @MethodSource("invalidDimensionDirectionPairs")
    @DisplayName("잘못된 차원-방향 조합으로 Question 생성 실패")
    void createQuestion_InvalidDimensionDirectionPairs_ThrowsException(Dimension dimension, Direction direction) {
        // given
        QuestionCreateRequest request = QuestionFixture.createRequest(dimension, direction);

        // when & then

        assertThatThrownBy(() -> Question.create(request))
                .isInstanceOf(IllegalArgumentException.class);
    }


    // 테스트 데이터 제공 메서드들
    static Stream<Arguments> validDimensionDirectionPairs() {
        return Stream.of(
                Arguments.of(Dimension.EI, Direction.E),
                Arguments.of(Dimension.EI, Direction.I),
                Arguments.of(Dimension.SN, Direction.S),
                Arguments.of(Dimension.SN, Direction.N),
                Arguments.of(Dimension.FT, Direction.F),
                Arguments.of(Dimension.FT, Direction.T),
                Arguments.of(Dimension.JP, Direction.J),
                Arguments.of(Dimension.JP, Direction.P)
        );
    }

    static Stream<Arguments> invalidDimensionDirectionPairs() {
        return Stream.of(
                Arguments.of(Dimension.EI, Direction.S),
                Arguments.of(Dimension.EI, Direction.N),
                Arguments.of(Dimension.EI, Direction.F),
                Arguments.of(Dimension.EI, Direction.T),
                Arguments.of(Dimension.EI, Direction.J),
                Arguments.of(Dimension.EI, Direction.P),

                Arguments.of(Dimension.SN, Direction.E),
                Arguments.of(Dimension.SN, Direction.I),
                Arguments.of(Dimension.SN, Direction.F),
                Arguments.of(Dimension.SN, Direction.T),
                Arguments.of(Dimension.SN, Direction.J),
                Arguments.of(Dimension.SN, Direction.P),

                Arguments.of(Dimension.FT, Direction.E),
                Arguments.of(Dimension.FT, Direction.I),
                Arguments.of(Dimension.FT, Direction.S),
                Arguments.of(Dimension.FT, Direction.N),
                Arguments.of(Dimension.FT, Direction.J),
                Arguments.of(Dimension.FT, Direction.P),

                Arguments.of(Dimension.JP, Direction.E),
                Arguments.of(Dimension.JP, Direction.I),
                Arguments.of(Dimension.JP, Direction.S),
                Arguments.of(Dimension.JP, Direction.N),
                Arguments.of(Dimension.JP, Direction.F),
                Arguments.of(Dimension.JP, Direction.T)
        );
    }
}