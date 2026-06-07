# 05. Compose UI 구성 가이드 (1차 구현)

이 문서는 현재 PR 범위에서 구현된 Compose 화면을 기준으로 작성했습니다.

현재 앱은 단일 Activity 구조에서 다음 두 화면으로만 동작합니다.

- `ArticleListScreen`: 목록 화면
- `ArticleDetailScreen`: 상세 편집 화면

## 1) 화면 구성 개요

### ArticleListScreen

입력값:

- `uiState: ArticleListUiState`
- `onArticleClick: (String) -> Unit`
- `onDeleteFirstClick: () -> Unit`

행동:

- 각 `Article` 항목 클릭 시 상세 진입 액션 호출
- 삭제 버튼(예제 기본동작)에서 첫 번째 항목 삭제

`ArticleRow`는 리스트의 각 아이템을 카드로 표시하고, 제목/본문을 출력한 뒤 클릭을 상위로 전파합니다.

### ArticleDetailScreen

입력값:

- `article: Article`
- `onSaveClick: (Article) -> Unit`
- `onBackClick: () -> Unit`

행동:

- 로컬 상태(`remember`)로 입력 필드(title/body) 관리
- Save 버튼에서 수정본을 `onSaveClick`으로 전달
- Back 버튼으로 목록 화면으로 복귀

## 2) 화면 전환 방식

현재는 별도 네비게이션 라이브러리를 쓰지 않고, 상태 기반 전환을 사용합니다.

- `selectedArticleId == null` 이면 리스트 화면
- `selectedArticleId != null` 이면 상세 화면

`MainActivity`의 `AppRoot`가 이를 판단해 화면을 분기합니다.

## 3) 상태 연동 포인트

- `collectAsStateWithLifecycle()`로 `uiState`를 lifecycle-aware하게 구독
- 상세에서 저장 시:
  1. `onSaveClick(updatedArticle)`
  2. `ViewModel`에서 reducer로 `UpdateArticle` 실행
  3. `selectedArticleId`를 `null`로 clear
  4. 다시 리스트 화면 표시
- Back 버튼도 동일하게 `selectedArticleId`를 clear

이 방식으로 상세 화면에서 “돌아가면 이전 데이터가 남아있는 버그”를 줄였습니다.

## 4) 단순 상태 기반 UI의 장점

- 화면 개수 확대 시에도 상태 전환 규칙이 일관됨
- 네비게이션 라이브러리 없이도 동작을 학습하기 쉬움
- 테스트에서 UI 단위별로 분리해 사고하기 쉬움

## 5) 주의할 점

- 이 단계는 교육/샘플 목적이므로 UI/UX는 최소 기능 위주
- 현재 삭제 동작은 “첫 번째 항목 삭제” 데모로 두었으므로, 실제 요구사항 반영 시에는 선택 삭제 액션으로 바꿔야 함
