package com.example.mbti.domain;

import com.example.mbti.domain.mbti.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MbtiScoreSummaryTest {

    @Test
    void summarize_EI_점수_정상합산된다() {
        // E 2개(7,7), I 1개(3)
        Question qE1 = QuestionFixture.createQuestion("Q1", Dimension.EI, Direction.E, 1);
        Question qE2 = QuestionFixture.createQuestion("Q2", Dimension.EI, Direction.E, 2);
        Question qI1 = QuestionFixture.createQuestion("Q3", Dimension.EI, Direction.I, 3);

        Response rE1 = ResponseFixture.createResponse("email", "session", 7, qE1);
        Response rE2 = ResponseFixture.createResponse("email", "session", 7, qE2);
        Response rI1 = ResponseFixture.createResponse("email", "session", 3, qI1);

        MbtiScoreSummary summary = MbtiScoreSummary.summarize(List.of(rE1, rE2, rI1));

        assertThat(summary.eScore()).isEqualTo(14);
        assertThat(summary.iScore()).isEqualTo(3);
        assertThat(summary.sScore()).isEqualTo(0);
    }

    @Test
    void summarize_모든차원_정상합산된다() {
        // 각 차원별 1개씩
        Question qE = QuestionFixture.createQuestion("Q1", Dimension.EI, Direction.E, 1);
        Question qN = QuestionFixture.createQuestion("Q2", Dimension.SN, Direction.N, 2);
        Question qF = QuestionFixture.createQuestion("Q3", Dimension.FT, Direction.F, 3);
        Question qP = QuestionFixture.createQuestion("Q4", Dimension.JP, Direction.P, 4);

        Response rE = ResponseFixture.createResponse("email", "session", 6, qE);
        Response rN = ResponseFixture.createResponse("email", "session", 5, qN);
        Response rF = ResponseFixture.createResponse("email", "session", 3, qF);
        Response rP = ResponseFixture.createResponse("email", "session", 2, qP);

        MbtiScoreSummary summary = MbtiScoreSummary.summarize(List.of(rE, rN, rF, rP));

        assertThat(summary.eScore()).isEqualTo(6);
        assertThat(summary.iScore()).isEqualTo(0);
        assertThat(summary.sScore()).isEqualTo(0);
        assertThat(summary.nScore()).isEqualTo(5);
        assertThat(summary.fScore()).isEqualTo(3);
        assertThat(summary.tScore()).isEqualTo(0);
        assertThat(summary.jScore()).isEqualTo(0);
        assertThat(summary.pScore()).isEqualTo(2);
    }
    @Test
    void calculateMbtiType_각차원_높은점수_선택된다() {
        // E=10, I=1 / S=2, N=8 / F=5, T=3 / J=0, P=4 => ENFP
        MbtiScoreSummary summary = new MbtiScoreSummary(10,1, 2,8, 5,3, 0,4);
        MbtiType mbti = summary.calculateMbtiType();

        assertThat(mbti).isEqualTo(MbtiType.ENFP);
    }

    @Test
    void calculateMbtiType_각차원_같은점수_왼쪽문자_우선한다() {
        // E=4, I=4 / S=2, N=2 / F=3, T=3 / J=6, P=6
        MbtiScoreSummary summary = new MbtiScoreSummary(4,4, 2,2, 3,3, 6,6);
        MbtiType mbti = summary.calculateMbtiType();

        assertThat(mbti).isEqualTo(MbtiType.ESFJ);
    }
}