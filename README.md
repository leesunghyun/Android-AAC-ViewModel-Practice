# Android ViewModel Migration Lab

A beginner-friendly OSS migration lab that modernizes a 2017 Android AAC ViewModel shared-state sample with Kotlin, Compose, ViewModel, and StateFlow.

This repository is in the **OSS setup phase** of the migration.

## Current status

Completed in this phase:

- Project direction clarified as `Android ViewModel Migration Lab`
- Legacy README preserved as historical documentation
- OSS metadata files added (`LICENSE`, `CONTRIBUTING.md`, `SECURITY.md`, `CODE_OF_CONDUCT.md`, `CHANGELOG.md`)
- GitHub issue/PR templates added

Not yet implemented:

- ViewModel + Reducer/StateFlow integration
- Compose list/detail UI screens
- Unit tests and Android CI

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
- [Migration Task Plan (source of truth)](docs/oss-remake-task-plan.md)
- [Historical planning references](docs/planning/archive/oss-remake-plan-reference.md)

## Roadmap

- [x] Preserve legacy baseline as archive (`legacy-app/`)
- [x] Preserve legacy 2017 snapshot (`legacy/2017-original`, `v0.0.0-legacy-2017`)
- [x] Move original app code into `legacy-app/`
- [x] Add modern app baseline
- [x] Add Article domain/model and reducer
- [ ] Add ArticleListReducer tests
- [ ] Add ViewModel + StateFlow implementation
- [ ] Add Compose screens
- [ ] Add unit tests
- [ ] Add Android CI workflow

## Contributing

Please read [`CONTRIBUTING.md`](CONTRIBUTING.md) and add small, focused changes.

## License

Apache-2.0. See [`LICENSE`](LICENSE).
