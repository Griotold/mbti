package com.example.mbti.application.mbti.provided;

import com.example.mbti.domain.mbti.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionFinder {

    /**
     * 질문 목록 조회
     * @param pageable 페이지 번호 (1부터 시작)
     * @return Page<Question> 질문 목록 조회
     * */
    Page<Question> getQuestions(Pageable pageable);
}
