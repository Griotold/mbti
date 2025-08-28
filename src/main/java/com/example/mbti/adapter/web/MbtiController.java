package com.example.mbti.adapter.web;

import com.example.mbti.application.mbti.provided.QuestionFinder;
import com.example.mbti.application.mbti.provided.ResponseModifier;
import com.example.mbti.application.mbti.provided.ResultFinder;
import com.example.mbti.application.mbti.provided.ResultModifier;
import com.example.mbti.domain.mbti.Question;
import com.example.mbti.domain.mbti.ResponseBatchCreateRequest;
import com.example.mbti.domain.mbti.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mbti-test")
@RequiredArgsConstructor
public class MbtiController {

    private final QuestionFinder questionFinder;
    private final ResponseModifier responseModifier;
    private final ResultFinder resultFinder;

    @GetMapping
    public String getQuestions(@RequestParam(defaultValue = "1") int page,
                               Model model,
                               HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        Pageable pageable = PageRequest.of(page - 1, 5); // 0-based index
        Page<Question> questions = questionFinder.getQuestions(pageable);

        model.addAttribute("sessionId", sessionId);
        model.addAttribute("questions", questions.getContent());
        model.addAttribute("currentPage", page);
        return "mbti/test"; // 타임리프 템플릿명
    }

    @PostMapping("/response")
    public String submitResponses(@ModelAttribute ResponseBatchCreateRequest batchRequest,
                                  @RequestParam int page) {
        responseModifier.createBatch(batchRequest); // 5개 응답 저장
        // 4페이지 다했으면 결과로 이동, 아니면 다음 페이지로 이동
        if(page < 4) {
            return "redirect:/mbti-test?page=" + (page + 1);
        }
        return "redirect:/mbti-test/result";
    }

    @GetMapping("/result")
    public String showResult(HttpServletRequest request, Model model) {
        String sessionId = request.getSession().getId(); // 세션에서 직접 가져오기

        // 결과 계산 및 조회
        Result result = resultFinder.getOrCreateResult(sessionId);

        model.addAttribute("result", result);
        return "mbti/result"; // 결과 화면 타임리프 템플릿명
    }

    @GetMapping("/reset")
    public String resetTest(HttpServletRequest request) {
        request.getSession().invalidate(); // 기존 세션 무효화
        return "redirect:/mbti-test"; // 새 세션으로 테스트 시작
    }
}
