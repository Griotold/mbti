package com.example.mbti.domain.mbti;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "dimension", nullable = false, length = 2)
    private Dimension dimension; // EI, SN, FT, JP

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false, length = 1)
    private Direction direction; // E, I, S, N, F, T, J, P

    @Column(name = "question_order", nullable = false)
    private Integer sortOrder; // 질문 순서 (1~20)

    public static Question create(QuestionCreateRequest createRequest) {
        // 필수값 검증
        requireNonNull(createRequest.content(), "content cannot be null");
        requireNonNull(createRequest.dimension(), "dimension cannot be null");
        requireNonNull(createRequest.direction(), "direction cannot be null");
        requireNonNull(createRequest.sortOrder(), "sortOrder cannot be null");

        // 비즈니스 규칙 검증: dimension과 direction 일치성 확인
        boolean isValidCombination = createRequest.direction().isValidForDimension(createRequest.dimension());
        if (!isValidCombination) {
            throw new IllegalArgumentException(
                    String.format("차원(%s)과 방향(%s)이 일치하지 않습니다.",
                            createRequest.dimension().name(),
                            createRequest.direction().name()
                    )
            );
        }

        Question question = new Question();
        question.content = createRequest.content();
        question.dimension = createRequest.dimension();
        question.direction = createRequest.direction();
        question.sortOrder = createRequest.sortOrder();

        return question;
    }
}
