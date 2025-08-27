package com.example.mbti.application.mbti;

import com.example.mbti.application.mbti.provided.ResponseModifier;
import com.example.mbti.application.mbti.required.QuestionRepository;
import com.example.mbti.application.mbti.required.ResponseRepository;
import com.example.mbti.domain.mbti.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResponseModifyService implements ResponseModifier {

    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Response create(ResponseCreateRequest createRequest, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("질문을 찾을 수 없습니다: " + questionId));

        return responseRepository.save(Response.create(createRequest, question));
    }

    @Override
    public List<Response> createBatch(ResponseBatchCreateRequest batchRequest) {
        List<Response> responses = new ArrayList<>();

        for (ResponseWithQuestion item : batchRequest.items()) {
            Question question = questionRepository.findById(item.questionId())
                    .orElseThrow(() -> new IllegalArgumentException("질문을 찾을 수 없습니다: " + item.questionId()));

            ResponseCreateRequest createRequest = new ResponseCreateRequest(
                    item.email(),
                    item.sessionId(),
                    item.score()
            );

            Response response = Response.create(createRequest, question);
            Response savedResponse = responseRepository.save(response);
            responses.add(savedResponse);
        }

        return responses;
    }
}
