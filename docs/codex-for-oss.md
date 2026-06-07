# Codex for OSS

이 저장소는 단계별 PR로 재구성합니다.  
Codex 작업자는 아래 규칙을 지켜 진행하면 리뷰/합의/이행이 안정적입니다.

## 1) 작업 전 확인

1. `docs/oss-remake-task-plan.md`를 Source of Truth로 확인
2. 현재 브랜치 상태를 정리해 현재 단계(문서/구현/테스트/릴리스)와 맞추기
3. 다음 PR 범위를 하나의 목표로 고정

범위를 벗어나는 변경은 피하고, 필요하면 다음 PR로 분리합니다.

## 2) 구현 단계별 기본 규칙

- 한 PR당 한 단위: `Baseline → State/Reducer → ViewModel → UI → 테스트/CI → 문서`
- 코드가 바뀌면 반드시 대응 문서를 같이 업데이트
- 기존 동작을 보호: `legacy-app`은 아카이브로 유지
- 빌드 대상은 1차로 현대 `app/` 중심

## 3) PR 생성 전 체크리스트

- PR 제목은 현재 단계가 드러나게 작성
  - 예: `Add migration documentation`, `Add Android CI workflow`
- 설명에는 완료 범위/미완료 범위 분리
- PR 설명에 빌드 검증 결과 반영
- CI 관련 PR에서는 최소 `./gradlew test`, `./gradlew assembleDebug` 여부를 넣고, 실행 환경(JDK/OS)을 남김

## 4) 문서 우선 원칙

- 코드가 추가되면 해당 문서 파일을 바로 작성/수정
  - 02-modern-architecture
  - 03-shared-state-problem
  - 04-livedata-to-stateflow
  - 05-compose-ui
- 문서는 최신 코드 기준으로 유지 (`README`와 링크도 동기화)

## 5) 리뷰 대응 규칙

- 리뷰 코멘트는 블로킹/비블로킹을 분리해 반영
- “재확인” 요청은 실행 환경 불일치 가능성을 가정하고 재현 정보(환경/로그/명령어)로 대응
- 반복 오류 발생 시 원인 후보 3~5개를 빠르게 추리고, 가장 비용 효율적인 해결을 우선 적용

## 6) 다음 단계 진입 기준

문서 단계가 끝나면 출시 준비로 이동합니다.

- `v0.1.0-oss-alpha.1` 태그
- Release Notes 작성
- 변경 범위를 유지한 README/체크리스트 정리 완료

