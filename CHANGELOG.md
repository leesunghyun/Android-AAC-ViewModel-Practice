# Changelog

## v0.1.0-oss-alpha.1 - Modern Android Migration Lab Alpha

### Added

- Rewrote README for OSS migration lab direction and status
- Added OSS metadata files
- Added GitHub pull request and issue templates
- Preserved original README in docs as legacy architecture archive
- Added modern Kotlin + Compose app baseline
- Added Article shared-state model and reducer foundation
- Added ViewModel + StateFlow state management
- Added reducer tests
- Added ViewModel tests
- Added Compose article list/detail screens
- Added Android CI workflow
- Added migration documentation set for modern architecture and state flow
- Added release workflow for tag-triggered `v0.1.0-oss-alpha.1` publish
- Added release notes and published `v0.1.0-oss-alpha.1` GitHub release

### Preserved

- Original 2017 Android AAC ViewModel sample preserved in `legacy-app/`
- `v0.1.0-oss-alpha.1` tag and GitHub Release published

### Known limitations

- First alpha intentionally excludes DI frameworks (Hilt/Dagger), Navigation library, and database/cloud integrations.
- Legacy app is archived only and excluded from CI build scope.
