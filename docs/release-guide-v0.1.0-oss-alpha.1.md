# Release Guide: v0.1.0-oss-alpha.1

이 문서는 Android ViewModel Migration Lab의 첫 공개 릴리스를 위한 체크리스트입니다.

## 1) Prerequisites

- PR #9(`Prepare v0.1.0 OSS alpha release notes`)가 main에 머지되어야 함
- `README.md`, `CHANGELOG.md`에 릴리스 항목이 반영되어 있어야 함
- CI(`./gradlew test`, `./gradlew assembleDebug`)가 통과해야 함

## 2) Release tag

```bash
git fetch --all --prune
git checkout main
git pull
git tag v0.1.0-oss-alpha.1
git push origin v0.1.0-oss-alpha.1
```

## 3) GitHub Release

### 제목

`v0.1.0-oss-alpha.1 - Modern Android Migration Lab Alpha`

### 본문

`docs/release-notes-v0.1.0-oss-alpha.1.md`를 복사해서 붙여넣기

### CLI 예시

```bash
gh release create v0.1.0-oss-alpha.1 \
  --title "v0.1.0-oss-alpha.1 - Modern Android Migration Lab Alpha" \
  --notes-file docs/release-notes-v0.1.0-oss-alpha.1.md
```

### GitHub Actions 자동 릴리스(권장)

`main`에서 `v0.1.0-oss-alpha.1` 태그를 push하면 `.github/workflows/release.yml`에서 `release` 잡이 자동으로 실행되어:

- `./gradlew test`와 `./gradlew assembleDebug` 재확인
- `docs/release-notes-v0.1.0-oss-alpha.1.md`를 사용한 GitHub Release 발행
- `app-debug.apk`를 릴리스 자산으로 업로드

자동 릴리스는 수동 릴리스 명령을 대체하지 않습니다. 수동과 자동 둘 다 허용됩니다.

## 4) 릴리스 제한 범위(1차 Alpha)

- DI(Hilt/Dagger) 미적용
- Navigation 라이브러리 미적용
- DB/클라우드 연동 미적용
- `legacy-app/`는 아카이브용으로 보존
