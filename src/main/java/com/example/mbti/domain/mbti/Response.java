package com.example.mbti.domain.mbti;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "response",
        indexes = {
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_session_id", columnList = "session_id")
        })
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 100)
    private String email; // 이메일이 있는 경우 (nullable)

    @Column(name = "session_id", nullable = false, length = 100)
    private String sessionId; // 세션 기반 임시 식별

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "score", nullable = false)
    private Integer score; // 1~7점 (1: 매우 비동의, 4: 보통, 7: 매우 동의)

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Response create(ResponseCreateRequest createRequest, Question question) {
        // 필수값 검증
        requireNonNull(createRequest.sessionId());
        requireNonNull(createRequest.score());
        requireNonNull(question);

        // 비즈니스 규칙 검증: score 범위 확인
        if (!isValidScore(createRequest.score())) {
            throw new IllegalArgumentException(
                    String.format("점수(%d)는 1~7 사이의 값이어야 합니다.", createRequest.score())
            );
        }

        Response response = new Response();
        response.email = createRequest.email(); // nullable (로그인/비로그인 구분)
        response.sessionId = createRequest.sessionId();
        response.question = question;
        response.score = createRequest.score();

        return response;
    }

    static boolean isValidScore(Integer score) {
        return score != null && score >= 1 && score <= 7;
    }

    
}
