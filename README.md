# SplitMoney

채팅방에 돈 뿌리기 서버 구축

## 개발환경
- Spring boot
- Java8
- JPA(Hibernate)
- H2 Database (In-Memory)
- Gradle
- Spock

## api
스웨거 url (http://localhost:8080/swagger-ui.html)

- 뿌리기 요청
  [POST] /api/split

- 받기 요청
  [GET] /api/receive
 
- 조회 요청
  [GET] /api/search
  
## 문제 해결 전략

- 유저정보와 방정보 저장 -> 프로젝트 시작 시 insert
- 뿌리기 분배 -> 균등하도록 분배 (ex. 10000원 3명이면 3333원, 3333원, 33334원)
- 토큰 생성 -> 랜덤하게 영어 소대문자로 유니크 하도록 3글자 생성
- 연속적 DB 업데이트 -> transaction 적용
