package com.example.mbti.domain.mbti;

import java.util.ArrayList;
import java.util.List;

public class ResponseFixture {

    public static Response createResponse(String email, String sessionId, Integer score, Question question) {
        ResponseCreateRequest createRequest = new ResponseCreateRequest(email, sessionId,score);
        return Response.create(createRequest, question);
    }

    public static Response createResponse(String email, String sessionId, Integer score) {
        Question question = QuestionFixture.createQuestion();
        return createResponse(email, sessionId, score, question);
    }

    public static Response createResponse() {
        return createResponse("user@example.com", "session123", 5);
    }

    public static ResponseCreateRequest createResponseCreateRequest(String email, String sessionId, Integer score) {
        return new ResponseCreateRequest(email, sessionId, score);
    }

    public static ResponseCreateRequest createResponseCreateRequest(String sessionId, Integer score) {
        return createResponseCreateRequest("user@example.com", sessionId, score);
    }

    public static ResponseCreateRequest createResponseCreateRequest() {
        return createResponseCreateRequest("user@example.com", "session123", 5);
    }

    /**
     * ENFP가 나오도록 20개 응답 생성
     */
    public static List<Response> create20ResponsesForENFP(String sessionId, String email, List<Question> questions) {
        List<Response> responses = new ArrayList<>();
        int[] scores = {7, 6, 5, 3, 2, 2, 3, 7, 6, 7, 6, 5, 4, 4, 6, 3, 5, 6, 7, 7}; // 총합 = 100점

        for (int i = 0; i < 20; i++) {
            responses.add(createResponse(email, sessionId, scores[i], questions.get(i)));
        }

        return responses;
    }

    /**
     * ENFP가 나오도록 20개 응답 생성 (email 기본값 사용)
     */
    public static List<Response> create20ResponsesForENFP(String sessionId, List<Question> questions) {
        return create20ResponsesForENFP(sessionId, "test@example.com", questions);
    }

    /**
     * ISTJ가 나오도록 20개 응답 생성
     * I > E, S > N, T > F, J > P
     */
    public static List<Response> create20ResponsesForISTJ(String sessionId, String email, List<Question> questions) {
        List<Response> responses = new ArrayList<>();
        int[] scores = {2, 2, 2, 6, 7, 6, 7, 2, 3, 2, 2, 2, 3, 6, 7, 6, 7, 2, 2, 3};

        for (int i = 0; i < 20; i++) {
            responses.add(createResponse(email, sessionId, scores[i], questions.get(i)));
        }

        return responses;
    }

    /**
     * ISTJ가 나오도록 20개 응답 생성 (email 기본값 사용)
     */
    public static List<Response> create20ResponsesForISTJ(String sessionId, List<Question> questions) {
        return create20ResponsesForISTJ(sessionId, "test@example.com", questions);
    }
}
