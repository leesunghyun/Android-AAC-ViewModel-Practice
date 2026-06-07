# Historical Reference Only

This document is an archived planning reference.

Do not use this document as the execution plan.

The source of truth is:
- `oss-remake-task-plan.md`
- `README.md`
- GitHub Issues

This file may include outdated ideas, discarded alternatives, or rough notes from earlier planning (including references, rough drafts, and broken citation fragments).

---

# 초보자용 전체 진행 순서

목표는 이겁니다.

> 기존 `Android-AAC-ViewModel-Practice`를
> **“옛날 Android 코드를 최신 Android 방식으로 바꾸는 학습용 오픈소스 프로젝트”**로 바꾸기

현재 저장소는 이미 좋은 주제를 가지고 있습니다. README에는 **여러 화면에서 같은 Article 데이터를 공유하고, Detail 화면에서 수정하면 List 화면도 갱신되는 구조**가 설명되어 있습니다. 
하지만 코드는 er()`와 Android Gradle Plugin `2.3.1`을 쓰고 있고, :contentReference[oaicite:2]{index=2} `compileSdkVersion 25`, Support Library 25, Lifecycle `1.0.0-alpha1`을 사용합니다. 

그래서 우리는 이 저장소를 이렇게 바꿉니다.

````text
옛날 Android AAC 연습 프로젝트
↓
레거시 Android ViewModel . 아주 쉽게 비유하면 이렇습니다.

| 단어 | 아주 쉬운 뜻 |
|---|---|
| GitHub | 내 프로젝트를 올려두는 인터넷 책장 |
| Repository / repo | 프로젝트 폴더 |
| Branch | 원본을 망치지 않기 위한 복사본 길 |
| Commit | 저장 지점 |
| Pull Request / PR | “이렇게 고쳤어요, 확인해주세요”라고 올리는 변경 묶음 |
| README | GitHub 프로젝트 첫 화면 설명서 |
| CI | GitHub가 자동으로 코드를 검사해주는 로봇 |
| License | 다른 사람이 이 코드를 써도 되는지 알려주는 규칙 |
| Android Studio | Android 앱을 만드는 프로그램 |
| Gradle | Android 앱을 조립해주는 도구 |
| ViewModel | 화면에 보여줄 데이터를 보관하는 상자 |
| StateFlow | 데이터가 바뀌면 화면에 알려주는 현대식 흐름 |
| Compose | Android 화면을 만드는 최신 방식 중 하나 |

---

# 1. 전체 큰 그림

이 프로젝트는 한 번에 완성하지 않습니다.

작은 단계로 나눠서 진행합니다.

```text
1단계: 원본 저장하기
2단계: README 바꾸기
3단계: 오픈소스 기본 파일 만들기
4단계: 최신 Android 프로젝트 뼈대 만들기
5단계: Article 데이터 구조 만들기
6단계: ViewModel 만들기
7단계: 테스트 만들기
8단계: 화면 만들기
9단계: 문서 만들기
10단계: Codex for OSS 제출 준비하기
````

중요한 원칙은 이것입니다.

> **한 번에 큰 수정 금지.**
> 작은 수정 → 저장 → 확인 → GitHub에 올리기 → 다음 수정

---

# 2. 준비물 설치하기

## 2-1. 필요한 프로그램

먼저 아래 프로그램이 필요합니다.

| 프로그램           | 용도                      |
| -------------- | ----------------------- |
| GitHub 계정      | 프로젝트 관리                 |
| GitHub Desktop | 초보자가 Git을 쉽게 쓰기 위한 프로그램 |
| Android Studio | Android 앱 만들기           |
| Git            | 코드 저장/업로드 도구            |
| Java 17        | Android 빌드에 필요한 도구      |

초보자라면 **GitHub Desktop**을 꼭 쓰는 것을 추천합니다. 터미널 명령어를 많이 몰라도 됩니다.

---

## 2-2. 폴더 만들기

컴퓨터에 작업 폴더를 하나 만듭니다.

예:

```text
Documents/
└─ oss-projects/
```

이 안에 프로젝트를 받을 겁니다.

---

# 3. GitHub에서 프로젝트 가져오기

## 3-1. GitHub Desktop으로 가져오기

1. GitHub Desktop 실행
2. itory`
3. `URL` 선택
4. 아래 주소 입력

```text
https://github.com/leesunghyun/Android-AAC-ViewModel-Practice
```

6. 저장 위치를 선택

```text
Documents/oss-projects/
```

7. `Clone` 버튼 클릭

성공하면([Android Developers][1])니다.

```text
Documents/oss-projects/Android-AAC-ViewModel-Practice
```

---

## ([Android Developers][1])io로 열기

1. Android Studio 실행
2. `Open` 클릭
3. 방금 받은 폴더 선택

```text
Android-AAC-ViewModel-Practice
```

4. 프로젝트가 열릴 때까지 기다립니다.

처음에는 에러가 날 가능성이 높습니다. 괜찮습니다. 이 프로젝트는 오래된 프로젝트라서 당연합니다.

---

# 4. 1단계: 원본 저장하기

가장 먼저 해야 할 일은 **옛날 코드를 안전하게 보관하는 것**입니다.

왜냐하면 앞으로 많이 고칠 예정이라 원본을 잃어버리면 안 됩니다.

---

## 4-1. GitHub Desktop에서 branch 만들기

1. GitHub Desktop 열기
2. 현재 repository가 `Android-AAC-ViewModel-Practic([OpenAI][2])nch 이름 클릭
3. `New Branch` 클릭
4. branch 이름 입력

```text
legacy/2017-original
```

6. `Create Branch` 클릭
7. `Publish branch` 클릭

이렇게 하면([OpenAI][2])ranch가 생깁니다.

---

## 4-2. 원본 tag 만들기

이건 터미널에서 해야 합니다.

Android Studio 아래쪽에 `Terminal` 탭을 엽니다.

아래 명령어를 한 줄씩 입력합니다.

```bash
git checkout master
git tag v0.0.0-legacy-2017
git push origin v0.0.0-legacy-2017
```

성공하면 원본 저장 지점이 생깁니다.

뜻은 이겁니다.

```text
v0.0.0-legacy-2017
= 2017년 원본 상태를 저장한 이름표
```

---

## 4-3. 성공 확인

GitHub 웹사이트에서 repository에 들어갑니다.

확인할 것:

* branch 목록에 `legacy/2017-original`이 있는가?
* tag 목록에 `v0.0.0-legacy-2017`이 있는가?

있으면 성공입니다.

---

# 5. 2단계: main branch 만들기

옛날 프로젝트는 `master` branch를 쓰고 있을 가능성이 높습니다.

요즘은 보통 `main`을 씁니다.

---

## 5-1. GitHub Desktop에서 main branch 만들기

1. GitHub Desktop 열기
2. 현재 branch를 `master`로 변경
3. branch 메뉴 ``text
   main

````

6. `Create Branch`
7. `Publish branch`

---

## 5-2. GitHub 기본 branchitory 들어가기
2. `Settings`
3. 왼쪽 메뉴 `Branches`
4. `Default branch`
5. `main`으로 변경

---

## 5-3. 성공 확인

GitHub repository 첫 화면에서 branch가 `main`으로 보이면 성공입니다.

---

# 6. 3단계: 프로젝트 이름과 설명 바꾸기

지금 이름은 e`입니다.

그냥 써도 되지만, 제출용으로는 조금 약합니다.  
가능하면 이름을 바꾸는가장 추천하는 이름:

```text
android-viewmodel-migration-lab
````

뜻:

```text
Android ViewModel을 옛날 방식에서 최신 방식으로 바꾸는 실험실
```

---

## 6-2. GitHub에서 이름 바꾸기

1. GitHub repository 들어가기
2. name`
3. 아래 이름으로 변경

```text
android-viewmodel-migration-lab
```

5. `Rename` ub repository 첫 화면에서 오른쪽 위나 About 영역에 설명을 넣습니다.

```text
A practical migration lab for modernizing legacy Android ViewModel and LiveData apps.
```

한국어로 뜻은:

```text
오래된 Android ViewModel / LiveData 앱을 최신 방식으로 바꾸는 학습 프로젝트
```

---

# 7. 4단계: README 바꾸기

README는 프로젝트의 얼굴입니다.

현재 README는 Demo, Branch, purpose 중심으로 되어 있고, `basic-livedata-viewmodel`, `dagger`, `dagger-databinding` branch 설명이 있습니다. 
이제 README를 제출용으로 바꿔야 합니다.

---

## 7-1. 기존 README 백업하기

프로젝트 폴더에서 새 폴더를 만듭니다.

```text
docs/
```

그리고 기존 `README.md` 내용을 복사해서 새 파일에 붙여넣습니다.

파일 이름:

```text
docs/01-legacy-architecture.md
```

제목은 이렇게 바꿉니다.

```md
# 2017 Legacy Architecture
```

---

## 7-2. 새 README 만들기

루트에 있는 `README.md`를 열고 전부 지운 뒤 아래 내용을 넣습니다.

```md
# Android ViewModel Migration Lab

A practical migration lab for maintainers of legacy Android apps.

This repository starts from a real 2017-era Android Architecture Components sample built with Java, Support Library, LiveData alpha, DataBinding, Dagger 2, and manual Fragment transactions.

It modernizes the same shared-state problem step by step using AndroidX, Kotlin, StateFlow, Hilt, Navigation, and Jetpack Compose.

## What problem does this project solve?

Many legacy Android apps share the ens.

In the original sample, an Article is shown in a list screen and edited in a detail screen. When the detail screen saves the Article, the list screen should also show the updated data.

This project shows how to solve that problem in both legacy and modern Android styles.

## Migration Map

| Legacy sample | Modern version |
|---|---|
| Java | Kotlin |
| Support Library | AndroidX |
| LiveData alpha | StateFlow |
| DataBinding | Compose |
| Dagger 2:contentReference[oaicite:20]{index=20}l Fragment transactions | Navigation |
| Mutable nested state | Immutable UI state |
| No CI | GitHub Actions |

## Project Goals

- Preserve the original 2017 Android Architecture Components idea
- Explain the legacy architecture
- Rebuild the sample with modern Android tools
- Add tests for shared state updates
- Provide step-by-step migration guides

## Roadmap

- [ ] Preserve the 2017 legacy baseline
- [ ] Add modern Android project baseline
- [ ] Extract Article domain model
- [ ] Replace nested LiveData with StateFlow
- [ ] Add ViewModel tests
- [ ] Add Navigation example
- [ ] Add Hilt example
- [ ] Add Compose example
- [ ] Prepare v1.0.0 release

## Documentation

- [2017 Legacy Architecture](docs/01-legacy-architecture.md)

## License

This project is licensed under the Apache License 2.0.
```

---

## 7-3. 저장하고 commit 하기

GitHub Desktop에서:

1. 변경 파일 확인
2. 아래 summary에 입력

```text
Rewrite README as Android ViewModel migration lab
```

3. `Commit to main`
4. `Push origin`

---

## 7-4. 성공 확인

GitHub repository 첫 화면에 새 README가 보이면 성공입니다.

---

# 8. 5단계: 오픈소스 기본 파일 만들기

오픈소스처럼 보이려면 기본 문서가 필요합니다.

만들 파일은 5개입니다.

```text
LICENSE
CONTRIBUTING.md
CODE_OF_CONDUCT.md
SECURITY.md
.github/pull_request_template.md
```

---

## 8-1. LICENSE 만들기

루트 폴더에 `LICENSE` 파일을 만듭니다.

추천은 Apache-2.0입니다.

초보자는 GitHub에서 자동 생성하는 게 쉽습니다.

1. GitHub repository 접속
2. `Add file`
3. `Create new file`
4. 파일 이름에 입력

```text
LICENSE
```

5. 오른쪽에 `Choose a license template`
6. `Apache License 2.0` 선택
7. 저장

---

## 8-2. CONTRIBUTING.md 만들기

루트에 `CONTRIBUTING.md` 파일을 만들고 아래 내용을 넣습니다.

```md
# Contributing

Thank you for your interest in contributing.

This project is a learning-focused migration lab for legacy Android architecture.

## How to contribute

1. Pick an issue from the roadmap.
2. Create a small pull request.
3. Explain what changed.
4. Add or update tests when possible.
5. Update documentation when the migration behavior changes.

## Pull request rules

- Keep each pull request small.
- Do not mix unrelated changes.
- Prefer readable code over clever code.
- Add documentation for migration steps.
```

---

## 8-3. SECURITY.md 만들기

루트에 `SECURITY.md` 파일을 만들고 아래 내용을 넣습니다.

```md
# Security Policy

This project is an educational Android migration sample.

If you find a security issue in the sample code or documentation, please open an issue with a clear explanation.

Do not include secrets, API keys, or private credentials in issues or pull requests.
```

---

## 8-4. PR template 만들기

폴더를 만듭니다.

```text
.github/
```

그 안에 파일을 만듭니다.

```text
.github/pull_request_template.md
```

내용:

```md
## Summary

Explain what changed.

## Migration step

Which migration step  ] Documentation
- [ ] Gradle / build
- [ ] AndroidX
- [ ] Kotlin
- [ ] StateFlow
- [ ] Navigatio [ ] Tests

## Checklist

- [ ] I kept this PR small
- [ ] I updated documentation if needed
- [ ] I added or updated tests if needed:contentReference[oaicite:23]{index=23}t builds locally
```

---

## 8-5. commit 하기

GitHub Desktop에서 summary 입력:

```text
Add open source project files
```

그리고:

```text
Commit to main
Push origin
```

---

# 9. 6단계: 작업 목록을 GitHub Issues로 만들기

이제 해야 할 일을 GitHub Issues에 적어둡니다.

이건 숙제 목록 같은 겁니다.

---

## 9-1. Issue 만들기

GitHub repository에서:

1. `Issues`
2. `New issue`
3. 제목과 내용을 넣고 저장

---

## 9-2. 만들 Issue 목록

아래 제목으로 하나씩 만듭니다.

```text
Preserve 2017 legacy baseline
Rewrite project documentation
Add modern Android Gradle baseline
Add GitHub Actions CI
Extract Article domain model
Add Article state reducer tests
Replace nested LiveData with StateFlow
Add modern ViewModel sample
Add Navigation migration guide
Add Hilt migration guide
Add Compose UI sample
Prepare Codex for OSS application notes
```

---

## 9-3. Issue 예시

제목:

```text
Extract Article domain model
```

내용:

```md
## Goal

Crat can be used without Android UI code.

## Tasks

- [ ] Create Article data class
- [ ] Create ArticleId value class
- [ ] Create sample Article list
- [ ] Add unit tests

## Done when

- Article state can be tested without running the Android app.
```

---

# 10. 7단계: 최신 Android 프로젝트를 새로 만들기

여기부터 코딩이 조금 들어갑니다.

초보자에게 가장 쉬운 방법은 **기존 오래된 Android 설정을 억지로 고치지 않고, Android Studio에서 새 프로젝트를 만든 뒤 필요한 파일을 가져오는 것**입니다.

---

## 10-1. 새 Android 프로젝트 만들기

Android Studio에서:

1. `File`
2. `New`
3. `New Project`
4. `Empty Activity` 또는 `Empty Compose Activity` 선택
5. 이름 입력

```text
AndroidViewModelMigrationLab
```

6. Language는 `Kotlin`
7. Minimum SDK는 너무 고민하지 말고 기본값 사용
8. Finish

---

## 10-2. 새 프로젝트가 잘 실행되는지 확인

Android Studio 위쪽에서 초록색 실행 버튼을 누릅니다.

에뮬레이터나 연결된 Android 기기에서 앱이 뜨면 성공입니다.

처음 화면이 단순히 `Hello Android` 같은 화면이어도 괜찮습니다.

---

## 10-3. 새 프로젝트 파일을 기존 repo에 옮기기

이제 새 프로젝트의 주요 파일을 기존 repo로 옮깁니다.

옮길 것:

```text
settings.gradle.kts
build.gradle.kts
gradle/
gradlew
gradlew.bat
app/
```

주의:

* 기존 repo 안의 오래된 `app/`은 바로 지우지 말고 백업합니다.
* 먼저 이름을 바꿉니다.

```text
app/
↓
legacy-app/
```

그리고 새 프로젝트의 `app/`을 복사해 넣습니다.

결과:

```text
android-viewmodel-migration-lab/
├─ legacy-app/
├─ app/
├─ docs/
├─ README.md
├─ LICENSE
├─ settings.gradle.kts
├─ build.gradle.kts
├─ gradlew
└─ gradlew.bat
```

---

## 10-4. Android Studio에서 다시 열기

1. Android Studio 종료
2. 다시 Android Studio 실행
3. repo 폴더 열기

```text
android-viewmodel-migration-lab
```

4. Sync가 끝날 때까지 기다리기

---

## 10-5. 성공 확인

Android Studio 아래 `Terminal`에서:

```bash
./gr:contentReference[oaicite:25]{index=25}ug
```

Windows라면:

````bash
gradlew.bat assembleDebug
``:contentReference[oaicite:26]{index=26}이 나옵니다.

```text
BUILD SUCCESSFUL
````

---

## 10-6. commit 하기

GitHub Desktop summary:

```text
Add modern Android project baseline
```

Push까지 합니다.

---

# 11. 8단계: GitHub Actions CI 만들기

CI는 GitHub가 자동으로 검사해주는 로봇입니다.

우리가 push하거나 PR을 만들면 자동으로 빌드합니다.

---

## 11-1. workflow 파일 만들기

폴더:

```text
.github/workflows/
```

파일:

```text
.github/workflows/android.yml
```

내용:

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

## 11-2. commit 하기

GitHub Desktop summary:

```text
Add Android CI workflow
```

Push합니다.

---

## 11-3. 성공 확인

GitHub에서:

1. repository 들어가기
2. `Actions`
3. `Android CI` 클릭
4. 초록색 체크가 뜨면 성공

빨간색이면 실패입니다. 실패해도 괜찮습니다. 에러 메시지를 보고 고치면 됩니다.

---

# 12. 9단계: Article 모델 만들기

이 프로젝트의 주인공은 `Article`입니다.

옛날 코드에서도 `ArticleListFragment`와 `ArticleDetailFragment`가 Article을 공유합니다. Detail 화면은 ViewModel에서 Article list를 가져와 특정 Article을 사용합니다. 

이제 이걸 더 깨끗하게 만듭니다.

---

## 12-1. 폴더 만text

app/src/main/java/...

````

아래에 패키지를 만듭니다.

추천 패키지 이름:

```text
com.example.viewmodelmigration
````

그 안에 폴더를 만듭니다.

```text
core/
```

최종:

```text
app/src/main/java/com/example/viewmodelmigration/core/
```

---

## 12-2. Article.kt 만들기

파일:

```text
Article.kt
```

내용:

```kotlin
package com.example.viewmodelmigration.core

data class Article(
    val id: String,
    val title: String,
    val body: String
)
```

뜻:

```text
Article은 id, title, body를 가진 글 하나입니다.
```

---

## 12-3. SampleArticles.kt 만들기

파일:

```text
SampleArticles.kt
```

내용:

```kotlin
package com.example.viewmodelmigration.core

object SampleArticles {
    fun create(): List<Article> {
        return listOf(
            Article(
                id = "1",
                title = "Legacy ViewModel",
                body = "This article explains dea."
            ),
            Article(
                id = "2",
                
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

## 12-4. commit 하기

GitHub Desktop summary:

```text
Add Article domain model
```

Push합니다.

---

# 13. 10단계: 화면 상태 만들기

화면에 필요한 데이터를 한 상자에 넣겠습니다.

---

## 13-1. ArticleListUiState.kt 만들기

파일:

```text
ArticleListUiState.kt
```

내용:

```kotlin
package com.example.viewmodelmigration.core

data class ArticleListUiState(
    val articles: List<Article> = emptyList(),
:contentReference[oaicite:31]{index=31}rticleId: String? = null
)
```

뜻:

```text
ArticleListUiState
= 화면이 지금 보여줘야 하는 상태
:contentReference[oaicite:32]{index=32}articles = 글 목록
selectedArticleId = 지금 선택한 글 id
```

---

## 13-2. ArticleListAction.kt 만들기

파일:

```text
ArticleListAction.kt
```

내용:

```kotlin
package com.example.viewmodelmigration.core

sealed interface ArticleListAction {
    data class SelectArticle(val articleId: String) : ArticleListAction
    data class UpdateArticle(val article: Article) : ArticleListAction
    data class DeleteArticle(val articleId: String) : ArticleListAction
}
```

뜻:

```text
Action
= 사용자가 한 행동
```

예:

```text
글 선택하기
글 수정하기
글 삭제하기
```

---

# 14. 11단계: 상태를 바꾸는 함수 만들기

이제 사용자가 행동했을 때 상태가 어떻게 바뀌는지 만듭니다.

---

## 14-1. ArticleListReducer.kt 만들기

파일:

```text
ArticleListReducer.kt
```

내용:

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

쉽게 말하면:

```text
SelectArticle → 선택한 글 id를 저장
UpdateArticle → 같은 id를 가진 글을 새 글로 교체
DeleteArticle → 해당 id의 글을 목록에서 제거
```

---

## 14-2. 왜 이게 중요한가

이 함수는 Android 화면 없이도 테스트할 수 있습니다.

즉, 앱을 실행하지 않아도:

```text
글 수정하면 목록도 바뀌는가?
```

를 확인할 수 있습니다.

이게 오픈소스 프로젝트에서 아주 좋은 점입니다.

---

# 15. 12단계: 테스트 만들기

테스트는 “내 코드가 맞는지 확인하는 자동 채점기”입니다.

---

## 15-1. 테스트 폴더 확인

아래 폴더가 있어야 합니다.

```text
app/src/test/java/
```

없으면 만듭니다.

그 안에 패키지를 만듭니다.

```text
com/example/viewmodelmigration/core/
```

최종:

```text
app/src/test/java/com/example/viewmodelmigration/core/
```

---

## 15-2. ArticleListReducerTest.kt 만들기

파일:

```text
ArticleListReducerTest.kt
```

내용:

```kotlin
package com.example.viewmodelmigration.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ArticleListReducerTest {

    @Test
    fun updateArticle_updatesArticleInList() {
        val oldArticle = Article(
            id = "1",
            title = "Old title",
            body = "Old body"
        )

        val newArticle = Article(
            id = "1",
            title = "New title",
            body = "New body"
        )

        val oldState = ArticleListUiState(
            articles = listOf(oldArticle)
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.UpdateArticle(newArticle)
        )

        assertEquals("New title", newState.articles.first().title)
        assertEquals("New body", newState.articles.first().body)
    }

    @Test
    fun selectArticle_updatesSelectedArticleId() {
        val oldState = ArticleListUiState(
            articles = SampleArticles.create()
        )

        val newState = ArticleListReducer.reduce(,
            action = ArticleListAction.SelectArticle("2")
        )

        assertEquals("2", newState.selectedArticleId)
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

        assertEquals(2, newState.articles.size)
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

## 15-3. 테스트 실행하기

Android Studio 아래 Terminal에서:

```bash
./gradlew test
```

Windows:

```bash
gradlew.bat test
```

성공하면:

```text
BUILD SUCCESSFUL
```

---

## 15-4. commit 하기

GitHub Desktop summary:

```text
Add Article state reducer tests
```

Push합니다.

---

# 16. 13단계: ViewModel 만들기

이제 화면이 사용할 ViewModel을 만듭니다.

옛날 코드는 `ArticleListViewModel`이 `MutableLiveData<List<MutableLiveData<Article>>>`를 가지고 있었습니다. 
새 코드는 더 단순하게 `ArticleListUiState` 하나를 관리합니다.

---

## 16-1. ViewModel 파일 만들기

패키지:

```text
app/src/main/java/com/example/viewmodelmigration/viewmodel/
```

파일:

```text
ArticleListViewModel.kt
```

내용:

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
            ArticleListReducer.reduce(oldState, action)
        }
    }
}
```

---

## 16-2. 에러가 날 수 있는 부분

`MutableStateFlow`에서 에러가 나면 Gradle dependencies에 coroutine이 빠졌을 수 있습니다.

그때는 `app/build.gradle.kts`에 아래가 있는지 확인합니다.

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
```

버전은 프로젝트 상황에 따라 달라도 됩니다.

---

## 16-3. commit 하기

GitHub Desktop summary:

```text
Add StateFlow ArticleListViewModel
```

Push합니다.

---

# 17. 14단계: ViewModel 테스트 만들기

이제 ViewModel이 제대로 동작하는지 확인합니다.

---

## 17-1. ArticleListViewModelTest.kt 만들기

폴더:

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
import org.junit.Test

class ArticleListViewModelTest {

    @Test
    fun updateArticle_updatesUiState() {
        val viewModel = ArticleListViewModel()

        val newArticle = Article(
            id = "1",
            title = "Updated title",
            body = "Updated body"
        )

        viewModel.updateArticle(newArticle)

        val state = viewModel.uiState.value
        val updatedArticle = state.articles.first { it.id == "1" }

        assertEquals("Updated title", updatedArticle.title)
        assertEquals("Updated body", updatedArticle.body)
    }

    @Test
    fun selectArticle_updatesSelectedArticleId() {
        val viewModel = ArticleListViewModel()

        viewModel.selectArticle("2")

        assertEquals("2", viewModel.uiState.value.selectedArticleId)
    }

    @Test
    fun deleteArticle_removesArticle() {
        val viewModel = ArticleListViewModel()

        viewModel.deleteArticle("1")

        val state = viewModel.uiState.value

        assertEquals(false, state.articles.any { it.id == "1" })
    }
}
```

---

## 17-2. 테스트 실행

```bash
./gradlew test
```

성공하면 commit 합니다.

summary:

```text
Add ViewModel tests
```

---

# 18. 15단계: Compose 화면 만들기

이제 실제 화면을 만듭니다.

화면은 2개입니다.

```text
ArticleListScreen
ArticleDetailScreen
```

---

## 18-1. ui 폴더 만들기

```text
app/src/main/java/com/example/viewmodelmigration/ui/
```

---

## 18-2. ArticleListScreen.kt 만들기

파일:

```text
ArticleListScreen.kt
```

내용:

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

## 18-3. ArticleDetailScreen.kt 만들기

파일:

```text
ArticleDetailScreen.kt
```

내용:

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
    val titleState = remember {
        mutableStateOf(article.title)
    }

    val bodyState = remember {
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

# 19. 16단계: MainActivity에 화면 연결하기

이제 앱을 실행했을 때 List와 Detail이 보이게 합니다.

---

## 19-1. MainActivity.kt 수정하기

`MainActivity.kt`를 열고 아래처럼 만듭니다.

패키지 이름은 프로젝트에 맞게 조정해야 합니다.

```kotlin
package com.example.viewmodelmigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val selectedArticleId = remember {
        mutableStateOf<String?>(null)
    }

    val selectedArticle = uiState.articles.firstOrNull {
        it.id == selectedArticleId.value
    }

    if (selectedArticle == null) {
        ArticleListScreen(
            uiState = uiState,
            onArticleClick = { articleId ->
                selectedArticleId.value = articleId
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
                selectedArticleId.value = null
            },
            onBackClick = {
                selectedArticleId.value = null
            }
        )
    }
}
```

---

## 19-2. 실행해서 확인하기

Android Studio에서 초록색 실행 버튼 클릭.

확인할 것:

1. Article 목록이 보이는가?
2. Article을 누르면 Detail 화면으로 가는가?
3. 제목을 수정하고 Save를 누르면 List로 돌아오는가?
4. List에서 수정된 제목이 보이는가?

이 4개가 되면 이 프로젝트의 핵심 기능이 살아난 겁니다.

---

## 19-3. commit 하기

GitHub Desktop summary:

```text
Add Compose article list and detail screens
```

Push합니다.

---

# 20. 17단계: 문서 작성하기

코드보다 더 중요한 게 문서입니다.

초보자가 봐도 이해할 수 있게 문서를 씁니다.

---

## 20-1. docs 폴더 구조

```text
docs/
├─ 01-legacy-architecture.md
├─ 02-modern-baseline.md
├─ 03-article-state-model.md
├─ 04-livedata-to-stateflow.md
├─ 05-compose-ui.md
└─ codex-for-oss.md
```

---

## 20-2. `02-modern-baseline.md`

내용 예시:

```md
# Modern Android Baseline

This document explains why the original project was rebuilt with a modern Android baseline.

## Original stack

- Java
- Support Library
- LiveData alpha
- DataBinding
- Dagger 2
- Manual Fragment transactions

## Modern stack

- Kotlin
- AndroidX
- StateFlow
- Compose
- ViewModel
- GitHub Actions

## Why rebuild?

The original sample is valuable as a historical example, but it depends on old Android tooling.

The modern sample keeps the same Article shared-state problem and rebuilds it with current Android architecture patterns.
```

---

## 20-3. `03-article-state-model.md`

````md
# Article State Model

The original project shared Article data between list and detail fragments.

In the modern version, Article state is represented by immutable Kotlin data classes.

## Article

```kotlin
data class Article(
    val id: String,
    val title: String,
    val body: String
)
````

## UI State

```kotlin
data class ArticleListUiState(
    val articles: List<Article> = emptyList(),
    val selectedArticleId: String? = null
)
```

## Why immutable state?

Immutable state is easier to test, easier to debug, and safer to share between screens.

````

---

## 20-4. `04-livedata-to-stateflow.md`

```md
# LiveData to StateFlow Migration

The original project used LiveData to share Article data between fragments.

The modern version uses StateFlow.

## Before

The legacy ViewModel exposed nested LiveData:

```java
MutableLiveData<List<MutableLiveData<Article>>>
````

## After

The modern ViewModel exposes one UI state stream:

```kotlin
StateFlow<ArticleListUiState>
```

## Benefit

The screen observes one state object instead of many nested mutable objects.

````

---

## 20-5. `codex-for-oss.md`

```md
# Codex for OSS Notes

This repository is being prepared as an open source migration lab for legacy Android maintainers.

## Why this project matters

Many Android apps still contain legacy patterns such as:

- Java
- Support Library
- early LiveData
- DataBinding
- Dagger 2
- manual Fragment transactions

This project provides a small, readable, tested migration path to modern Android architecture.

## How Codex can help

Codex can help with:

- generating migration PRs
- adding regression tests
- updating deprecated APIs
- reviewing architecture changes
- maintaining documentation
````

---

# 21. 18단계: PR 방식으로 작업하기

지금까지는 main에 바로 commit하는 방식으로 설명했습니다.

하지만 이제부터는 PR 방식으로 하는 게 좋습니다.

---

## 21-1. 작업 branch 만들기

GitHub Desktop에서:

1. branch 클릭
2. `New Branch`
3. 이름 입력

```text
docs/add-stateflow-migration-guide
```

4. 작업
5. commit
6. push
7. GitHub에서 Pull Request 만들기

---

## 21-2. branch 이름 규칙

| 작업 종류 | branch 이름 예시                    |
| ----- | ------------------------------- |
| 문서    | `docs/add-compose-guide`        |
| 기능    | `feature/add-article-viewmodel` |
| 테스트   | `test/add-reducer-tests`        |
| 설정    | `build/add-ci-workflow`         |
| 수정    | `fix/ci-build-error`            |

---

## 21-3. commit 메시지 규칙

좋은 예:

```text
Add Article reducer tests
```

```text
Document LiveData to StateFlow migration
```

```text
Add Compose detail screen
```

나쁜 예:

```text
update
```

```text
fix
```

```text
asdf
```

---

# 22. 19단계: Demo GIF 또는 스크린샷 만들기

GitHub README에는 그림이 있으면 좋습니다.

현재 README도 Demo 이미지를 가지고 있습니다. 
새 프로젝트도 Demo를 넣으면 훨씬 좋아집니다.

---

## 22-1. 찍어야 할 장면

1. 앱 실행
2. Article 목록 보기
3. 첫 번째 Article 클릭
4. 제목 수정
5. Save 클릭
6. 목록에서 제목이 바뀐 것 확인

---

## 22-2. 저장 위치

```text
docs/images/demo.gif
```

README에 추가:

```md
## Demo

![Demo](docs/images/demo.gif)
```

---

## 22-3. commit 하기

summary:

```text
Add demo GIF
```

---

# 23. 20단계: 첫 번째 release 만들기

어느 정도 준비되면 release를 만듭니다.

---

## 23-1. GitHub에서 release 만들기

1. GitHub repository 접속
2. 오른쪽 `Releases`
3. `Create a new release`
4. Tag 입력

```text
v0.1.0-modern-baseline
```

5. 제목 입력

```text
v0.1.0 - Modern Android Baseline
```

6. 설명 입력

```md
## What's included

- Rebranded project as Android ViewModel Migration Lab
- Preserved 2017 legacy baseline
- Added modern Android project baseline
- Added Article domain model
- Added StateFlow ViewModel
- Added reducer and ViewModel tests
- Added migration documentation
```

7. Publish

---

# 24. 21단계: Codex for OSS 제출 전 체크리스트

아래가 모두 체크되어야 제출할 만합니다.

```md
## Repository

- [ ] Repository is public
- [ ] Repository name is clear
- [ ] Repository description is clear
- [ ] README explains the project
- [ ] LICENSE exists
- [ ] CONTRIBUTING.md exists
- [ ] SECURITY.md exists

## Code

- [ ] Modern Android app builds
- [ ] Article model exists
- [ ] ViewModel exists
- [ ] StateFlow is used
- [ ] Compose screens exist
- [ ] Tests exist

## CI

- [ ] GitHub Actions runs on push
- [ ] GitHub Actions runs on PR
- [ ] Tests pass
- [ ] Debug build passes

## Documentation

- [ ] Legacy architecture guide exists
- [ ] Modern baseline guide exists
- [ ] LiveData to StateFlow guide exists
- [ ] Compose guide exists
- [ ] Codex for OSS notes exist

## Project activity

- [ ] Issues exist
- [ ] Pull requests exist
- [ ] At least one release exists
- [ ] Demo GIF or screenshots exist
```

---

# 25. 가장 추천하는 실제 작업 순서

아래 순서대로 하면 됩니다.

## 첫날: 저장소 정리

```text
1. GitHub Desktop 설치
2. repo clone
3. legacy/2017-original branch 만들기
4. v0.0.0-legacy-2017 tag 만들기
5. main branch 만들기
6. README 새로 쓰기
7. LICENSE 추가
8. CONTRIBUTING.md 추가
9. SECURITY.md 추가
10. Push
```

완료 목표:

```text
GitHub 첫 화면이 오픈소스 프로젝트처럼 보이기
```

---

## 둘째 날: 최신 Android 뼈대 만들기

```text
1. Android Studio에서 새 Kotlin Android 프로젝트 만들기
2. 새 프로젝트가 실행되는지 확인
3. 기존 repo에 새 app 구조 복사
4. legacy-app 폴더로 옛 app 보관
5. ./gradlew assembleDebug 실행
6. GitHub Actions CI 추가
7. Push
```

완료 목표:

```text
GitHub Actions에서 BUILD SUCCESSFUL 보기
```

---

## 셋째 날: Article 상태 만들기

```text
1. Article.kt 만들기
2. SampleArticles.kt 만들기
3. ArticleListUiState.kt 만들기
4. ArticleListAction.kt 만들기
5. ArticleListReducer.kt 만들기
6. Reducer 테스트 만들기
7. ./gradlew test 실행
8. Push
```

완료 목표:

```text
글 수정/삭제/선택 테스트 통과
```

---

## 넷째 날: ViewModel 만들기

```text
1. ArticleListViewModel.kt 만들기
2. StateFlow로 uiState 만들기
3. selectArticle 함수 만들기
4. updateArticle 함수 만들기
5. deleteArticle 함수 만들기
6. ViewModel 테스트 만들기
7. ./gradlew test 실행
8. Push
```

완료 목표:

```text
ViewModel 테스트 통과
```

---

## 다섯째 날: 화면 만들기

```text
1. ArticleListScreen.kt 만들기
2. ArticleDetailScreen.kt 만들기
3. MainActivity에 화면 연결
4. 앱 실행
5. 글 클릭
6. 제목 수정
7. Save
8. 목록에서 수정 확인
9. Push
```

완료 목표:

```text
앱에서 실제로 수정이 반영되는 것 확인
```

---

## 여섯째 날: 문서 만들기

```text
1. docs/02-modern-baseline.md 작성
2. docs/03-article-state-model.md 작성
3. docs/04-livedata-to-stateflow.md 작성
4. docs/05-compose-ui.md 작성
5. docs/codex-for-oss.md 작성
6. README에 문서 링크 추가
7. Push
```

완료 목표:

```text
코드를 모르는 사람도 README와 docs를 보고 이해 가능
```

---

## 일곱째 날: 제출 준비

```text
1. Demo GIF 만들기
2. README에 Demo 추가
3. GitHub Issues 정리
4. v0.1.0 release 만들기
5. Codex for OSS 신청 문구 준비
```

완료 목표:

```text
Codex for OSS 폼에 넣을 수 있는 상태 만들기
```

---

# 26. Codex for OSS 제출 문구

## Repository 설명

```text
A practical migration lab for modernizing legacy Android ViewModel and LiveData apps.
```

## Why does this repository qualify?

```text
This project helps maintainers modernize legacy Android apps built with Java, Support Library, early LiveData/ViewModel, DataBinding, Dagger 2, and manual Fragment navigation. It provides runnable before/after samples, tests, and step-by-step migration guides to AndroidX, Kotlin, StateFlow, Hilt, Navigation, and Compose.
```

## How will you use Codex?

```text
We will use Codex to generate migration PRs, add regression tests, review architecture changes, update deprecated Android APIs, maintain documentation, and validate before/after examples for legacy Android modernization workflows.
```

---

# 27. 실수했을 때 대처법

## 빌드 실패

터미널에 빨간 글씨가 나오면 당황하지 말고 아래를 봅니다.

| 에러 느낌                  | 뜻                     | 대처                        |
| ---------------------- | --------------------- | ------------------------- |
| `Unresolved reference` | 이름을 못 찾음              | import 또는 파일 이름 확인        |
| `Build failed`         | 앱 조립 실패               | 에러 첫 번째 줄 확인              |
| `Package name` 관련 에러   | 폴더 위치와 package 이름 불일치 | package 경로 확인             |
| `Gradle sync failed`   | Gradle 설정 문제          | Android Studio Sync 다시 실행 |
| `Test failed`          | 테스트 결과가 예상과 다름        | expected / actual 비교      |

---

## GitHub Desktop에서 변경이 너무 많을 때

무서워하지 말고:

1. 바꾼 파일 목록 보기
2. 내가 만든 파일인지 확인
3. 이상한 파일이면 체크 해제
4. commit 메시지 정확히 쓰기

---

## main에 직접 큰 실수를 올렸을 때

바로 고치려고 하지 말고:

1. GitHub Desktop에서 History 보기
2. 마지막 정상 commit 확인
3. 문제가 된 파일만 되돌리기
4. 새 commit으로 수정

---

# 28. 최종 목표 모습

마지막에는 repository가 이런 모습이면 좋습니다.

```text
android-viewmodel-migration-lab/
├─ README.md
├─ LICENSE
├─ CONTRIBUTING.md
├─ SECURITY.md
├─ app/
│  └─ modern Android sample
├─ legacy-app/
│  └─ original 2017 sample
├─ docs/
│  ├─ 01-legacy-architecture.md
│  ├─ 02-modern-baseline.md
│  ├─ 03-article-state-model.md
│  ├─ 04-livedata-to-stateflow.md
│  ├─ 05-compose-ui.md
│  └─ codex-for-oss.md
├─ .github/
│  ├─ workflows/
│  │  └─ android.yml
│  └─ pull_request_template.md
└─ gradle/
```

GitHub 첫 화면에서는 이렇게 보여야 합니다.

```text
Android ViewModel Migration Lab

A practical migration lab for maintainers of legacy Android apps.

- Original 2017 Android AAC sample preserved
- Modern Android version added
- Article shared-state problem explained
- StateFlow ViewModel implemented
- Tests included
- Migration docs included
```

---

# 29. 제일 먼저 해야 하는 것만 다시 정리

지금 바로 시작한다면 이 순서입니다.

```text
1. GitHub Desktop으로 repo clone
2. legacy/2017-original branch 만들기
3. v0.0.0-legacy-2017 tag 만들기
4. main branch 만들기
5. README 새로 쓰기
6. LICENSE 추가
7. CONTRIBUTING.md 추가
8. GitHub Issues 만들기
9. Android Studio에서 새 Kotlin Android 프로젝트 만들기
10. 새 프로젝트를 기존 repo에 옮기기
```

이 10개가 끝나면 프로젝트 방향이 완전히 바뀝니다.

핵심은 이것입니다.

> **코드를 완벽히 이해하고 시작하는 게 아니라,
> 작은 단계로 만들고, 실행하고, 저장하면서 배우는 방식으로 진행하면 됩니다.**

[1]: https://developer.android.com/topic/libraries/architecture/viewmodel "ViewModel overview  |  App architecture  |  Android Developers"
[2]: https://openai.com/form/codex-for-oss "Codex for Open Source | OpenAI"
