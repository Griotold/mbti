package com.example.mbti.application.mbti.provided;

import com.example.mbti.domain.mbti.Question;
import com.example.mbti.domain.mbti.QuestionCreateRequest;

public interface QuestionModifier {

    /**
     * 질문 생성
     * @param createRequest 질문 생성 요청
     * @return Question 생성된 질문
     * */
    Question create(QuestionCreateRequest createRequest);
}
