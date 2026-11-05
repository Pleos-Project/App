# 브랜치 전략

| 브랜치 | 용도 |
|---|---|
| `main` | 배포(릴리스) 브랜치. `develop`에서 릴리스할 때만 PR로 머지합니다. |
| `develop` | 개발 통합 브랜치(기본 브랜치). 모든 기능 브랜치는 여기로 머지합니다. |
| `feature/<작업명>` | 기능 개발. `develop`에서 분기합니다. |
| `fix/<작업명>` | 버그 수정. `develop`에서 분기합니다. |

## 작업 흐름

```bash
git switch develop
git pull
git switch -c feature/<작업명>
# 작업 후 커밋
git push -u origin feature/<작업명>
gh pr create --base develop
```

- `main`, `develop`에는 직접 푸시하지 않고 PR로만 머지합니다.
- 기능 PR의 base 브랜치는 항상 `develop`입니다.
- 릴리스할 때는 `develop` → `main` PR을 올립니다.
