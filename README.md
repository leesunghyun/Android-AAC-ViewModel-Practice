# Android ViewModel Migration Lab

A beginner-friendly OSS migration lab that modernizes a 2017 Android AAC ViewModel shared-state sample with Kotlin, Compose, ViewModel, and StateFlow.

## What this project teaches

- How to keep shared state synchronized across multiple screens
- How to migrate legacy Android Architecture Components patterns to modern Kotlin/Compose architecture
- How to structure tests for reducer and ViewModel logic

## Original problem

The original project shared an `Article` model between screens:
- List screen shows Articles
- Detail screen edits a selected Article
- Save should update the list state immediately

## Modern solution

This repository preserves the original idea but provides a modern baseline using:
- Kotlin + Compose UI
- ViewModel
- StateFlow
- Reducer-style state updates

## Quick start

1. Clone the repository
2. Build with Gradle

```bash
./gradlew test
./gradlew assembleDebug
```

## Project structure

- `README.md`: project purpose and usage
- `app/`: modern Kotlin/Compose sample app
- `docs/`: migration notes and guides
- `.github/`: issue/pr templates and CI config

## Migration docs

- [2017 Legacy Architecture](docs/01-legacy-architecture.md)

## Roadmap

- [x] Preserve legacy baseline as archive
- [x] Preserve legacy 2017 snapshot (`legacy/2017-original`, `v0.0.0-legacy-2017`)
- [ ] Add modern app baseline
- [ ] Add Article domain/model and reducer tests
- [ ] Add ViewModel tests
- [ ] Add Compose screens
- [ ] Add CI workflow

## Contributing

Please read [`CONTRIBUTING.md`](CONTRIBUTING.md) and add small, focused changes.

## License

Apache-2.0. See [`LICENSE`](LICENSE).
