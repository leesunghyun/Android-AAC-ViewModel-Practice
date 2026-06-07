# 02. 현대 앱 아키텍처(1차 구현 기준)

이 문서는 `app/` 모듈에서 현재 구현된 현대화 아키텍처를 기준으로 작성한 안내서입니다.  
목표는 “한 번에 크게 바꾸지 않고, 상태 흐름이 명확한 구조를 누적”하는 것입니다.

## 1) 패턴의 핵심

현재 구현은 아래의 흐름을 사용합니다.

1. UI 이벤트가 `ViewModel` 메서드로 들어옴
2. `ViewModel`이 액션으로 변환
3. 액션이 `Reducer`로 전달되어 새 상태를 계산
4. `StateFlow`가 새 상태를 노출
5. Compose가 상태를 구독해 화면을 렌더링

요약하면, 상태 업데이트는 **Reducer로 중앙화**하고, UI는 상태만 렌더링하도록 분리합니다.

## 2) 패키지 구조(현재 기준)

```text
app/src/main/java/com/example/viewmodelmigration/
├─ MainActivity.kt
├─ core/
│  ├─ Article.kt
│  ├─ ArticleListAction.kt
│  ├─ ArticleListReducer.kt
│  ├─ ArticleListUiState.kt
│  └─ SampleArticles.kt
├─ ui/
│  ├─ ArticleListScreen.kt
│  └─ ArticleDetailScreen.kt
└─ viewmodel/
   └─ ArticleListViewModel.kt
```

- `core/` : 도메인 + 순수 상태/변환
- `viewmodel/` : 상태 오케스트레이션 + `StateFlow` 노출
- `ui/` : Compose 화면 (`ArticleListScreen`, `ArticleDetailScreen`)

## 3) 상태 객체

### `Article`

`data class`로 불변 객체를 사용합니다.

- `id: String`
- `title: String`
- `body: String`

### `ArticleListUiState`

- `articles: List<Article>`
- `selectedArticleId: String?`

`selectedArticleId`는 현재 어떤 항목이 상세 화면에 노출되는지 관리합니다.

## 4) 액션과 리듀서

### `ArticleListAction`

- `SelectArticle(articleId)`
- `UpdateArticle(article)`
- `DeleteArticle(articleId)`
- `ClearSelection`

### `ArticleListReducer`

`ArticleListReducer.reduce(state, action)`은 이전 상태와 액션을 받아서 새 상태를 반환합니다.

- `SelectArticle` → `selectedArticleId` 변경
- `UpdateArticle` → `articles` 목록에서 해당 `id` 항목 교체
- `DeleteArticle` → 목록에서 제거 + 삭제된 항목이 선택 중이면 선택값 초기화
- `ClearSelection` → 선택값만 `null`로 초기화

## 5) ViewModel + StateFlow

`ArticleListViewModel`은 다음 형태를 사용합니다.

- 내부 상태: `MutableStateFlow<ArticleListUiState>`
- 외부 노출: `StateFlow<ArticleListUiState>` (`asStateFlow`)
- 상태 전환: `dispatch(action)` 내부에서 `_uiState.update { ... }`
- 상태 전환 규칙: `ArticleListReducer.reduce`

이 방식은 비즈니스 규칙을 `ViewModel`에서 직접 구현하지 않고, Reducer에 위임해 테스트 단위를 간결하게 유지합니다.

## 6) 현재 단계 정리

이 문서 기준으로 현재 프로젝트는 아래 단계까지 달성했습니다.

- 핵심 상태/액션/리듀서 정의
- `StateFlow` 노출 ViewModel
- 두 개 Compose 화면의 상태 기반 전환
- 단위 테스트 추가(리듀서/뷰모델)

다음 단계 문서는 화면 흐름 및 공유 상태 문제 해결을 더 상세히 기록합니다.

