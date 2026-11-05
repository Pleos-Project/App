# 작업 규칙

## 1. 이슈 먼저

**모든 기능·수정 작업은 GitHub 이슈를 먼저 만들고 시작합니다.** 작은 수정도 예외 없이 이슈가 있어야 브랜치를 만들 수 있습니다.

- 이슈 템플릿(기능 요청 / 버그 리포트)을 골라 작성합니다.
- 이미 관련 이슈가 있으면 새로 만들지 않고 그 이슈로 진행합니다.

## 2. 브랜치 전략

| 브랜치 | 용도 |
|---|---|
| `main` | 배포(릴리스) 브랜치. `develop`에서 릴리스할 때만 PR로 머지합니다. |
| `develop` | 개발 통합 브랜치(기본 브랜치). 모든 기능 브랜치는 여기로 머지합니다. |
| `feature/<이슈번호>-<작업명>` | 기능 개발. `develop`에서 분기합니다. 예: `feature/12-door-status` |
| `fix/<이슈번호>-<작업명>` | 버그 수정. `develop`에서 분기합니다. 예: `fix/15-crash-on-start` |

## 3. 작업 흐름

```bash
# 1) 이슈 생성 (번호 확인)
gh issue create

# 2) develop 최신화 후 브랜치 생성
git switch develop
git pull
git switch -c feature/<이슈번호>-<작업명>

# 3) 작업 후 커밋·푸시
git push -u origin feature/<이슈번호>-<작업명>

# 4) develop으로 PR — 본문에 Closes #<이슈번호>
gh pr create --base develop
```

- PR 본문에 `Closes #<이슈번호>`를 적어서 머지될 때 이슈가 자동으로 닫히게 합니다.
- `main`, `develop`에는 직접 푸시하지 않고 PR로만 머지합니다.
- 기능 PR의 base 브랜치는 항상 `develop`입니다.
- 머지된 기능 브랜치는 자동으로 삭제됩니다.
- 릴리스할 때는 `develop` → `main` PR을 올립니다.
