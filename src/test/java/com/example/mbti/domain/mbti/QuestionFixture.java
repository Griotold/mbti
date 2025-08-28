package com.example.mbti.domain.mbti;

import java.util.ArrayList;
import java.util.List;

public class QuestionFixture {

    public static Question createQuestion(String content, Dimension dimension, Direction direction, Integer sortOrder) {
        QuestionCreateRequest createRequest = new QuestionCreateRequest(content, dimension, direction, sortOrder);
        return Question.create(createRequest);
    }

    public static Question createQuestion() {
        return createQuestion("나는 사람들과 함께 있을 때 에너지를 얻는다", Dimension.EI, Direction.E, 1);
    }

    public static QuestionCreateRequest createRequest(String content, Dimension dimension, Direction direction, Integer sortOrder) {
        return new  QuestionCreateRequest(content, dimension, direction, sortOrder);
    }

    public static QuestionCreateRequest createRequest(Dimension dimension, Direction direction) {
        return createRequest("나는 사람들과 함께 있을 때 에너지를 얻는다", dimension, direction, 1);
    }

    public static QuestionCreateRequest createRequest() {
        return createRequest("나는 사람들과 함께 있을 때 에너지를 얻는다",  Dimension.EI, Direction.E, 1);
    }

    /**
     * 20개 질문 생성 (각 차원별 5개씩)
     * 테스트용으로 사용
     */
    public static List<Question> create20Questions() {
        List<Question> questions = new ArrayList<>();

        // EI 차원 5개 (E 3개, I 2개)
        questions.add(createQuestion("외향적 질문1", Dimension.EI, Direction.E, 1));
        questions.add(createQuestion("외향적 질문2", Dimension.EI, Direction.E, 2));
        questions.add(createQuestion("외향적 질문3", Dimension.EI, Direction.E, 3));
        questions.add(createQuestion("내향적 질문1", Dimension.EI, Direction.I, 4));
        questions.add(createQuestion("내향적 질문2", Dimension.EI, Direction.I, 5));

        // SN 차원 5개 (S 2개, N 3개)
        questions.add(createQuestion("감각적 질문1", Dimension.SN, Direction.S, 6));
        questions.add(createQuestion("감각적 질문2", Dimension.SN, Direction.S, 7));
        questions.add(createQuestion("직관적 질문1", Dimension.SN, Direction.N, 8));
        questions.add(createQuestion("직관적 질문2", Dimension.SN, Direction.N, 9));
        questions.add(createQuestion("직관적 질문3", Dimension.SN, Direction.N, 10));

        // FT 차원 5개 (F 3개, T 2개)
        questions.add(createQuestion("감정적 질문1", Dimension.FT, Direction.F, 11));
        questions.add(createQuestion("감정적 질문2", Dimension.FT, Direction.F, 12));
        questions.add(createQuestion("감정적 질문3", Dimension.FT, Direction.F, 13));
        questions.add(createQuestion("사고적 질문1", Dimension.FT, Direction.T, 14));
        questions.add(createQuestion("사고적 질문2", Dimension.FT, Direction.T, 15));

        // JP 차원 5개 (J 2개, P 3개)
        questions.add(createQuestion("판단적 질문1", Dimension.JP, Direction.J, 16));
        questions.add(createQuestion("판단적 질문2", Dimension.JP, Direction.J, 17));
        questions.add(createQuestion("인식적 질문1", Dimension.JP, Direction.P, 18));
        questions.add(createQuestion("인식적 질문2", Dimension.JP, Direction.P, 19));
        questions.add(createQuestion("인식적 질문3", Dimension.JP, Direction.P, 20));

        return questions;
    }
}
