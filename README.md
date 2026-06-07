# Android ViewModel Migration Lab

A beginner-friendly OSS migration lab that modernizes a 2017 Android AAC ViewModel shared-state sample with Kotlin, Compose, ViewModel, and StateFlow.

This repository is now in the **release readiness phase** of the OSS rebuild.

## Current status

Completed in this phase:

- Project direction clarified as `Android ViewModel Migration Lab`
- Legacy README preserved as historical documentation
- OSS metadata files added (`LICENSE`, `CONTRIBUTING.md`, `SECURITY.md`, `CODE_OF_CONDUCT.md`, `CHANGELOG.md`)
- GitHub issue/PR templates added
- Modern Kotlin/Compose baseline created
- Article domain model, reducer, ViewModel, and tests added
- Compose list/detail screens implemented
- Android CI workflow added
- Release workflow added for `v0.1.0-oss-alpha.1` tag push
- `v0.1.0-oss-alpha.1` first OSS alpha release is published
  - GitHub Release: `v0.1.0-oss-alpha.1`
  - Sample debug APK: `app-debug.apk`

## What this project will teach

- How to keep shared state synchronized across multiple screens
- How to migrate legacy Android Architecture Components patterns to modern Kotlin/Compose architecture
- How to structure reducer and ViewModel tests

## Original problem

The original project shared an `Article` model between screens:
- List screen shows Articles
- Detail screen edits a selected Article
- Save should update the list state immediately

## Planned modern solution

The modern version provides:
- Kotlin + Compose UI
- ViewModel
- StateFlow
- Reducer-style state updates

## Quick start

1. Clone the repository
   ```bash
   git clone https://github.com/leesunghyun/Android-AAC-ViewModel-Practice.git
   cd Android-AAC-ViewModel-Practice
   ```
2. Build tests (first-time setup complete on CI)
   ```bash
   ./gradlew test
   ```
3. Build and install the debug app
   ```bash
   ./gradlew assembleDebug
   ./gradlew installDebug
   ```
4. Open the project in Android Studio and run `app`.

## Demo preview

The first alpha flow is: `List → Detail/Edit → Save → List` with shared state update.

![v0.1.0 OSS alpha demo placeholder](docs/assets/v0.1.0-oss-alpha-demo-placeholder.svg)

## Release status

- First OSS alpha release: [v0.1.0-oss-alpha.1](https://github.com/leesunghyun/Android-AAC-ViewModel-Practice/releases/tag/v0.1.0-oss-alpha.1)
- APK is provided as a sample debug artifact for manual smoke testing (not production release).

For full release details and run verification commands, see:
- [Release Notes](docs/release-notes-v0.1.0-oss-alpha.1.md)
- [Release Guide](docs/release-guide-v0.1.0-oss-alpha.1.md)

## Project structure

- `README.md`: project purpose and progress
- `docs/`: migration notes and legacy baseline records
- `legacy-app/`: archived legacy baseline code
- `.github/`: pull request and issue templates

## Migration docs

- [2017 Legacy Architecture](docs/01-legacy-architecture.md)
- [Modern Architecture](docs/02-modern-architecture.md)
- [Shared State Problem](docs/03-shared-state-problem.md)
- [LiveData to StateFlow](docs/04-livedata-to-stateflow.md)
- [Compose UI Guide](docs/05-compose-ui.md)
- [Release Notes: v0.1.0-oss-alpha.1](docs/release-notes-v0.1.0-oss-alpha.1.md)
- [Release Guide: v0.1.0-oss-alpha.1](docs/release-guide-v0.1.0-oss-alpha.1.md)
- [Migration Task Plan (source of truth)](docs/oss-remake-task-plan.md)
- [Codex for OSS](docs/codex-for-oss.md)
- [Historical planning references](docs/planning/archive/oss-remake-plan-reference.md)

## Roadmap

- [x] Preserve legacy baseline as archive (`legacy-app/`)
- [x] Preserve legacy 2017 snapshot (`legacy/2017-original`, `v0.0.0-legacy-2017`)
- [x] Move original app code into `legacy-app/`
- [x] Add modern app baseline
- [x] Add Article domain/model and reducer
- [x] Add ArticleListReducer tests
- [x] Add ViewModel + StateFlow implementation
- [x] Add Compose screens
- [x] Add unit tests
- [x] Add Android CI workflow
- [x] Publish migration document bundle
- [x] Add release notes and changelog section for v0.1.0-oss-alpha.1

## Next milestones (draft)

- [ ] Add UI tests for article edit/delete interactions
- [ ] Add `collectAsStateWithLifecycle` in Compose state collection
- [ ] Replace placeholder demo image with real screen recording
- [ ] Improve Article detail validation UX
- [ ] Prepare v0.2.0 roadmap and release notes

## Contributing

Please read [`CONTRIBUTING.md`](CONTRIBUTING.md) and add small, focused changes.

## License

Apache-2.0. See [`LICENSE`](LICENSE).
