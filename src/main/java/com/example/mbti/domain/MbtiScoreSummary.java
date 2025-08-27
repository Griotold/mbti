package com.example.mbti.domain;

import com.example.mbti.domain.mbti.*;

import java.util.List;

public record MbtiScoreSummary(
        int eScore, int iScore,
        int sScore, int nScore,
        int fScore, int tScore,
        int jScore, int pScore
) {
    // 집계 팩토리
    public static MbtiScoreSummary summarize(List<Response> responses) {
        int e=0, i=0, s=0, n=0, f=0, t=0, j=0, p=0;

        for (Response response : responses) {
            int score = response.getScore();
            switch (response.getQuestion().getDirection()) {
                case E -> e += score;
                case I -> i += score;
                case S -> s += score;
                case N -> n += score;
                case F -> f += score;
                case T -> t += score;
                case J -> j += score;
                case P -> p += score;
            }
        }
        return new MbtiScoreSummary(e, i, s, n, f, t, j, p);
    }

    // 최종 MBTI 타입 계산
    public MbtiType calculateMbtiType() {
        StringBuilder sb = new StringBuilder();
        sb.append(eScore >= iScore ? "E" : "I");
        sb.append(sScore >= nScore ? "S" : "N");
        sb.append(fScore >= tScore ? "F" : "T");
        sb.append(jScore >= pScore ? "J" : "P");
        return MbtiType.valueOf(sb.toString());
    }
}
