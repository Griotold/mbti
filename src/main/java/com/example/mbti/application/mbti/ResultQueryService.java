package com.example.mbti.application.mbti;

import com.example.mbti.application.mbti.provided.ResultFinder;
import com.example.mbti.application.mbti.required.ResultRepository;
import com.example.mbti.domain.mbti.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResultQueryService implements ResultFinder {

    private final ResultRepository resultRepository;

    @Override
    public Result findBySessionId(String sessionId) {
        return resultRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("No result found for session id: " + sessionId));
    }

    @Override
    public boolean existsBySessionId(String sessionId) {
        return resultRepository.existsBySessionId(sessionId);
    }
}
