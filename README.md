# ✅ 코드 컨벤션

- **클래스명**: PascalCase (파스칼 케이스)  
- **메서드/변수명**: camelCase (카멜 케이스)
- **패키지명**: 소문자로 작성
- **PR/이슈 작성 시**: PR, 이슈 템플릿 사용

---

# ✅ 커밋 메시지 컨벤션

| 타입 | 설명 |
|------|------|
| `Fix(#이슈번호)` | 🐞 버그 수정 |
| `Chore(#이슈번호)` | 🔧 빌드 작업, 설정 수정 등 |
| `Refactor(#이슈번호)` | ♻️ 코드 리팩토링 (기능 변경 없음) |
| `Docs(#이슈번호)` | 📝 문서 수정 |
| `Feat(#이슈번호)` | ✨ 새로운 기능 추가 |

---

# 🌿 브랜치 전략

- 브랜치는 **이슈 템플릿 번호**를 기준으로 생성
- 브랜치 이름은 **이슈 번호를 함께 표기**  
  예) `feat/#1`

---

# 📦 패키지 구조
```
com.savepay.backend
│
├── config        : 설정 관련 클래스
├── exception     : 예외 처리 관련 클래스
├── util          : 유틸성 클래스
│
└── domain
    ├── controller    : 컨트롤러 클래스
    ├── service       : 서비스 인터페이스 및 구현체
    ├── repository    : 레포지토리 인터페이스
    ├── entity        : 엔티티 클래스
    └── dto           : Request, Response DTO 클래스
```

---

# 📊 ERD

<img width="1197" height="630" alt="image" src="https://github.com/user-attachments/assets/858495d0-371d-4052-98cf-caae48ca2e49" />



                     



