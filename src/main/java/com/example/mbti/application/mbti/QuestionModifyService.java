package com.example.mbti.application.mbti;

import com.example.mbti.application.mbti.provided.QuestionModifier;
import com.example.mbti.application.mbti.required.QuestionRepository;
import com.example.mbti.domain.mbti.Question;
import com.example.mbti.domain.mbti.QuestionCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionModifyService implements QuestionModifier {

    private final QuestionRepository questionRepository;

    @Override
    public Question create(QuestionCreateRequest createRequest) {
        return questionRepository.save(Question.create(createRequest));
    }
}
