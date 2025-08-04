# SavePay 도메인 모델

## User(사용자)
_entity_
### Field
- id(PK): 유저 인조 식별자
- email(unique): 유저 이메일
- username: 이름
- socialType: 가입한 소셜 타입 (KAKAO, NAVER)

### Method
- 회원가입
  - OAuth2를 통한 소셜 회원가입 (User Entity 생성)
  - 토큰 or 세션이 없고 DB에 user에 관한 정보가 없을 때 작동
  - Naver or Kakao

## Telecom(통신사)
_entity_
### Field
- id(PK): 통신사 식별자
- name: 통신사 이름(SKT, KT, LG, 알뜰폰)

## UserTelecom
_entity_
유저랑 telecom 연결 다대다
### Field
- id(PK): 유저 텔레콤 식별자
- user: 사용자
- telecom: 통신사
- isMemberShip: 멤버쉽 여부
- telecomGrade(enum): 통신사 등급

