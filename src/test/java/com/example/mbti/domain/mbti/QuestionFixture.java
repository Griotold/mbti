package com.example.mbti.domain.mbti;

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
}
