# v0.1.0-oss-alpha.1 - Android ViewModel Migration Lab Alpha

This is the first OSS alpha release of Android ViewModel Migration Lab.

## Summary

- Rebuild plan completed for the 1st alpha milestone:
  - Modern Kotlin + Compose baseline
  - Article shared-state model and reducer
  - StateFlow-based ViewModel
  - Reducer and ViewModel unit tests
  - Android CI workflow (`./gradlew test`, `./gradlew assembleDebug`)
  - Beginner-focused migration documentation set

## Added

- Modern Kotlin + Compose app baseline
- Article shared-state sample
- ViewModel + StateFlow state management
- Reducer-based state updates
- Reducer tests
- ViewModel tests
- GitHub Actions CI
- Migration documentation:
  - `docs/02-modern-architecture.md`
  - `docs/03-shared-state-problem.md`
  - `docs/04-livedata-to-stateflow.md`
  - `docs/05-compose-ui.md`
  - `docs/codex-for-oss.md`

## Preserved

- Legacy 2017 Android AAC ViewModel sample preserved as archive in `legacy-app/`
- Migration history and baseline notes documented in `docs/01-legacy-architecture.md`

## Known limitations

- No DI framework in first alpha (ex: Hilt/Dagger)
- No Navigation library in first alpha
- Legacy archive is preserved for reference and not part of CI build

