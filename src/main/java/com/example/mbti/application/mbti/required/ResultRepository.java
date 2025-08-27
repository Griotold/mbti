package com.example.mbti.application.mbti.required;

import com.example.mbti.domain.mbti.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
