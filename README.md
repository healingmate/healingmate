## ![healingmate-logo](README.assets/healingmate-logo.png)HealingMate란?

##### 공통의 관심사를 나누거나 속 깊은 고민을 이야기 할 힐링메이트(Healing Mate)를 찾아주는 모바일 웹 서비스

## 🚢팀 소개

|   Name   | 이동규                              | 김순석                              | 김윤빈                              | 서범석                              | 이아영                              |
| :------: | ----------------------------------- | ----------------------------------- | ----------------------------------- | ----------------------------------- | ----------------------------------- |
| Profile  | ![이동규](README.assets/이동규.jpg) | ![김순석](README.assets/김순석.png) | ![김윤빈](README.assets/김윤빈.png) | ![서범석](README.assets/서범석.png) | ![이아영](README.assets/이아영.png) |
| Position | 팀장<br />프론트엔드                | 팀원 <br />프론트엔드               | 팀원 <br />프론트엔드               | 팀원 <br />백엔드                   | 팀원<br />백엔드<br />인프라        |

## 📖Index

- [Architecture](#archiecture)
- [Tech Stack](#tech-stack)
- [User Manual](#user-manual)
- [Feature](#feature)
- [Wireframe](#wireframe)

## 📌Architecture

## 📌Tech Stack

### Back-End

#### 기술스택

![Apache Maven](https://img.shields.io/badge/Apache_Maven-4.0.0-C71A36?Style=flat&logo=Apache-Maven&logoColor=C71A36)
![JAVA ](https://img.shields.io/badge/JAVA_JDK-1.8-007396?Style=flat&logo=Java&logoColor=007396)
![SpringBoot](https://img.shields.io/badge/SpringBoot-2.4.2-6DB33F?Style=flat&logo=Spring&logoColor=6DB33F)
![MariaDB](https://img.shields.io/badge/MariaDB(AWS_RDS)-10.4.13-61DAFB?Style=flat&logo=MariaDB&logoColor=61DAFB)

#### 사용된 라이브러리
![spring-security](https://img.shields.io/badge/Spring_security-2.4.2-6DB33F?Style=flat&logo=Spring&logoColor=85EA2D)
![spring-redis](https://img.shields.io/badge/Spring_redis-2.4.2-DC382D?Style=flat&logo=Redis&logoColor=DC382D)
![spring-jpa](https://img.shields.io/badge/Spring_jpa-2.4.2-6DB33F?Style=flat&logo=Spring&logoColor=85EA2D)
![spring-jdbc](https://img.shields.io/badge/Spring_jdbc-2.4.2-6DB33F?Style=flat&logo=Spring&logoColor=85EA2D)
![spring-validation](https://img.shields.io/badge/Spring_validation-2.4.2-6DB33F?Style=flat&logo=Spring&logoColor=85EA2D)
![Lombok](https://img.shields.io/badge/Lombok-1.18.16-BC4521?Style=flat)
![Swagger](https://img.shields.io/badge/Swagger-2.9.2-85EA2D?Style=flat&logo=Swagger&logoColor=85EA2D)

---

### Front-End

#### 기술스택

#### ![vue](https://img.shields.io/badge/Vue.js-2.6.11-4FC08D?Style=flat&logo=Vue-js&logoColor=4FC08D)


#### 사용된 라이브러리

![axios](https://img.shields.io/badge/Axios-0.21.1-61DAFB?Style=flat)
![quasar](https://img.shields.io/badge/Quasar-1.0.0-1976d2?Style=flat&logo=Quasar&logoColor=1976d2)
![jwt-decode](https://img.shields.io/badge/Jwt_Decode-3.1.2-000000?Style=flat&logo=JSON-Web-Tokens&logoColor=000000)
![three](https://img.shields.io/badge/Three.js-0.128.0-000000?Style=flat&logo=Three.js&logoColor=000000)
![three-full](https://img.shields.io/badge/Three_Full-28.0.2-000000?Style=flat&logo=&logoColor=)
![vue-croppa](https://img.shields.io/badge/Vue_Croppa-1.3.8-4FC08D?Style=flat&logo=Vue-js&logoColor=4FC08D)
![vue-owl-carousel2](https://img.shields.io/badge/Vue_Owl_Carousel2-2.0.7-4FC08D?Style=flat&logo=Vue-js&logoColor=4FC08D)
![vue-router](https://img.shields.io/badge/Vue_Router-3.5.1-4FC08D?Style=flat&logo=Vue-js&logoColor=4FC08D)
![vuex](https://img.shields.io/badge/Vuex-3.6.2-2FC08D?Style=flat&logo=Vue-js&logoColor=4FC08D)

---

### 인프라

#### 기술스택

![Docker](https://img.shields.io/badge/Docker-gray?Style=flat&logo=Docker&logoColor=2496ED)
![Nginx](https://img.shields.io/badge/Nginx-gray?Style=flat&logo=Nginx&logoColor=269539)
![Jenkins](https://img.shields.io/badge/Jenkins-gray?Style=flat&logo=Jenkins&logoColor=D24939)
![GitLab](https://img.shields.io/badge/GitLab-gray?Style=flat&logo=GitLab&logoColor=FCA121)
![Mattermost](https://img.shields.io/badge/Mattermost-gray?Style=flat&logo=Mattermost&logoColor=0072C6)



## 📌User Manual

#### Back-End

1. 다음 형식으로 application.yaml 작성

   ```yaml
      server:
        port: 8080
        servlet:
          context-path: /api
      
      spring:
        datasource:
          driverClassName: org.mariadb.jdbc.Driver
          url: < Database URL >
          username: < Database ID >
          password: < Database Password >
        jpa:
          hibernate:
            ddl-auto: update
        servlet:
          multipart:
            max-file-size: 20MB
            max-request-size: 20MB
        cache:
          type: redis
        redis:
          host: < Redis URL >
          port: < Redis Port >
          password: < Redis Password >
      
      springdoc:
        version: '1.0.0'
        api-docs:
          path: /api-docs
        default-consumes-media-type: application/json
        default-produces-media-type: application/json
        swagger-ui:
          operations-sorter: method
          tags-sorter: alpha
          path: /swagger-ui.html
          disable-swagger-default-url: true
          display-query-params-without-oauth2: true
      
      app:
        token:
          authorizationHeader: authorization
          bearerPrefix: Bearer
        auth:
          tokenSecret: < Token Secret 문자열 >
          AccessTokenExpirationMsec: < Access Token 기간 (밀리초단위) >
          RefreshTokenExpirationMsec: < Refresh Token 기간 (밀리초단위)>
      
      file:
        path: < image 파일 경로 >
   ```

   

2. ```bash
   cd ./backend
   gradlew clean build
   java -jar {생성된jar파일이름}
   ```

#### Front-End

1. ```bash
   cd ./frontend
   npm install
   npm run serve
   ```



## 📌Feature

### 주요기능

#### 회원관리

- TODO

#### 게시글 CRUD

- TODO

#### 메타버스

- TODO

#### 유저 매칭

- TODO



## 📌Wireframe

<iframe height='600' scrolling='no' title='Fancy Animated SVG Menu' src='https://www.figma.com/embed?embed_host=astra&url=
       https://www.figma.com/file/01wdPw8RgH4z8GYIRU4pDY/HealingMate?node-id=0%3A1' frameborder='no' allowtransparency='true' allowfullscreen='true' style='width: 100%;'>
</iframe>
