package com.example.mbti.application.mbti.provided;

import com.example.mbti.domain.mbti.Response;
import com.example.mbti.domain.mbti.ResponseBatchCreateRequest;
import com.example.mbti.domain.mbti.ResponseCreateRequest;

import java.util.List;

public interface ResponseModifier {
    // 단일 생성
    Response create(ResponseCreateRequest createRequest, Long QuestionId);

    // 리스트로 받아서 생성 5개씩
    List<Response> createBatch(ResponseBatchCreateRequest batchRequest);
}
