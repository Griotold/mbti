package com.example.mbti.application.mbti;

import com.example.mbti.application.mbti.provided.QuestionFinder;
import com.example.mbti.application.mbti.required.QuestionRepository;
import com.example.mbti.domain.mbti.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionQueryService implements QuestionFinder {

    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
}
