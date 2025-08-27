package com.example.mbti.adapter.web;

import com.example.mbti.application.mbti.provided.QuestionFinder;
import com.example.mbti.application.mbti.provided.QuestionModifier;
import com.example.mbti.domain.mbti.Question;
import com.example.mbti.domain.mbti.QuestionCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionModifier questionModifier;
    private final QuestionFinder questionFinder;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("questionCreateRequest", new QuestionCreateRequest("", null, null, null));
        return "question/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute QuestionCreateRequest questionCreateRequest,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return "question/form";
        }

        try {
            Question question = questionModifier.create(questionCreateRequest);
            redirectAttributes.addFlashAttribute("message", "질문이 성공적으로 생성되었습니다.");
            return "redirect:/question/new";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "question/form";
        }
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        Pageable pageable = PageRequest.of(page - 1, 5); // 1페이지 = 0번째 인덱스
        Page<Question> questions = questionFinder.getQuestions(pageable);

        model.addAttribute("questions", questions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", questions.getTotalPages());

        return "question/list";
    }
}
