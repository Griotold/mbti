package com.example.mbti.domain.mbti;

public class ResultFixture {

    public static Result createResult(String email, String sessionId, MbtiType mbtiType,
                                      Integer eScore, Integer iScore, Integer sScore, Integer nScore,
                                      Integer fScore, Integer tScore, Integer jScore, Integer pScore) {
        ResultCreateRequest createRequest = new ResultCreateRequest(email, sessionId, mbtiType,
                eScore, iScore, sScore, nScore,
                fScore, tScore, jScore, pScore);
        return Result.create(createRequest);
    }

    public static Result createResult() {
        return createResult("email@com", "session123", MbtiType.ENFJ, 7, 8,
                9, 10, 11, 12, 13, 14);
    }
}
