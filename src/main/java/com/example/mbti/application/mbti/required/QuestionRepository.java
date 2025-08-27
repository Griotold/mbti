package com.example.mbti.application.mbti.required;

import com.example.mbti.domain.mbti.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
