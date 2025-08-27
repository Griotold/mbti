package com.example.mbti.domain.mbti;

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
}
