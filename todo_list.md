# MBTI 프로젝트 TODO List

### 1. ResultModifierTest 작성
- [ ] `calculateAndSave()` 메서드 테스트
- [ ] 20개 응답으로 정확한 점수 계산 확인
- [ ] MBTI 타입 결정 로직 검증
- [ ] 중복 계산 방지 테스트 (같은 sessionId로 재실행 시)
- [ ] 응답이 20개 미만일 때 예외 처리 테스트

### 2. 결과 컨트롤러 구현
- [ ] `ResultController` 생성
- [ ] MBTI 테스트 완료 후 결과 계산 엔드포인트
  - `POST /result/calculate` - sessionId로 결과 계산
- [ ] 결과 조회 페이지
  - `GET /result/{sessionId}` - 결과 화면 표시
- [ ] 예외 처리 (결과 없음, 응답 부족 등)

### 3. 결과 화면 구현
- [ ] `templates/result/` 디렉토리 생성
- [ ] 결과 표시 페이지 (`result.html`)
  - MBTI 유형 표시 (예: ENFP)
  - 유형 설명 출력
  - 각 차원별 점수 시각화 (프로그레스 바?)
  - 다시 테스트하기 버튼
- [ ] 결과 계산 로딩 페이지 (optional)

### 4. 전체 플로우 연결
- [ ] Question 목록 → 응답 수집 → 결과 계산 → 결과 표시
- [ ] 세션 관리 및 중복 방지 로직
- [ ] 에러 페이지 및 예외 처리

## 향후 개선 사항 (optional)
- [ ] 결과 공유 기능
- [ ] 통계 페이지 (유형별 분포 등)
- [ ] 관리자용 질문 관리 페이지 개선
- [ ] 응답 데이터 Export 기능

---
*Last updated: 2025-08-27*
