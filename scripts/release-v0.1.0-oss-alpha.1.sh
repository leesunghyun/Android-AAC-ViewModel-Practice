#!/usr/bin/env bash
set -euo pipefail

TAG="v0.1.0-oss-alpha.1"
RELEASE_NOTES="docs/release-notes-${TAG}.md"
RELEASE_NAME="${TAG} - Android ViewModel Migration Lab Alpha"

if ! command -v gh >/dev/null 2>&1; then
  echo "gh CLI is required: https://cli.github.com/"
  exit 1
fi

if ! command -v git >/dev/null 2>&1; then
  echo "git is required"
  exit 1
fi

if [ ! -f "$RELEASE_NOTES" ]; then
  echo "Missing release notes: $RELEASE_NOTES"
  exit 1
fi

git fetch --all --prune
git checkout main
git pull origin main

./gradlew test
./gradlew assembleDebug

if git rev-parse --verify "$TAG" >/dev/null 2>&1; then
  echo "Tag $TAG already exists locally."
  exit 0
fi

git tag "$TAG"
git push origin "$TAG"

gh release create "$TAG" \
  --title "$RELEASE_NAME" \
  --notes-file "$RELEASE_NOTES"
