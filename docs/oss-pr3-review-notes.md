# PR #3 리뷰: `Add OSS metadata and contribution docs`

## 결론

**방향은 좋습니다. 첫 PR로 적절합니다.**
다만 **README/CHANGELOG/PR 설명에서 “이미 구현된 것”과 “앞으로 할 것”을 명확히 분리**하면 더 안전합니다.

제가 보는 머지 판단은:

> **조건부 Approve**
> 아래 3가지만 확인/수정하면 머지해도 됩니다.

1. README가 아직 존재하지 않는 `Compose / StateFlow / CI / modern app`을 **완료된 것처럼 말하지 않는지** 확인
2. `CHANGELOG.md`가 `v0.1.0`을 이미 릴리스된 것처럼 쓰지 않고 `Unreleased` 중심인지 확인
3. PR 본문에 “문서/메타데이터 전용 PR이라 Gradle build/test는 아직 대상 아님”을 명시

PR 본문 기준으로는 README 재작성, OSS 메타 파일 추가, `.github` 템플릿 추가, 기존 baseline 문서 보존을 포함하고 있어서, 우리가 정한 **0단계 원본 보존 + 1단계 OSS 메타/가이드 기본 구성**과 잘 맞습니다. PR 설명에도 “beginner-friendly OSS migration lab”으로 설정하고, README/라이선스/CONTRIBUTING/SECURITY/CODE_OF_CONDUCT/CHANGELOG 및 `.github` 템플릿을 추가했다고 되어 있습니다. 

---

# 전체 평가

| 항목         | 평가     |
| ---------- | ------ |
| PR 크기      | 적절함    |
| 첫 PR로서의 역할 | 좋음     |
| 프로젝트 방향성   | 계획과 일치 |
| 코드 리스크     | 낮음     |
| 문서 리스크     | 약간 있음  |
| 머지 가능성     | 조건부 가능 |

이번 PR은 실제 앱 코드 변경이 아니라 **저장소의 정체성을 정리하는 PR**입니다. 그래서 첫 PR로 매우 좋습니다.

우리가 수정본 계획에서 정한 방향은 `Article` 중심의 레거시 Android AAC/ViewModel 공유 상태 샘플을 현대 Android로 재구성하는 것이고, Reserve.M/예약/매출/ダブルブッキング 같은 비즈니스 용어는 제거하는 것이었습니다. 
PR 제목과 설명을 보면 현재 PR은 그 방향에 맞게 “Android ViewModel Migration Lab”으로 저장소의 방향을 잡는 역할을 하고 있습니다. 

---

# 좋은 점

## 1. 첫 PR 범위가 적절합니다

첫 PR에서 바로 Gradle, Kotlin, Compose, ViewModel까지 건드리지 않은 점이 좋습니다.

이번 PR은 다음에 해당합니다.

```text
README 정리
OSS 메타 문서 추가
PR/Issue 템플릿 추가
기존 README/baseline 문서 보존
작업 계획 문서 추가
```

이건 우리가 정한 실행 순서의 초반부와 맞습니다.

```text
1. 원본 보존
2. OSS 메타 문서 정비
3. 현대 앱 뼈대 정비
4. Article 모델/Reducer
...
```

수정본 계획서도 먼저 원본 보존과 OSS 메타 문서 정비를 하도록 되어 있습니다. 

## 2. “다음 PR의 범위”를 남긴 점이 좋습니다

PR 본문에 “Next PRs will add modern Kotlin/Compose app baseline and tests”라고 되어 있는 방향은 좋습니다. 즉, 이번 PR이 **완성 PR이 아니라 시작 PR**이라는 점을 분명히 하려는 의도가 보입니다. 

이건 매우 중요합니다. 첫 PR은 저장소 방향을 고정하는 역할만 하고, 실제 구현은 다음 PR로 나누는 게 맞습니다.

## 3. OSS 제출용 기본 골격이 생깁니다

이번 PR에서 추가한 것으로 보이는 파일들은 모두 필요합니다.

```text
README.md
LICENSE
CONTRIBUTING.md
SECURITY.md
CODE_OF_CONDUCT.md
CHANGELOG.md
.github/pull_request_template.md
.github/ISSUE_TEMPLATE/bug_report.md
.github/ISSUE_TEMPLATE/migration_task.md
docs/01-legacy-architecture.md
```

우리 최종 산출 기준에도 `README / LICENSE / CONTRIBUTING / SECURITY / CODE_OF_CONDUCT / PR 템플릿`이 포함되어 있습니다. 

---

# 머지 전 확인해야 할 부분

## 1. README가 “현재 상태”와 “목표 상태”를 헷갈리게 쓰면 안 됩니다

가장 중요한 체크입니다.

이번 PR 시점에서는 아직 다음이 없습니다.

```text
현대 Kotlin + Compose app
Article 모델
Reducer
ViewModel + StateFlow
테스트
CI
v0.1.0 release
```

따라서 README에 아래처럼 쓰면 위험합니다.

```md
This project provides a modern Kotlin + Compose app with StateFlow and tests.
```

아직 구현 전이라면 이렇게 바꾸는 게 좋습니다.

```md
This project is being rebuilt as a modern Kotlin + Compose migration lab.

Current status:
- OSS metadata and documentation baseline are being prepared.
- The modern Kotlin + Compose app will be added in the next implementation PR.
```

추천 문구:

```md
## Current status

This repository is in the initial OSS setup phase.

Completed:
- Project direction clarified
- OSS metadata added
- Legacy README preserved as documentation

Next:
- Add modern Kotlin + Compose app baseline
- Add Article state model
- Add ViewModel + StateFlow sample
- Add tests and CI
```

이렇게 쓰면 신규 방문자가 “왜 아직 앱이 안 돌아가지?”라고 혼란스러워하지 않습니다.

## 2. `CHANGELOG.md`는 `Unreleased`부터 시작하는 게 안전합니다

이번 PR에서 `CHANGELOG.md`를 추가했다면, 아직 `v0.1.0-oss-alpha.1`이 나온 상태가 아니므로 아래처럼 쓰는 게 좋습니다.

```md
# Changelog

## Unreleased

### Added
- Rewrote README for Android ViewModel Migration Lab direction
- Added OSS metadata files
- Added GitHub pull request and issue templates
- Preserved original README content as legacy architecture documentation
```

아래처럼 쓰면 조금 이릅니다.

```md
## v0.1.0-oss-alpha.1
```

`v0.1.0-oss-alpha.1`은 실제로 현대 앱, 테스트, CI, 문서가 갖춰진 뒤 태그를 만들기로 했습니다. 수정본 계획에서도 최종 산출 기준에 `v0.1.0-oss-alpha.1` 태그 생성을 마지막 기준으로 두고 있습니다. 

## 3. PR 본문에 “빌드/테스트 미실행 사유”를 명확히 쓰는 게 좋습니다

PR 본문에는 `How I tested this` 영역이 있고, `I ran ...` 체크박스가 비어 있는 것으로 보입니다. 

이번 PR은 문서/메타데이터 전용이라 `./gradlew test`나 `./gradlew assembleDebug`를 아직 강제하지 않아도 됩니다. 다만 비어 있으면 보는 사람이 “검증 안 한 PR인가?”라고 느낄 수 있습니다.

추천 수정:

```md
## How I tested this

- [x] Reviewed Markdown rendering locally/GitHub preview
- [x] Confirmed this PR does not change app source code
- [ ] I ran `./gradlew test`
  - Not applicable yet: this PR only adds OSS metadata/docs.
- [ ] I ran `./gradlew assembleDebug`
  - Not applicable yet: modern Android app baseline will be added in a follow-up PR.
```

또는 PR 템플릿 자체에 이것을 추가하면 좋습니다.

```md
- [ ] Not applicable: docs-only change
```

## 4. `docs/01-legacy-architecture.md`는 “원본 README 보존”이라는 성격을 명확히 해야 합니다

PR 설명에 기존 baseline을 `docs/01-legacy-architecture.md`로 보존했다고 되어 있습니다. 

좋습니다. 다만 이 문서는 “최신 가이드”가 아니라 “원본 기록”입니다.

파일 맨 위에 아래 안내문을 넣는 것을 추천합니다.

```md
> This document preserves the original README and legacy architecture notes.
> It describes the 2017-era sample and may reference outdated Android tooling.
> The modern migration guide starts from `docs/02-modern-architecture.md`.
```

한국어로는:

```md
> 이 문서는 원본 README와 2017년식 구조를 보존하기 위한 문서입니다.
> 오래된 Android 도구와 의존성을 언급할 수 있습니다.
> 현대화 문서는 `docs/02-modern-architecture.md`부터 작성됩니다.
```

이걸 넣어두면 나중에 누가 봐도 “이 문서가 왜 오래된 내용을 담고 있는지” 이해할 수 있습니다.

# PR 코멘트로 남기면 좋은 리뷰 문구

아래 내용을 그대로 GitHub 리뷰 코멘트로 남기면 좋습니다.

```md
Overall direction looks good. This is an appropriate first PR because it focuses on repository identity, OSS metadata, contribution docs, and preserving the legacy baseline before touching the Android build.

Before merging, I recommend clarifying three things:

1. README should clearly separate current status from roadmap. Since the modern Kotlin/Compose app, StateFlow ViewModel, tests, and CI are not implemented yet, please avoid wording that sounds like they already exist.
2. CHANGELOG should start with `Unreleased` instead of treating `v0.1.0-oss-alpha.1` as already released.
3. In the PR description, please mark Gradle build/test as “not applicable yet” because this PR is docs/metadata-only.

After those small adjustments, this PR is safe to merge as the OSS setup baseline.
```

# 제안하는 PR 본문 수정안

현재 PR 본문은 방향은 좋은데, 첫 PR이라면 아래처럼 조금 더 명확하게 바꾸는 것을 추천합니다.

```md
## Summary

This PR sets up the repository as a beginner-friendly Android ViewModel Migration Lab.

It does not change Android app source code yet.

## Changes

- Rewrites README to describe the OSS migration lab direction
- Adds OSS metadata files:
  - LICENSE
  - CONTRIBUTING.md
  - SECURITY.md
  - CODE_OF_CONDUCT.md
  - CHANGELOG.md
- Adds GitHub templates:
  - Pull request template
  - Bug report issue template
  - Migration task issue template
- Preserves the original README content in `docs/01-legacy-architecture.md`
- Adds OSS remake task/reference documentation

## Current status

Completed in this PR:
- OSS metadata setup
- Repository direction clarified
- Legacy README preserved

Not included yet:
- Modern Kotlin + Compose app
- Article domain model
- Reducer
- ViewModel + StateFlow
- Tests
- CI

## How I tested this

- [x] Reviewed Markdown rendering
- [x] Confirmed this PR is docs/metadata-only
- [ ] `./gradlew test`
  - Not applicable yet. Modern app baseline will be added in a follow-up PR.
- [ ] `./gradlew assembleDebug`
  - Not applicable yet. Modern app baseline will be added in a follow-up PR.

## Next PRs

- Add modern Kotlin + Compose app baseline
- Move legacy app into archive structure if needed
- Add Article state model and reducer
- Add reducer tests
```

# 다음 PR 추천

이 PR이 머지되면 다음 PR은 바로 코드로 가기 전에 **현대 앱 뼈대 정비 PR**이 좋습니다.

## PR #4 재리뷰 (최신)

### 추천 제목

```text
Add modern app baseline with Article state foundation
```

### 현재 PR #4 실제 범위

```text
legacy-app 보존
+ modern Kotlin Compose app baseline
+ Article core state model
+ ArticleListReducer
+ reducer tests
```

### 머지 판단

- 현재는 **병합 승인** 상태입니다.
- `ArticleListReducer`와 `ArticleListReducerTest`가 함께 들어와 이전 이슈(테스트 부재)는 해소됨.
- 검증 실행(2026-06-07, JDK 21): `./gradlew test`, `./gradlew assembleDebug` 모두 PASS.

### 적용된 검증 환경

- `JAVA_HOME=/Applications/Android Studio.app/Contents/jbr/Contents/Home`
- `ANDROID_HOME=$HOME/Library/Android/sdk`
- `gradle-wrapper` 업그레이드: `gradle-8.6-all.zip`
- `kotlinCompilerExtensionVersion = 1.5.14`

### 머지 전 필수 확인 (완료)

1. `./gradlew test` 통과 ✅
2. `./gradlew assembleDebug` 통과 ✅

### 권장 다음 PR

1) Article state model + reducer 기반 정합성 고도화  
2) Detail/Edit/Save 흐름 추가  
3) StateFlow ViewModel + UI 바인딩  
4) Compose 리스트/상세 화면 정리  
5) CI 도입

이 순서가 안전합니다.

---

# 최종 판단

**PR #3은 첫 PR로 좋습니다.**
저라면 아래 조건으로 머지합니다.

```text
조건부 머지 OK
```

머지 전 필수 확인:

* README가 현재 구현 상태를 과장하지 않는다.
* CHANGELOG는 `Unreleased`부터 시작한다.
* PR 본문에 docs-only라서 build/test 미실행이 정상임을 쓴다.
* Reserve.M/예약/매출/ダブルブッキング 문구가 없다.
* `docs/01-legacy-architecture.md`가 “원본 보존 문서”임을 명시한다.

이 정도만 정리하면, 계획서 기준으로 **0~1단계 착수 PR로 매우 적절합니다.**
