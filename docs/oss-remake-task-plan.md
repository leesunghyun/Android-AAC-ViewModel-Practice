# Android ViewModel Migration Lab OSS 리메이크 태스크 플랜

## 0. 문서 목적

이 문서는 `Android-AAC-ViewModel-Practice` 저장소를 **초보자 친화형 Android OSS 마이그레이션 랩**으로 리메이크하기 위한 실행 계획서입니다.

이 계획서는 아래 사람도 따라 할 수 있도록 작성되었습니다.

- Android 개발 경험이 적은 사람
- GitHub 사용이 익숙하지 않은 사람
- Kotlin, Compose, ViewModel, StateFlow를 처음 접하는 사람
- 기존 레거시 Android 프로젝트를 최신 구조로 바꾸는 과정을 배우고 싶은 사람

이 문서의 핵심 원칙은 다음과 같습니다.

> 한 번에 크게 바꾸지 않는다.  
> 작은 단위로 바꾸고, 테스트하고, 커밋하고, 문서화한다.

---

## 1. 프로젝트 정체성

### 1.1 이 프로젝트가 아닌 것

이 프로젝트는 Reserve.M 프로젝트가 아닙니다.

따라서 아래 용어와 방향은 사용하지 않습니다.

- Reserve.M
- 예약
- 매출
- 고객 관리
- お客様
- 予約
- 売上
- ダブルブッキング
- 稼働率改善
- 失客防止
- リピート率向上

이 프로젝트는 특정 비즈니스 앱을 홍보하거나 재현하는 저장소가 아닙니다.

---

### 1.2 이 프로젝트가 되는 것

이 프로젝트는 아래 주제를 다룹니다.

> 오래된 Android AAC / ViewModel 공유 상태 샘플을  
> Kotlin, Compose, ViewModel, StateFlow 기반의 현대 Android 구조로 재구성하는  
> 초보자 친화형 OSS 마이그레이션 랩

핵심 예제 도메인은 `Article`입니다.

앱의 핵심 흐름은 다음과 같습니다.

```text
Article List
↓
Article Detail
↓
Edit Article
↓
Save
↓
Back to List
↓
Updated Article is reflected
```

한국어로 말하면 다음과 같습니다.

```text
글 목록 보기
↓
글 상세 보기
↓
글 수정하기
↓
저장하기
↓
목록으로 돌아가기
↓
수정된 글이 목록에 반영되었는지 확인하기
```

---

## 2. 최종 목표

### 2.1 한 문장 목표

```text
A beginner-friendly OSS migration lab that modernizes a 2017 Android AAC ViewModel shared-state sample with Kotlin, Compose, ViewModel, and StateFlow.
```

한국어 설명:

```text
2017년식 Android AAC ViewModel 공유 상태 예제를 Kotlin, Compose, ViewModel, StateFlow 기반의 현대 Android 구조로 바꾸는 초보자 친화형 OSS 마이그레이션 학습 프로젝트입니다.
```

---

### 2.2 기술 선택

1차 리메이크에서는 선택지를 늘리지 않습니다. 아래로 고정합니다.

| 항목 | 선택 |
|---|---|
| 언어 | Kotlin |
| UI | Jetpack Compose |
| 상태관리 | ViewModel + StateFlow |
| DI | 1차 제외 |
| Navigation | 별도 라이브러리 없이 간단한 상태 전환 |
| 테스트 | Reducer 테스트 + ViewModel 테스트 |
| Legacy 보존 | branch, tag, `legacy-app/` |
| CI 빌드 대상 | 현대 `app/` 모듈만 |

---

## 3. 최종 산출 기준

아래 조건을 모두 만족하면 `v0.1.0-oss-alpha.1`을 만들 수 있습니다.

### 3.1 저장소 구조

- [ ] 원본 상태를 보존한 `legacy/2017-original` branch가 존재한다.
- [ ] 원본 상태를 가리키는 `v0.0.0-legacy-2017` tag가 존재한다.
- [ ] 기존 Android 앱 코드는 `legacy-app/`에 읽기용 아카이브로 보존되어 있다.
- [ ] 현대 Android 앱은 `app/`에 존재한다.
- [ ] 1차 CI 빌드 대상은 `app/` 하나뿐이다.

---

### 3.2 OSS 기본 파일

아래 파일이 존재해야 합니다.

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
.github/workflows/android.yml
```

---

### 3.3 앱 기능

아래 흐름이 실제 앱에서 동작해야 합니다.

```text
Article List → Article Detail → Edit → Save → List 반영
```

확인할 동작:

- [ ] Article 목록이 보인다.
- [ ] Article을 누르면 상세 화면으로 이동한다.
- [ ] 제목과 본문을 수정할 수 있다.
- [ ] Save를 누르면 목록 화면으로 돌아온다.
- [ ] 목록에서 수정된 내용이 보인다.
- [ ] Article을 삭제할 수 있다.
- [ ] 선택된 Article을 삭제하면 선택 상태가 초기화된다.

---

### 3.4 테스트

아래 테스트가 통과해야 합니다.

- [ ] Article 선택 시 `selectedArticleId`가 변경된다.
- [ ] Article 수정 시 목록의 해당 Article이 변경된다.
- [ ] Article 삭제 시 목록에서 제거된다.
- [ ] 선택된 Article 삭제 시 `selectedArticleId`가 `null`이 된다.
- [ ] ViewModel이 StateFlow로 최신 UI 상태를 노출한다.

명령어:

```bash
./gradlew test
```

Windows:

```bash
gradlew.bat test
```

---

### 3.5 CI

GitHub Actions에서 아래 명령어가 통과해야 합니다.

```bash
./gradlew test
./gradlew assembleDebug
```

---

### 3.6 문서

아래 문서가 존재해야 합니다.

```text
docs/01-legacy-architecture.md
docs/02-modern-architecture.md
docs/03-shared-state-problem.md
docs/04-livedata-to-stateflow.md
docs/05-compose-ui.md
docs/codex-for-oss.md
```

---

### 3.7 릴리스

아래 태그가 존재해야 합니다.

```text
v0.1.0-oss-alpha.1
```

그리고 GitHub Release에 릴리스 노트가 있어야 합니다.

---

## 4. 권장 최종 저장소 구조

최종적으로 저장소는 아래 구조를 목표로 합니다.

```text
android-viewmodel-migration-lab/
├─ README.md
├─ LICENSE
├─ CONTRIBUTING.md
├─ SECURITY.md
├─ CODE_OF_CONDUCT.md
├─ CHANGELOG.md
├─ settings.gradle.kts
├─ build.gradle.kts
├─ gradlew
├─ gradlew.bat
├─ gradle/
├─ app/
│  └─ modern Kotlin + Compose Android app
├─ legacy-app/
│  └─ original 2017 Android AAC sample, archive only
├─ docs/
│  ├─ 01-legacy-architecture.md
│  ├─ 02-modern-architecture.md
│  ├─ 03-shared-state-problem.md
│  ├─ 04-livedata-to-stateflow.md
│  ├─ 05-compose-ui.md
│  └─ codex-for-oss.md
└─ .github/
   ├─ pull_request_template.md
   ├─ ISSUE_TEMPLATE/
   │  ├─ bug_report.md
   │  └─ migration_task.md
   └─ workflows/
      └─ android.yml
```

중요:

```text
legacy-app/는 읽기용입니다.
1차 단계에서 legacy-app/는 빌드하지 않습니다.
현대 앱의 빌드 대상은 app/ 하나입니다.
```

`settings.gradle.kts`에는 1차에서 아래처럼 `:app`만 포함합니다.

```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidViewModelMigrationLab"
include(":app")
```

---

## 5. 전체 실행 순서 요약

작업은 아래 순서로 진행합니다.

```text
0단계: 목표 정리 및 원본 보존
1단계: OSS 메타/가이드 기본 구성
2단계: 현대 Android 앱 뼈대 정비
3단계: 도메인/상태 모델 정리
4단계: 테스트 기반 완성
5단계: ViewModel/화면 구현
6단계: CI/문서/릴리스
```

진행 원칙:

```text
작은 작업
→ 로컬 확인
→ 테스트
→ 커밋
→ Push
→ 다음 작업
```

---

## 6. 0단계: 목표 정리 및 원본 보존

### 6.1 목적

원본 프로젝트를 잃어버리지 않도록 안전하게 보존합니다.

리메이크를 진행하다가 실수해도 언제든 원본으로 돌아갈 수 있어야 합니다.

---

### 6.2 할 일

#### 6.2.1 저장소 clone

GitHub Desktop 또는 터미널로 저장소를 가져옵니다.

```bash
git clone https://github.com/leesunghyun/Android-AAC-ViewModel-Practice.git
cd Android-AAC-ViewModel-Practice
```

---

#### 6.2.2 원본 branch 생성

```bash
git checkout master
git checkout -b legacy/2017-original
git push origin legacy/2017-original
```

주의:

- 기본 branch가 `master`가 아니라면 현재 기본 branch 이름을 사용합니다.
- GitHub Desktop을 사용한다면 `New Branch` 버튼으로 만들어도 됩니다.

---

#### 6.2.3 원본 tag 생성

```bash
git checkout legacy/2017-original
git tag v0.0.0-legacy-2017
git push origin v0.0.0-legacy-2017
```

뜻:

```text
v0.0.0-legacy-2017
= 리메이크 전 원본을 고정해두는 이름표
```

---

#### 6.2.4 main branch 생성

```bash
git checkout master
git checkout -b main
git push origin main
```

GitHub repository 설정에서 기본 branch를 `main`으로 변경합니다.

경로:

```text
GitHub repository
→ Settings
→ Branches
→ Default branch
→ main
```

---

#### 6.2.5 기존 README 보존

기존 `README.md` 내용을 아래 파일로 이동합니다.

```text
docs/01-legacy-architecture.md
```

파일 맨 위에는 아래 제목을 넣습니다.

```md
# 2017 Legacy Architecture
```

---

### 6.3 완료 기준

- [ ] `legacy/2017-original` branch가 GitHub에 있다.
- [ ] `v0.0.0-legacy-2017` tag가 GitHub에 있다.
- [ ] `main` branch가 기본 branch다.
- [ ] 기존 README가 `docs/01-legacy-architecture.md`에 보존되어 있다.

---

### 6.4 추천 커밋 메시지

```text
Preserve original 2017 legacy baseline
```

---

## 7. 1단계: OSS 메타/가이드 기본 구성

### 7.1 목적

저장소를 오픈소스 프로젝트처럼 보이게 만드는 단계입니다.

코드보다 먼저 해야 합니다.  
왜냐하면 처음 방문한 사람이 README를 보고 프로젝트의 목적을 이해해야 하기 때문입니다.

---

### 7.2 README.md 재작성

루트의 `README.md`를 새로 작성합니다.

권장 첫 문장:

```md
# Android ViewModel Migration Lab

A beginner-friendly OSS migration lab that modernizes a 2017 Android AAC ViewModel shared-state sample with Kotlin, Compose, ViewModel, and StateFlow.
```

README에는 최소한 아래 섹션을 넣습니다.

```md
## What this project teaches

## Original problem

## Modern solution

## Quick start

## Project structure

## Migration docs

## Roadmap

## Contributing

## License
```

---

### 7.3 LICENSE 추가

추천 라이선스:

```text
Apache-2.0
```

GitHub에서 만드는 방법:

```text
Add file
→ Create new file
→ LICENSE
→ Choose a license template
→ Apache License 2.0
```

---

### 7.4 CONTRIBUTING.md 추가

파일:

```text
CONTRIBUTING.md
```

권장 내용:

```md
# Contributing

Thank you for your interest in contributing.

This project is a beginner-friendly migration lab for legacy Android architecture.

## How to contribute

1. Pick a small issue.
2. Create a small branch.
3. Make one focused change.
4. Run tests.
5. Open a pull request.

## Rules

- Keep each pull request small.
- Do not mix unrelated changes.
- Prefer readable code over clever code.
- Update documentation when behavior changes.
```

---

### 7.5 SECURITY.md 추가

파일:

```text
SECURITY.md
```

권장 내용:

```md
# Security Policy

This project is an educational Android migration sample.

Do not include secrets, API keys, private credentials, or personal data in issues or pull requests.

If you find a security issue in sample code or documentation, please open an issue with a clear explanation.
```

---

### 7.6 CODE_OF_CONDUCT.md 추가

파일:

```text
CODE_OF_CONDUCT.md
```

권장 내용:

```md
# Code of Conduct

This project is intended to be a friendly learning space.

Please be respectful, patient, and constructive when opening issues, reviewing pull requests, or asking questions.
```

---

### 7.7 Pull Request 템플릿 추가

파일:

```text
.github/pull_request_template.md
```

내용:

```md
## Summary

Explain what changed.

## Migration step

- [ ] Documentation
- [ ] Build / Gradle
- [ ] Domain model
- [ ] Reducer
- [ ] ViewModel
- [ ] Compose UI
- [ ] Tests
- [ ] CI

## How I tested this

- [ ] I ran `./gradlew test`
- [ ] I ran `./gradlew assembleDebug`
- [ ] I checked the app manually

## Notes

Write any failure reason, known issue, or follow-up task here.
```

---

### 7.8 Issue 템플릿 추가

폴더:

```text
.github/ISSUE_TEMPLATE/
```

파일 1:

```text
.github/ISSUE_TEMPLATE/bug_report.md
```

내용:

```md
# Bug Report

## What happened?

Describe the problem.

## Steps to reproduce

1.
2.
3.

## Expected result

What should have happened?

## Actual result

What happened instead?

## Environment

- OS:
- Android Studio version:
- JDK version:
- Device or emulator:

## Logs or screenshots

Paste logs or screenshots if available.
```

파일 2:

```text
.github/ISSUE_TEMPLATE/migration_task.md
```

내용:

```md
# Migration Task

## Goal

What migration step does this issue cover?

## Tasks

- [ ]
- [ ]
- [ ]

## Done when

- [ ] Code is updated
- [ ] Tests pass
- [ ] Documentation is updated
```

---

### 7.9 CHANGELOG.md 추가

파일:

```text
CHANGELOG.md
```

초기 내용:

```md
# Changelog

## v0.1.0-oss-alpha.1

### Added
- Modern Kotlin + Compose app baseline
- Article shared-state sample
- ViewModel + StateFlow state management
- Article reducer
- Reducer tests
- ViewModel tests
- Beginner-friendly migration documentation

### Preserved
- Original 2017 Android AAC ViewModel sample as legacy archive
```

---

### 7.10 완료 기준

- [ ] README가 새 프로젝트 목적을 설명한다.
- [ ] LICENSE가 있다.
- [ ] CONTRIBUTING.md가 있다.
- [ ] SECURITY.md가 있다.
- [ ] CODE_OF_CONDUCT.md가 있다.
- [ ] CHANGELOG.md가 있다.
- [ ] PR 템플릿이 있다.
- [ ] Issue 템플릿이 있다.

---

### 7.11 추천 커밋 메시지

```text
Add OSS project metadata and contribution guides
```

---

## 8. 2단계: 현대 Android 앱 뼈대 정비

### 8.1 목적

오래된 Gradle과 Android 설정을 억지로 업그레이드하지 않습니다.

대신 새 Kotlin + Compose 앱을 만들고, 기존 원본은 `legacy-app/`에 읽기용으로 보존합니다.

---

### 8.2 중요한 원칙

```text
기존 오래된 Gradle 설정을 직접 고치려고 하지 않는다.
새로운 Android Studio 프로젝트를 기준으로 현대 앱 뼈대를 만든다.
legacy-app/는 빌드 대상이 아니다.
app/만 빌드한다.
```

---

### 8.3 작업 순서

#### 8.3.1 Android Studio에서 새 프로젝트 생성

Android Studio에서:

```text
File
→ New
→ New Project
→ Empty Activity 또는 Empty Compose Activity
```

설정:

```text
Name: AndroidViewModelMigrationLab
Language: Kotlin
UI: Compose
Minimum SDK: 기본값 사용
```

---

#### 8.3.2 새 프로젝트 단독 실행 확인

새 프로젝트에서 아래를 확인합니다.

```bash
./gradlew test
./gradlew assembleDebug
```

또는 Android Studio의 Run 버튼으로 실행합니다.

---

#### 8.3.3 기존 app을 legacy-app으로 이동

기존 저장소에서:

```text
app/
↓
legacy-app/
```

주의:

```text
legacy-app/는 1차 단계에서 빌드하지 않습니다.
legacy-app/는 원본 참고용입니다.
```

---

#### 8.3.4 새 app을 복사

새 Android Studio 프로젝트에서 아래 항목을 기존 저장소로 복사합니다.

```text
app/
settings.gradle.kts
build.gradle.kts
gradle/
gradlew
gradlew.bat
```

---

#### 8.3.5 settings.gradle.kts 확인

1차에서는 아래처럼 `:app`만 포함합니다.

```kotlin
rootProject.name = "AndroidViewModelMigrationLab"
include(":app")
```

`legacy-app`는 넣지 않습니다.

---

#### 8.3.6 로컬 빌드 확인

macOS 또는 Linux:

```bash
./gradlew test
./gradlew assembleDebug
```

Windows:

```bash
gradlew.bat test
gradlew.bat assembleDebug
```

성공 메시지:

```text
BUILD SUCCESSFUL
```

---

### 8.4 완료 기준

- [ ] `legacy-app/`에 원본 코드가 있다.
- [ ] `app/`에 새 Kotlin + Compose 앱이 있다.
- [ ] `settings.gradle.kts`에는 `:app`만 포함된다.
- [ ] `./gradlew test`가 통과한다.
- [ ] `./gradlew assembleDebug`가 통과한다.

---

### 8.5 추천 커밋 메시지

```text
Add modern Android app baseline
```

---

## 9. 3단계: 도메인/상태 모델 정리

### 9.1 목적

화면 없이도 핵심 로직을 테스트할 수 있도록 도메인과 상태 모델을 분리합니다.

중요한 점:

```text
Article은 글 하나입니다.
ArticleListUiState는 목록 화면 전체 상태입니다.
```

---

### 9.2 만들 파일

권장 패키지:

```text
app/src/main/java/com/example/viewmodelmigration/core/
```

파일:

```text
Article.kt
SampleArticles.kt
ArticleListUiState.kt
ArticleListAction.kt
ArticleListReducer.kt
```

---

### 9.3 Article.kt

```kotlin
package com.example.viewmodelmigration.core

data class Article(
    val id: String,
    val title: String,
    val body: String
)
```

---

### 9.4 SampleArticles.kt

```kotlin
package com.example.viewmodelmigration.core

object SampleArticles {
    fun create(): List<Article> {
        return listOf(
            Article(
                id = "1",
                title = "Legacy ViewModel",
                body = "This article explains the original 2017 ViewModel idea."
            ),
            Article(
                id = "2",
                title = "StateFlow Migration",
                body = "This article explains how to migrate LiveData to StateFlow."
            ),
            Article(
                id = "3",
                title = "Compose UI",
                body = "This article explains how to render state with Compose."
            )
        )
    }
}
```

---

### 9.5 ArticleListUiState.kt

```kotlin
package com.example.viewmodelmigration.core

data class ArticleListUiState(
    val articles: List<Article> = emptyList(),
    val selectedArticleId: String? = null
)
```

---

### 9.6 ArticleListAction.kt

```kotlin
package com.example.viewmodelmigration.core

sealed interface ArticleListAction {
    data class SelectArticle(val articleId: String) : ArticleListAction
    data class UpdateArticle(val article: Article) : ArticleListAction
    data class DeleteArticle(val articleId: String) : ArticleListAction
}
```

---

### 9.7 ArticleListReducer.kt

```kotlin
package com.example.viewmodelmigration.core

object ArticleListReducer {

    fun reduce(
        state: ArticleListUiState,
        action: ArticleListAction
    ): ArticleListUiState {
        return when (action) {
            is ArticleListAction.SelectArticle -> {
                state.copy(
                    selectedArticleId = action.articleId
                )
            }

            is ArticleListAction.UpdateArticle -> {
                state.copy(
                    articles = state.articles.map { article ->
                        if (article.id == action.article.id) {
                            action.article
                        } else {
                            article
                        }
                    }
                )
            }

            is ArticleListAction.DeleteArticle -> {
                state.copy(
                    articles = state.articles.filterNot { article ->
                        article.id == action.articleId
                    },
                    selectedArticleId = if (state.selectedArticleId == action.articleId) {
                        null
                    } else {
                        state.selectedArticleId
                    }
                )
            }
        }
    }
}
```

---

### 9.8 완료 기준

- [ ] `Article` 모델이 있다.
- [ ] `SampleArticles`가 있다.
- [ ] `ArticleListUiState`가 있다.
- [ ] `ArticleListAction`이 있다.
- [ ] `ArticleListReducer`가 있다.
- [ ] Android 화면 없이 상태 변경 로직을 테스트할 수 있다.

---

### 9.9 추천 커밋 메시지

```text
Add Article state model and reducer
```

---

## 10. 4단계: 테스트 기반 완성

### 10.1 목적

앱을 실행하지 않아도 핵심 로직이 맞는지 확인합니다.

테스트는 자동 채점기입니다.

---

### 10.2 만들 파일

권장 패키지:

```text
app/src/test/java/com/example/viewmodelmigration/core/
```

파일:

```text
ArticleListReducerTest.kt
```

---

### 10.3 ArticleListReducerTest.kt

```kotlin
package com.example.viewmodelmigration.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ArticleListReducerTest {

    @Test
    fun selectArticle_updatesSelectedArticleId() {
        val oldState = ArticleListUiState(
            articles = SampleArticles.create()
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.SelectArticle("2")
        )

        assertEquals("2", newState.selectedArticleId)
    }

    @Test
    fun updateArticle_updatesArticleInList() {
        val oldArticle = Article(
            id = "1",
            title = "Old title",
            body = "Old body"
        )

        val updatedArticle = Article(
            id = "1",
            title = "New title",
            body = "New body"
        )

        val oldState = ArticleListUiState(
            articles = listOf(oldArticle)
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.UpdateArticle(updatedArticle)
        )

        assertEquals("New title", newState.articles.first().title)
        assertEquals("New body", newState.articles.first().body)
    }

    @Test
    fun deleteArticle_removesArticleFromList() {
        val oldState = ArticleListUiState(
            articles = SampleArticles.create()
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.DeleteArticle("1")
        )

        assertEquals(false, newState.articles.any { article ->
            article.id == "1"
        })
    }

    @Test
    fun deleteSelectedArticle_clearsSelectedArticleId() {
        val oldState = ArticleListUiState(
            articles = SampleArticles.create(),
            selectedArticleId = "1"
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.DeleteArticle("1")
        )

        assertNull(newState.selectedArticleId)
    }
}
```

---

### 10.4 테스트 실행

macOS 또는 Linux:

```bash
./gradlew test
```

Windows:

```bash
gradlew.bat test
```

---

### 10.5 완료 기준

- [ ] Article 선택 테스트가 통과한다.
- [ ] Article 수정 테스트가 통과한다.
- [ ] Article 삭제 테스트가 통과한다.
- [ ] 선택된 Article 삭제 테스트가 통과한다.
- [ ] `./gradlew test`가 성공한다.

---

### 10.6 추천 커밋 메시지

```text
Add reducer tests for Article state updates
```

---

## 11. 5단계: ViewModel 구현

### 11.1 목적

화면이 사용할 상태를 ViewModel에서 제공합니다.

ViewModel은 아래 역할을 합니다.

```text
현재 UI 상태를 StateFlow로 제공한다.
사용자의 행동을 Action으로 바꾼다.
Reducer를 사용해서 상태를 업데이트한다.
```

---

### 11.2 만들 파일

권장 패키지:

```text
app/src/main/java/com/example/viewmodelmigration/viewmodel/
```

파일:

```text
ArticleListViewModel.kt
```

---

### 11.3 ArticleListViewModel.kt

```kotlin
package com.example.viewmodelmigration.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viewmodelmigration.core.Article
import com.example.viewmodelmigration.core.ArticleListAction
import com.example.viewmodelmigration.core.ArticleListReducer
import com.example.viewmodelmigration.core.ArticleListUiState
import com.example.viewmodelmigration.core.SampleArticles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArticleListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ArticleListUiState(
            articles = SampleArticles.create()
        )
    )

    val uiState: StateFlow<ArticleListUiState> = _uiState.asStateFlow()

    fun selectArticle(articleId: String) {
        dispatch(ArticleListAction.SelectArticle(articleId))
    }

    fun updateArticle(article: Article) {
        dispatch(ArticleListAction.UpdateArticle(article))
    }

    fun deleteArticle(articleId: String) {
        dispatch(ArticleListAction.DeleteArticle(articleId))
    }

    private fun dispatch(action: ArticleListAction) {
        _uiState.update { oldState ->
            ArticleListReducer.reduce(
                state = oldState,
                action = action
            )
        }
    }
}
```

---

### 11.4 ViewModel 테스트 추가

권장 패키지:

```text
app/src/test/java/com/example/viewmodelmigration/viewmodel/
```

파일:

```text
ArticleListViewModelTest.kt
```

내용:

```kotlin
package com.example.viewmodelmigration.viewmodel

import com.example.viewmodelmigration.core.Article
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class ArticleListViewModelTest {

    @Test
    fun selectArticle_updatesSelectedArticleId() {
        val viewModel = ArticleListViewModel()

        viewModel.selectArticle("2")

        assertEquals("2", viewModel.uiState.value.selectedArticleId)
    }

    @Test
    fun updateArticle_updatesUiState() {
        val viewModel = ArticleListViewModel()

        val updatedArticle = Article(
            id = "1",
            title = "Updated title",
            body = "Updated body"
        )

        viewModel.updateArticle(updatedArticle)

        val article = viewModel.uiState.value.articles.first {
            it.id == "1"
        }

        assertEquals("Updated title", article.title)
        assertEquals("Updated body", article.body)
    }

    @Test
    fun deleteArticle_removesArticleFromUiState() {
        val viewModel = ArticleListViewModel()

        viewModel.deleteArticle("1")

        assertFalse(
            viewModel.uiState.value.articles.any {
                it.id == "1"
            }
        )
    }
}
```

---

### 11.5 완료 기준

- [ ] `ArticleListViewModel`이 있다.
- [ ] `uiState`가 `StateFlow<ArticleListUiState>`로 노출된다.
- [ ] `selectArticle` 함수가 있다.
- [ ] `updateArticle` 함수가 있다.
- [ ] `deleteArticle` 함수가 있다.
- [ ] ViewModel 테스트가 통과한다.
- [ ] `./gradlew test`가 통과한다.

---

### 11.6 추천 커밋 메시지

```text
Add StateFlow ArticleListViewModel
```

---

## 12. 6단계: Compose 화면 구현

### 12.1 목적

사용자가 실제로 Article 목록을 보고, 상세 화면에서 수정하고, 목록에 반영되는 것을 확인할 수 있게 합니다.

---

### 12.2 만들 파일

권장 패키지:

```text
app/src/main/java/com/example/viewmodelmigration/ui/
```

파일:

```text
ArticleListScreen.kt
ArticleDetailScreen.kt
```

---

### 12.3 ArticleListScreen.kt

```kotlin
package com.example.viewmodelmigration.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodelmigration.core.Article
import com.example.viewmodelmigration.core.ArticleListUiState

@Composable
fun ArticleListScreen(
    uiState: ArticleListUiState,
    onArticleClick: (String) -> Unit,
    onDeleteFirstClick: () -> Unit
) {
    Column {
        Button(
            onClick = onDeleteFirstClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Delete first article")
        }

        uiState.articles.forEach { article ->
            ArticleRow(
                article = article,
                onClick = {
                    onArticleClick(article.id)
                }
            )
        }
    }
}

@Composable
private fun ArticleRow(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = article.title)
            Text(text = article.body)
        }
    }
}
```

---

### 12.4 ArticleDetailScreen.kt

```kotlin
package com.example.viewmodelmigration.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodelmigration.core.Article

@Composable
fun ArticleDetailScreen(
    article: Article,
    onSaveClick: (Article) -> Unit,
    onBackClick: () -> Unit
) {
    val titleState = remember(article.id) {
        mutableStateOf(article.title)
    }

    val bodyState = remember(article.id) {
        mutableStateOf(article.body)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Edit Article")

        OutlinedTextField(
            value = titleState.value,
            onValueChange = {
                titleState.value = it
            },
            label = {
                Text("Title")
            }
        )

        OutlinedTextField(
            value = bodyState.value,
            onValueChange = {
                bodyState.value = it
            },
            label = {
                Text("Body")
            }
        )

        Button(
            onClick = {
                onSaveClick(
                    article.copy(
                        title = titleState.value,
                        body = bodyState.value
                    )
                )
            }
        ) {
            Text("Save")
        }

        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}
```

---

### 12.5 MainActivity 연결

`MainActivity.kt`를 아래 개념으로 정리합니다.

```kotlin
package com.example.viewmodelmigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelmigration.ui.ArticleDetailScreen
import com.example.viewmodelmigration.ui.ArticleListScreen
import com.example.viewmodelmigration.viewmodel.ArticleListViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppRoot()
        }
    }
}

@Composable
private fun AppRoot(
    viewModel: ArticleListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val selectedArticle = uiState.articles.firstOrNull {
        it.id == uiState.selectedArticleId
    }

    if (selectedArticle == null) {
        ArticleListScreen(
            uiState = uiState,
            onArticleClick = { articleId ->
                viewModel.selectArticle(articleId)
            },
            onDeleteFirstClick = {
                val firstArticle = uiState.articles.firstOrNull()

                if (firstArticle != null) {
                    viewModel.deleteArticle(firstArticle.id)
                }
            }
        )
    } else {
        ArticleDetailScreen(
            article = selectedArticle,
            onSaveClick = { updatedArticle ->
                viewModel.updateArticle(updatedArticle)
                viewModel.selectArticle("")
            },
            onBackClick = {
                viewModel.selectArticle("")
            }
        )
    }
}
```

주의:

위 예제는 초보자용 단순 구현입니다.  
다만 `selectArticle("")`은 조금 어색합니다. 가능하면 후속 작업에서 `ClearSelection` Action을 추가하는 것이 좋습니다.

더 좋은 후속 개선:

```kotlin
sealed interface ArticleListAction {
    data class SelectArticle(val articleId: String) : ArticleListAction
    data object ClearSelection : ArticleListAction
    data class UpdateArticle(val article: Article) : ArticleListAction
    data class DeleteArticle(val articleId: String) : ArticleListAction
}
```

1차에서는 단순 구현을 우선하고, `ClearSelection`은 별도 이슈로 분리해도 됩니다.

---

### 12.6 직접 확인할 동작

앱을 실행한 뒤 아래를 확인합니다.

- [ ] Article 목록이 보인다.
- [ ] Article을 누르면 상세 화면으로 이동한다.
- [ ] 제목을 수정할 수 있다.
- [ ] 본문을 수정할 수 있다.
- [ ] Save를 누르면 목록 화면으로 돌아온다.
- [ ] 목록에 수정된 제목과 본문이 보인다.
- [ ] Delete first article을 누르면 첫 번째 Article이 사라진다.

---

### 12.7 완료 기준

- [ ] `ArticleListScreen`이 있다.
- [ ] `ArticleDetailScreen`이 있다.
- [ ] `MainActivity`가 ViewModel과 화면을 연결한다.
- [ ] 핵심 흐름이 실제 앱에서 동작한다.
- [ ] `./gradlew assembleDebug`가 통과한다.

---

### 12.8 추천 커밋 메시지

```text
Add Compose article list and detail screens
```

---

## 13. 7단계: CI 추가

### 13.1 목적

GitHub가 자동으로 빌드와 테스트를 확인하게 만듭니다.

---

### 13.2 만들 파일

```text
.github/workflows/android.yml
```

---

### 13.3 android.yml

```yaml
name: Android CI

on:
  pull_request:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout
        uses: actions/checkout@v4

      - name: Set up Java
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 17

      - name: Make Gradle executable
        run: chmod +x ./gradlew

      - name: Run unit tests
        run: ./gradlew test

      - name: Build debug app
        run: ./gradlew assembleDebug
```

---

### 13.4 완료 기준

- [ ] GitHub Actions에 `Android CI`가 보인다.
- [ ] Pull Request에서 CI가 실행된다.
- [ ] `test`가 통과한다.
- [ ] `assembleDebug`가 통과한다.

---

### 13.5 추천 커밋 메시지

```text
Add Android CI workflow
```

---

## 14. 8단계: 문서 작성

### 14.1 목적

초보자가 코드를 몰라도 프로젝트의 흐름을 이해할 수 있게 합니다.

문서는 코드보다 중요할 수 있습니다.  
Codex for OSS 제출 관점에서도 문서화는 프로젝트의 신뢰도를 높입니다.

---

### 14.2 작성할 문서 목록

```text
docs/01-legacy-architecture.md
docs/02-modern-architecture.md
docs/03-shared-state-problem.md
docs/04-livedata-to-stateflow.md
docs/05-compose-ui.md
docs/codex-for-oss.md
```

---

### 14.3 docs/02-modern-architecture.md

권장 내용:

```md
# Modern Architecture

This document explains the modern version of the sample.

## Stack

- Kotlin
- Compose
- ViewModel
- StateFlow
- Reducer-style state updates

## Main idea

The app has one UI state object:

```kotlin
ArticleListUiState
```

The ViewModel exposes that state as:

```kotlin
StateFlow<ArticleListUiState>
```

Screens read the state and send user actions back to the ViewModel.
```

---

### 14.4 docs/03-shared-state-problem.md

권장 내용:

```md
# Shared State Problem

The original sample teaches a common Android problem:

When the same data is shown on multiple screens, how do we keep the screens in sync?

## Example

1. The list screen shows Articles.
2. The detail screen edits one Article.
3. After saving, the list screen should show the updated Article.

## Modern solution

The modern version keeps the list and selected Article inside one UI state object.
```

---

### 14.5 docs/04-livedata-to-stateflow.md

권장 내용:

```md
# LiveData to StateFlow

The legacy version used early Android Architecture Components.

The modern version uses StateFlow.

## Before

The old ViewModel exposed mutable observable data.

## After

The new ViewModel exposes immutable UI state as StateFlow.

```kotlin
val uiState: StateFlow<ArticleListUiState>
```

## Why this helps

- One state object is easier to test.
- Reducer logic can be tested without Android UI.
- Screens become simpler.
```

---

### 14.6 docs/05-compose-ui.md

권장 내용:

```md
# Compose UI

The modern version uses Compose to render UI from state.

## Rule

State goes down.
Events go up.

## Example

- `ArticleListScreen` receives `ArticleListUiState`
- User clicks an Article
- Screen calls `onArticleClick(articleId)`
- ViewModel updates state
- UI redraws automatically
```

---

### 14.7 docs/codex-for-oss.md

권장 내용:

```md
# Codex for OSS Notes

This repository is prepared as an OSS migration lab for legacy Android maintainers.

## Why this project matters

Many Android projects still contain older patterns such as:

- Java
- Support Library
- early LiveData
- DataBinding
- manual Fragment transactions

This repository provides a small, readable, tested migration path to modern Android architecture.

## How Codex can help

Codex can help with:

- generating migration pull requests
- adding regression tests
- reviewing architecture changes
- updating deprecated Android APIs
- maintaining documentation
- creating beginner-friendly explanations
```

---

### 14.8 완료 기준

- [ ] 문서가 3개 이상 있다.
- [ ] 권장 문서 6개가 모두 있으면 더 좋다.
- [ ] README에서 docs 링크가 연결되어 있다.
- [ ] 코드 모르는 사람도 핵심 흐름을 이해할 수 있다.

---

### 14.9 추천 커밋 메시지

```text
Add migration documentation
```

---

## 15. 9단계: GitHub Issues 만들기

### 15.1 목적

작업을 작게 나누고 추적합니다.

초보자는 큰 작업을 보면 막막합니다.  
Issue로 나누면 하나씩 처리할 수 있습니다.

---

### 15.2 만들 Issue 목록

아래 제목으로 Issue를 만듭니다.

```text
Preserve original 2017 legacy baseline
Add OSS metadata files
Add modern Kotlin Compose baseline
Add Article domain model
Add Article reducer
Add reducer tests
Add StateFlow ViewModel
Add ViewModel tests
Add Compose Article list screen
Add Compose Article detail screen
Add Android CI workflow
Write legacy architecture guide
Write modern architecture guide
Write shared state problem guide
Write LiveData to StateFlow guide
Write Compose UI guide
Prepare Codex for OSS notes
Prepare v0.1.0-oss-alpha.1 release
```

---

### 15.3 Issue 작성 예시

제목:

```text
Add Article reducer
```

내용:

```md
## Goal

Create reducer logic for Article list state updates.

## Tasks

- [ ] Create ArticleListAction
- [ ] Create ArticleListReducer
- [ ] Support SelectArticle
- [ ] Support UpdateArticle
- [ ] Support DeleteArticle

## Done when

- [ ] Reducer code exists
- [ ] Reducer tests pass
```

---

## 16. 10단계: 릴리스 준비

### 16.1 목적

첫 번째 공개 가능한 버전을 만듭니다.

---

### 16.2 릴리스 전 확인

아래 명령어가 모두 통과해야 합니다.

```bash
./gradlew test
./gradlew assembleDebug
```

확인할 파일:

```text
README.md
LICENSE
CONTRIBUTING.md
SECURITY.md
CODE_OF_CONDUCT.md
CHANGELOG.md
.github/workflows/android.yml
docs/
```

---

### 16.3 태그 생성

```bash
git tag v0.1.0-oss-alpha.1
git push origin v0.1.0-oss-alpha.1
```

---

### 16.4 GitHub Release 작성

Release 제목:

```text
v0.1.0-oss-alpha.1 - Modern Android Migration Lab Alpha
```

Release 내용:

```md
## Summary

This is the first OSS alpha release of Android ViewModel Migration Lab.

## Added

- Modern Kotlin + Compose app baseline
- Article shared-state sample
- ViewModel + StateFlow state management
- Reducer-based state updates
- Reducer tests
- ViewModel tests
- GitHub Actions CI
- Beginner-friendly migration documentation

## Preserved

- Original 2017 Android AAC ViewModel sample as legacy archive

## Known limitations

- No DI framework in the first alpha
- No Navigation library in the first alpha
- Legacy app is preserved as archive only and is not part of the CI build
```

---

### 16.5 완료 기준

- [ ] `v0.1.0-oss-alpha.1` tag가 있다.
- [ ] GitHub Release가 있다.
- [ ] Release note가 있다.
- [ ] README에서 release 상태를 설명한다.

---

## 17. Codex for OSS 신청 준비 문구

### 17.1 Repository description

```text
A beginner-friendly OSS migration lab for modernizing legacy Android ViewModel shared-state samples with Kotlin, Compose, ViewModel, and StateFlow.
```

---

### 17.2 Why does this repository qualify?

```text
This project helps maintainers modernize legacy Android apps built with older Android Architecture Components patterns. It preserves a 2017 ViewModel shared-state sample and rebuilds it with Kotlin, Compose, ViewModel, and StateFlow. The repository provides runnable before/after references, tests, CI, and beginner-friendly migration documentation.
```

---

### 17.3 How will you use Codex?

```text
We will use Codex to generate focused migration pull requests, add regression tests, review state-management changes, update deprecated Android APIs, maintain documentation, and create beginner-friendly explanations for legacy Android maintainers.
```

---

## 18. 제외 항목

1차 단계에서는 아래를 하지 않습니다.

- Hilt 도입
- Dagger 재구성
- Room DB 도입
- 네트워크 API 연동
- Firebase 연동
- 다국어 처리
- Navigation 라이브러리 도입
- 멀티 모듈 구조
- 예약/매출/고객/Reserve.M 도메인 확장
- 과도한 디자인 시스템 구축
- 실제 서비스 수준의 기능 확장

이유:

```text
1차 목표는 작고 명확한 OSS 마이그레이션 샘플을 만드는 것입니다.
기능이 많아지면 초보자가 이해하기 어려워집니다.
```

---

## 19. 리스크 및 대응

| 리스크 | 설명 | 대응 |
|---|---|---|
| 레거시 원본이 사라짐 | 새 앱으로 바꾸다가 원본을 잃을 수 있음 | branch, tag, legacy-app으로 3중 보존 |
| Gradle 설정이 깨짐 | 오래된 설정과 새 설정이 섞일 수 있음 | legacy-app은 빌드 대상에서 제외하고 app만 빌드 |
| 초보자가 이해하기 어려움 | Kotlin/Compose/StateFlow가 처음일 수 있음 | 문서와 주석을 단계별로 작성 |
| 범위가 커짐 | Hilt, Navigation, DB까지 넣고 싶어질 수 있음 | 1차 제외 항목을 지킨다 |
| CI 실패가 많아짐 | 빌드 대상이 복잡하면 실패 가능성 증가 | :app만 빌드하고 PR 템플릿에 실패 원인 기록 |
| 프로젝트 정체성이 흐려짐 | Reserve.M이나 예약 도메인이 섞일 수 있음 | Article 도메인만 사용한다 |

---

## 20. 작업 단위별 추천 커밋 목록

아래 커밋들이 순서대로 쌓이면 좋습니다.

```text
Preserve original 2017 legacy baseline
Add OSS project metadata and contribution guides
Add modern Android app baseline
Add Article state model and reducer
Add reducer tests for Article state updates
Add StateFlow ArticleListViewModel
Add ViewModel tests
Add Compose article list and detail screens
Add Android CI workflow
Add migration documentation
Prepare v0.1.0-oss-alpha.1 release notes
```

---

## 21. 최종 체크리스트

작업 완료 전에 아래를 확인합니다.

### 21.1 정체성

- [ ] Reserve.M 문구가 없다.
- [ ] 예약/매출/고객/ダブルブッキング 문구가 없다.
- [ ] 프로젝트 설명이 Android ViewModel Migration Lab으로 통일되어 있다.
- [ ] Article 도메인만 사용한다.

---

### 21.2 코드

- [ ] `app/`가 빌드된다.
- [ ] `legacy-app/`는 빌드 대상이 아니다.
- [ ] `Article` 모델이 있다.
- [ ] `ArticleListUiState`가 있다.
- [ ] `ArticleListReducer`가 있다.
- [ ] `ArticleListViewModel`이 있다.
- [ ] `ArticleListScreen`이 있다.
- [ ] `ArticleDetailScreen`이 있다.

---

### 21.3 테스트

- [ ] `./gradlew test`가 통과한다.
- [ ] Article 선택 테스트가 있다.
- [ ] Article 수정 테스트가 있다.
- [ ] Article 삭제 테스트가 있다.
- [ ] 선택된 Article 삭제 테스트가 있다.
- [ ] ViewModel 테스트가 있다.

---

### 21.4 CI

- [ ] GitHub Actions workflow가 있다.
- [ ] Pull Request에서 CI가 실행된다.
- [ ] `test`가 통과한다.
- [ ] `assembleDebug`가 통과한다.

---

### 21.5 문서

- [ ] README가 명확하다.
- [ ] Quick start가 있다.
- [ ] Project structure가 있다.
- [ ] Migration docs 링크가 있다.
- [ ] `docs/01-legacy-architecture.md`가 있다.
- [ ] `docs/02-modern-architecture.md`가 있다.
- [ ] `docs/03-shared-state-problem.md`가 있다.
- [ ] `docs/04-livedata-to-stateflow.md`가 있다.
- [ ] `docs/05-compose-ui.md`가 있다.
- [ ] `docs/codex-for-oss.md`가 있다.

---

### 21.6 릴리스

- [ ] `CHANGELOG.md`가 있다.
- [ ] `v0.1.0-oss-alpha.1` tag가 있다.
- [ ] GitHub Release가 있다.
- [ ] Release note가 있다.

---

## 22. 1차 완료 후 다음 단계

`v0.1.0-oss-alpha.1` 이후에 고려할 수 있는 작업입니다.

단, 1차 완료 전에는 하지 않습니다.

```text
v0.2.0
- ClearSelection Action 추가
- Navigation Compose 도입 검토
- UI 테스트 추가
- README에 GIF 추가

v0.3.0
- Legacy LiveData 버전과 Modern StateFlow 버전 비교 문서 강화
- Before/After 코드 비교 페이지 추가
- 초보자용 용어 사전 추가

v1.0.0
- 문서 안정화
- 샘플 앱 안정화
- Codex for OSS 신청 준비 완료
```

---

## 23. 가장 먼저 해야 할 일

지금 바로 시작한다면 아래 5개만 먼저 합니다.

```text
1. legacy/2017-original branch 만들기
2. v0.0.0-legacy-2017 tag 만들기
3. 기존 README를 docs/01-legacy-architecture.md로 보존하기
4. README.md를 새 프로젝트 설명으로 바꾸기
5. LICENSE, CONTRIBUTING, SECURITY, CODE_OF_CONDUCT 추가하기
```

이 5개가 끝나면 프로젝트 방향이 안전하게 고정됩니다.

---

## 24. 최종 결론

이 계획의 핵심은 단순합니다.

```text
원본은 보존한다.
현대 앱은 작게 만든다.
Article 흐름만 다룬다.
테스트로 핵심 로직을 증명한다.
문서로 초보자가 따라올 수 있게 한다.
CI로 항상 빌드 가능한 상태를 유지한다.
```

프로젝트의 최종 정체성은 아래 한 문장입니다.

```text
Android ViewModel Migration Lab is a beginner-friendly OSS project that teaches how to migrate a legacy Android shared-state sample to modern Kotlin, Compose, ViewModel, and StateFlow architecture.
```
