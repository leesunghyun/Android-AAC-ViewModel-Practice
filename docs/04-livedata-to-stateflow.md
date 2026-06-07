# 04. LiveData에서 StateFlow로 이동

이 프로젝트는 구형 구현보다 현대 구현을 목표로 하므로, 상태 전달은 `StateFlow`를 기본으로 정리합니다.
`LiveData`를 그대로 따라가지 않고도 왜 `StateFlow`가 적합한지, 어떻게 전환했는지 기록합니다.

## 왜 StateFlow를 택했는가

요구사항은 다음입니다.

- 최신 상태를 즉시 UI에 반영
- 상태 변경이 예측 가능해야 함
- 동시 접근 가능한 단일 상태 트리
- 테스트가 쉬워야 함

`StateFlow`는 이 점에서 유리합니다.

- 항상 현재 값을 보유
- 불변 상태 객체를 기반으로 변경 전파
- `Flow` 계열 연산을 이용한 확장성
- 테스트에서 상태 assert가 직관적

## 최소 전환 규칙(현재 구현)

### 1. 도메인 상태를 불변 객체로 정의

`Article`, `ArticleListUiState`를 `data class`로 정의해 상태 전환의 입력/출력을 명확히 합니다.

### 2. 상태 변경 진입점을 Reducer로 고정

`ArticleListReducer.reduce(state, action)`에서만 실제 목록/선택 변화를 계산합니다.

### 3. ViewModel에서 StateFlow로 노출

`MutableStateFlow`로 내부 상태를 관리하고, 외부에는 `StateFlow`만 노출합니다.

현재 핵심 코드 패턴:

```kotlin
private val _uiState = MutableStateFlow(ArticleListUiState(...))
val uiState: StateFlow<ArticleListUiState> = _uiState.asStateFlow()
```

### 4. 변경은 항상 `update` 기반으로 수행

`update` 블록 안에서 reducer를 호출해 새 상태를 반환합니다.

```kotlin
private fun dispatch(action: ArticleListAction) {
    _uiState.update { oldState ->
        ArticleListReducer.reduce(oldState, action)
    }
}
```

### 5. Compose에서 수집

`collectAsState()`로 `uiState`를 관찰해 화면이 스스로 최신 상태를 반영합니다.

```kotlin
val uiState by viewModel.uiState.collectAsState()
```

## LiveData 대비 핵심 포인트

- `LiveData`를 제거한 것은 기술 트렌드만의 이유보다, 상태 전환을 테스트 가능한 순수 함수로 분리하기 쉬운 구조를 얻기 위함입니다.
- 구형 예제의 핵심 문제(화면 간 공유 상태 동기화)는 `StateFlow` 하나의 상태 소스와 reducer로 더 선명하게 제어됩니다.

## 전환 단계 체크리스트

1. 상태를 `ArticleListUiState` 한 곳에서 관리한다.
2. reducer가 상태 전환을 순수 함수로 담당한다.
3. ViewModel은 액션을 받아 dispatcher 역할만 한다.
4. UI는 상태만 구독한다.
5. 변경/삭제/선택 동작 모두 테스트한다.

본 문서는 향후에 `StateFlow` 사용 시 기준 템플릿으로 재사용하세요.

