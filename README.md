# api-todo
TODO List service

## 이용 기술
- Java 17
- Spring Boot 3.3.0
- JPA
- Gradle
- H2

## 서버 실행하기
```bash
# 프로젝트 경로에서 
./gradlew bootRun
```
- H2 콘솔 확인하기
  1. 브라우저에서 `http://localhost:8080/h2-console` 에 접속하기
  2. Connection 정보 입력하기 - JDBC URL: `jdbc:h2:mem:testdb`

## API 목차
1. 회원
   1. 가입
   2. 로그인
   3. 탈퇴
2. 투두리스트
   1. 생성
   2. 조회
   3. 상태 변경

## API
- Success Response 예시
```json
{
  "status": "success",
  "message": "The request successfully has been processed.",
  "data": {
    "id": 1
  }
}
```
- Error Response 예시
```json
{
  "status": "error",
  "message": "Missing session attribute 'id' of type Long",
  "data": {
    "code": "B1001"
  }
}
```

### 1-1. 가입
- `POST /members`
- Request Body

| 이름       |타입| 설명      |필수|
|----------|---|---------|--|
| loginId  |string| 회원 아이디  |O|
| password |string| 회원 비밀번호 |O|
| nickname |string| 회원 별명   |O|

- 요청 예시
```json
{
   "loginId": "gildong",
   "password": "123456",
   "nickname": "길동"
}
```
```bash
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
   "loginId": "gildong",
   "password": "123456",
   "nickname": "길동"
}' \
 'http://localhost:8080/members'
```

### 1-2. 로그인
- `POST /sessions`
- Request Body

| 이름       |타입| 설명      |필수|
|----------|---|---------|--|
| loginId  |string| 회원 아이디  |O|
| password |string| 회원 비밀번호 |O|

- 요청 예시
```json
{
   "loginId": "gildong",
   "password": "123456"
}
```
```bash
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
   "loginId": "gildong",
   "password": "123456"
}' \
 'http://localhost:8080/sessions'
```

### 1-3. 탈퇴
- 로그인 필요
- `DELETE /members/{id}`
- 요청 예시
```bash
curl -i -X DELETE \
 'http://localhost:8080/members/1'
```

### 2-1. 투두 생성
- 로그인 필요
- `POST /tasks`
- Request Body

| 이름      |타입| 설명    |필수|
|---------|---|-------|--|
| content |string| 투두 내용 |O|

- 요청 예시
```json
{
   "content": "Read a book."
}
```
```bash
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
   "content": "Read a book."
}' \
 'http://localhost:8080/tasks'
```

### 2-2. 투두 조회
- 로그인 필요
- `GET /tasks`
- Query Parameter

| 이름   |타입| 설명                   |필수|
|------|---|----------------------|--|
| size |int| 페이지당 사이즈 (default 20) | X|
| page |int| 조회할 페이지 (default 0)  | X|
- 요청 예시 (회원의 가장 최근 투두 1건 조회)
```bash
curl -i -X GET \
 'http://localhost:8080/tasks?size=1&page=0'
```
- 응답 예시
```json
{
    "status": "success",
    "message": "The request successfully has been processed.",
    "data": {
        "tasks": [
            {
                "id": 1,
                "content": "Read a book.",
                "status": "TODO",
                "createdAt": "2024-06-12 23:33"
            }
        ],
        "totalElements": 1
    }
}
```

### 2-3. 투두 상태 변경
- 로그인 필요
- `PATCH /tasks/{id}`
- Request Body

| 이름       |타입| 설명      |필수|
|----------|---|---------|--|
| toStatus |string| 변경될 상태 [DONE, TODO, IN_PROGRESS, PENDING] |O|

- 요청 예시
```json
{
   "toStatus": "DONE"
}
```
```bash
curl -i -X PATCH \
   -H "Content-Type:application/json" \
   -d \
'{
  "toStatus": "DONE"
}' \
 'http://localhost:8080/tasks/1'
```


## 고려한 사항
회원
- 로그인은 세션 방식으로 구현했습니다. 다중 서버일 경우 세션 공유, 토큰 사용을 고려합니다.
- 애플리케이션단에서 회원 중복을 체크하지만 멀티 스레드를 고려하여 테이블의 loginId 컬럼에 unique 조건을 걸었습니다.
- 비밀번호는 해시 암호화를 합니다. salt(추가적인 암호키)를 추가하는 것이 좋지만 그냥 입력받은 비밀번호를 그대로 해싱하였습니다.
- 탈퇴 시 엔티티를 삭제하지 않고 플래그를 '비활성화'로 바꿉니다.

투두
- 가장 최근의 투두 1개 조회, 목록 조회 API는 API를 각각 만들지 않고 목록 조회 API의 parameter 조절을 통해 가져올 수 있도록 했습니다. (가장 최근 1건: ?size=1)
- pageable의 기본 size값은 20, max size값은 100으로 설정했습니다.
- 조회 시 회원, 날짜에 따라 조회하는 경우가 대부분일 것이므로 테이블에 'memberId, createdAt' 인덱스를 설정했습니다.
- 조회 시 엔티티를 그대로 클라이언트에게 노출하지 않습니다. lazy loading 시 에러가 발생하지 않도록 트랜잭션 내에서 dto로 변환합니다.
- 클라이언트와 주고 받는 Request, Response는 자주 바뀌거나, 클라이언트마다 별도로 두어야 할 수 있습니다. 외부와 내부의 경계를 구분 짓기 위해 web 레이어에 별도의 dto를 둡니다.

공통
- API 에러 응답값은 보안을 해치지 않는 선에서 원인을 파악할 수 있는 메시지를 포함합니다.
- API 에러 응답값은 글로벌 에러 핸들러에서 잡아 통일된 형식(ErrorResponse - 에러 코드, 메시지 포함)으로 노출합니다.
- 엔티티 객체 생성시에 최대한 속성값이 다 들어간 완전한 객체를 만듭니다. 

---

## 요구사항
- 유저는 서비스에 가입 할 수 있어야 한다.
- 서비스에 가입 한 유저를 회원 이라고 한다.
- 회원은 닉네임을 가진다.
- 회원은 서비스에 로그인 및 탈퇴 할 수 있어야 한다.
- 보안이 고려되어 있어야 한다.
- 할 일을 TODO라고 한다. 
- 회원은 TODO를 작성할 수 있어야 한다. 
- 회원은 회원이 작성한 TODO를 아래와 같이 조회할 수 있어야 한다. 
  - 가장 최근의 TODO 1개 
  - 목록 
- 회원은 회원이 작성한 TODO의 상태를 아래와 같이 변경할 수 있어야 한다. 
  - TODO (할 일), IN PROGRESS (진행 중), DONE (완료), PENDING (대기)
  - 진행 중 상태에서만 대기 상태로 변경될 수 있다. 
  - 대기 상태에서는 어떤 상태로든 변경할 수 있다.