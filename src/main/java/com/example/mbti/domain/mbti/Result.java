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
@Table(name = "result",
        indexes = {
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_session_id", columnList = "sessionId"),
                @Index(name = "idx_mbti_type", columnList = "mbtiType")
        })
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "session_id", nullable = false, length = 100)
    private String sessionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti_type", nullable = false, length = 4)
    private MbtiType mbtiType; // ENFP, ISTJ 등

    // 각 차원별 점수 저장
    @Column(name = "e_score", nullable = false)
    private Integer eScore = 0;

    @Column(name = "i_score", nullable = false)
    private Integer iScore = 0;

    @Column(name = "s_score", nullable = false)
    private Integer sScore = 0;

    @Column(name = "n_score", nullable = false)
    private Integer nScore = 0;

    @Column(name = "f_score", nullable = false)
    private Integer fScore = 0;

    @Column(name = "t_score", nullable = false)
    private Integer tScore = 0;

    @Column(name = "j_score", nullable = false)
    private Integer jScore = 0;

    @Column(name = "p_score", nullable = false)
    private Integer pScore = 0;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Result create(ResultCreateRequest createRequest) {
        Result result = new Result();

        result.email = createRequest.email(); // nullable
        result.sessionId = requireNonNull(createRequest.sessionId());
        result.mbtiType = requireNonNull(createRequest.mbtiType());
        result.eScore = requireNonNull(createRequest.eScore());
        result.iScore = requireNonNull(createRequest.iScore());
        result.sScore = requireNonNull(createRequest.sScore());
        result.nScore = requireNonNull(createRequest.nScore());
        result.fScore = requireNonNull(createRequest.fScore());
        result.tScore = requireNonNull(createRequest.tScore());
        result.jScore = requireNonNull(createRequest.jScore());
        result.pScore = requireNonNull(createRequest.pScore());

        return result;
    }
}
