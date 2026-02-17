# 안쪽까지 들여다보는 스프링 시큐리티 예제 코드

이 저장소는 **「안쪽까지 들여다보는 스프링 시큐리티」** 책의 부/장 구성에 맞춘 예제 코드를 제공합니다.

## 1부 스프링 시큐리티 소개

- 2장 Hello Spring Security
  - [demo](part-01/chapter-02/demo)

## 2부 인증

- 3장 스프링 시큐리티 인증 아키텍처
  - [demo](part-02/chapter-03/demo)
- 4장 폼 로그인 인증
  - [demo](part-02/chapter-04/demo)
- 5장 폼 로그인 인증 구현하기
  - [demo](part-02/chapter-05/demo)
- 6장 HTTP 기본 인증
  - [demo](part-02/chapter-06/demo)
- 7장 HTTP 기본 인증 구현하기
  - [demo](part-02/chapter-07/demo)
- 8장 리멤버-미 인증
  - [demo](part-02/chapter-08/demo)
- 9장 리멤버-미 인증 구현하기
  - [demo](part-02/chapter-09/demo)
- 10장 익명 인증
  - [demo](part-02/chapter-10/demo)
- 11장 인증 이벤트
  - [demo](part-02/chapter-11/demo)
- 12장 인증 이벤트 구현하기
  - [demo](part-02/chapter-12/demo)

## 3부 인가

- 13장 스프링 시큐리티 인가 아키텍처
  - [demo](part-03/chapter-13/demo)
  - [legacy demo](part-03/chapter-13-legacy/demo)
- 14장 HTTP 요청 기반 인가
  - [demo](part-03/chapter-14/demo)
  - [authorization-manager demo](part-03/chapter-14-authorization-manager/demo)
- 15장 HTTP 요청 기반 인가 구현하기
  - [demo](part-03/chapter-15/demo)
- 16장 메서드 기반 인가
  - [demo](part-03/chapter-16/demo)
- 17장 메서드 기반 인가 구현하기
  - [demo](part-03/chapter-17/demo)
- 18장 인가 이벤트
  - [demo](part-03/chapter-18/demo)
- 19장 인가 이벤트 구현하기
  - [demo](part-03/chapter-19/demo)

## 4부 애플리케이션 보호

- 20장 사이트 간 요청 위조
  - [demo](part-04/chapter-20/demo)
  - [attacker demo](part-04/chapter-20/demo-attacker)
  - [secure demo](part-04/chapter-20/demo-secure)
- 21장 교차 출처 리소스 공유(CORS)
  - [demo](part-04/chapter-21/demo)

## 5부 Spring Security OAuth 2.0

- 23장 스프링 시큐리티 OAuth2 로그인
  - [demo](part-05/chapter-23/demo)
- 24장 SNS 로그인 구현하기
  - [demo](part-05/chapter-24/demo)
- 25장 스프링 시큐리티 OAuth2 클라이언트
  - [demo](part-05/chapter-25/demo)
- 26장 OAuth2 클라이언트 구현하기
  - [demo](part-05/chapter-26/demo)

## 6부 스프링 시큐리티 테스트

- 27장 스프링 시큐리티 테스트 지원
  - [demo](part-06/chapter-27/demo)
  - [mvc-demo](part-06/chapter-27/mvc-demo)
  - [oauth2-demo](part-06/chapter-27/oauth2-demo)
  - [test-double](part-06/chapter-27/test-double)

## 요구사항

- Java 17
- Spring Boot 3.3.5
- Spring Security 6.3.4

## 인텔리제이 IDE 프로젝트 import 방법

이 저장소는 챕터별로 **독립적인 Gradle 프로젝트**(settings.gradle 포함)로 구성되어 있습니다. 따라서 저장소 루트가 아니라, 실행하려는 챕터의 demo(또는 mvc-demo, oauth2-demo 등) 폴더를 프로젝트로 여는 것을 권장합니다.

1. IntelliJ IDEA 실행
2. File > Open... 선택
3. **챕터 프로젝트 폴더** 선택 (예: part-02/chapter-05/demo)
4. Open as Project 선택
5. Gradle import 팝업 창에서 **Use Gradle Wrapper**로 import 진행
6. JDK는 **Java 17**로 설정 (File > Project Structure > Project)
7. 우측 Gradle 탭에서 Tasks > application > bootRun 또는 Tasks > verification > test 실행