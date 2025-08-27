package com.example.mbti.application.mbti.required;

import com.example.mbti.domain.mbti.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
