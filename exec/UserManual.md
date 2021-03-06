## ๐User Manual

#### Back-End

1. ๋ค์ ํ์์ผ๋ก ./backend/build/resources/application.yaml ๊ฒฝ๋ก๋ก application.yaml ํ์ผ ์์ฑ

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
          tokenSecret: < Token Secret ๋ฌธ์์ด >
          AccessTokenExpirationMsec: < Access Token ๊ธฐ๊ฐ (๋ฐ๋ฆฌ์ด๋จ์) >
          RefreshTokenExpirationMsec: < Refresh Token ๊ธฐ๊ฐ (๋ฐ๋ฆฌ์ด๋จ์)>
      
      file:
        path: < image ํ์ผ ๊ฒฝ๋ก >
   ```

   

2. ```bash
   cd ./backend
   gradlew clean build
   java -jar {์์ฑ๋jarํ์ผ์ด๋ฆ}
   ```

#### Front-End

1. ```bash
   cd ./frontend
   npm install
   npm run serve
   ```

#### Socket Server

1. server.js์์ ๋ค์์ ์์ ํด์ค๋ค.

   ```javascript
   var PORT = 9001;
   var isUseHTTPs = true; //-> false๋ก ๋ณ๊ฒฝ
   ```

2. config.js์์ ์ฌ์ฉํ  ํฌํธ ๋ฒํธ ๋ฐ ํค ํ์ผ ๊ฒฝ๋ก ๋ฑ์ ์ค์ ํด์ค๋ค.

   ("./keys/privkey1.pem"์ "./keys/fullchain1.pem"๋ fake-key์ ๊ฒฝ๋ก๋ก ๊ทธ๋๋ก ์ฌ์ฉํ์๋ ๋ฌด๋ฐฉํฉ๋๋ค)

   ```json
   {
     "socketURL": "/",
     "dirPath": "",
     "homePage": "/demos/index.html",
     "socketMessageEvent": "RTCMultiConnection-Message",
     "socketCustomEvent": "RTCMultiConnection-Custom-Message",
     "port": "8282", //----------------------> ์ฌ์ฉํ  ํฌํธ ๋ฒํธ
     "enableLogs": "false",
     "autoRebootServerOnFailure": "false",
     "isUseHTTPs": "false",
     "sslKey": "./keys/privkey1.pem",// -----> key ํ์ผ ๊ฒฝ๋ก
     "sslCert": "./keys/fullchain1.pem",// --> key ํ์ผ ๊ฒฝ๋ก
     "sslCabundle": "",
     "enableAdmin": "true",
     "adminUserName": "username",
     "adminPassword": "password"
   }
   ```

3. `npm install`์ ์ด์ฉํ์ฌ ํ์ํ ๋ผ์ด๋ธ๋ฌ๋ฆฌ๋ฅผ ์ค์นํด์ค๋ค.

4. `server.js`๊ฐ ์๋ ์์น๋ก ์ด๋ํ์ฌ `node server`์ ์ด์ฉํ์ฌ ์คํํ๋ค.