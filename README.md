# Member Management API

## 프로젝트 개요

이 프로젝트는 사용자 관리 API를 구현하는 Spring Boot 애플리케이션입니다. 이 API는 기본적인 사용자 등록, 로그인, 업데이트, 삭제 기능을 제공합니다. 이 애플리케이션은 개발, 테스트 및 실행을 위해 H2 인메모리 데이터베이스를 사용합니다.

## 기술 스택
- **JAVA 17**
- **Spring Boot 3.1.0**
- **Spring Data JPA**
- **H2 Database**

## 빌드 및 실행

1. **프로젝트 빌드**: Gradle을 사용하여 프로젝트를 빌드합니다.
    ```bash
    ./gradlew build
    ```

## API 요청 예제

  - [ ] -H "enc: true" \ 이 없는 모든 요청은 평문으로 전송 됩니다.

### 로그인

```bash
curl -X POST http://localhost:8080/members/login \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "nickName": "testUser",
          "password": "testPassword"
        }'
```
회원 가입
```bash
curl -X POST http://localhost:8080/members/join \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "name": "Test User",
          "password": "testPassword",
          "nickName": "testUser"
        }'
```
회원 정보 업데이트
```bash
curl -X PUT http://localhost:8080/members/update \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "id": 1,
          "name": "Updated User",
          "password": "newPassword",
          "nickName": "updatedUser"
        }'
```
회원 탈퇴
```bash
curl -X DELETE http://localhost:8080/members/leave \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "id": 1
        }'
```
로그인 시 잘못된 사용자 정보
```bash
curl -X POST http://localhost:8080/members/login \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "nickName": "nonExistentUser",
          "password": "testPassword"
        }'
```
잘못된 비밀번호로 로그인
```bash
curl -X POST http://localhost:8080/members/login \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "nickName": "testUser",
          "password": "wrongPassword"
        }'
```
이미 존재하는 닉네임으로 회원 가입
```bash
curl -X POST http://localhost:8080/members/join \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "name": "Another User",
          "password": "testPassword",
          "nickName": "testUser"
        }'
```
존재하지 않는 ID로 정보 업데이트
```bash
curl -X PUT http://localhost:8080/members/update \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "id": 9999,
          "name": "Updated User",
          "password": "newPassword",
          "nickName": "updatedUser"
        }'
```
이미 존재하는 닉네임으로 정보 업데이트
```bash
curl -X PUT http://localhost:8080/members/update \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "id": 1,
          "name": "Updated User",
          "password": "newPassword",
          "nickName": "existingNickName"
        }'
```
존재하지 않는 ID로 회원 탈퇴
```bash
curl -X DELETE http://localhost:8080/members/leave \
    -H "Content-Type: application/json" \
    -H "enc: true" \
    -d '{
          "id": 9999
        }'
```
Echo 요청 해당 요청은 암호화된 String을 복호화 하여 응답합니다.
```bash
curl -X POST http://localhost:8080/members/echo \
    -H "Content-Type: application/json" \
    -d 'LnVpOmF0Gz+2cPYKBKPIvDNdd7ux0xebmb63Ov7X5DE0GDsc/wRe1IUCz2l6u6XBfZVvBkMBWWo1zyfT1MiDFWRbZFRaRti9qXrzX8sjBBM='
```

## POSTMAN_JSON
 아래 파일을 postman에 import 하시면 됩니다.
- postmanImport
- postmanImport2
