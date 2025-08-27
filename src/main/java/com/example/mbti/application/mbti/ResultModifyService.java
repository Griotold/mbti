package com.example.mbti.application.mbti;

import com.example.mbti.application.mbti.provided.ResultModifier;
import com.example.mbti.application.mbti.required.ResponseRepository;
import com.example.mbti.application.mbti.required.ResultRepository;
import com.example.mbti.domain.MbtiScoreSummary;
import com.example.mbti.domain.mbti.MbtiType;
import com.example.mbti.domain.mbti.Response;
import com.example.mbti.domain.mbti.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResultModifyService implements ResultModifier {

    private final ResultRepository resultRepository;
    private final ResponseRepository responseRepository;

    @Override
    public Result calculateAndSave(String sessionId) {
        List<Response> responses = responseRepository.findAllWithQuestionBySessionId(sessionId);

        MbtiScoreSummary summary = MbtiScoreSummary.summarize(responses);

        MbtiType mbtiType = summary.calculateMbtiType();

        Result result = Result.create(null, sessionId, mbtiType, summary);

        return resultRepository.save(result);
    }
}
