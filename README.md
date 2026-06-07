# Android ViewModel Migration Lab

A beginner-friendly OSS migration lab that modernizes a 2017 Android AAC ViewModel shared-state sample with Kotlin, Compose, ViewModel, and StateFlow.

This repository is now in the **migration implementation phase** of the OSS rebuild.

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

Not yet implemented:

- OSS release preparation:
  - `v0.1.0-oss-alpha.1` 릴리스 메모
  - CHANGELOG 정리 및 공개 릴리스 노트
  - `v0.1.0-oss-alpha.1` 태그 생성
  - GitHub Release 게시

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

The future modern version will provide:
- Kotlin + Compose UI
- ViewModel
- StateFlow
- Reducer-style state updates

## Quick start

1. Clone the repository
2. Work in sequence with the migration PRs in this repository

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

## Contributing

Please read [`CONTRIBUTING.md`](CONTRIBUTING.md) and add small, focused changes.

## License

Apache-2.0. See [`LICENSE`](LICENSE).
