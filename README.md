# Play and Stay 🌏 🏠
숙박 예약 플랫폼
  - Host는 숙박 업소를 등록하고 판매할 수 있으며, 
  - Guest는 원하는 날짜에 숙박을 예약할 수 있습니다.



## 프로젝트 ERD
![playandstay-erd](https://user-images.githubusercontent.com/103729286/172188865-6c629ddb-fc50-490e-bb27-862d1de09e45.png)



## 프로젝트 세팅

- Implementation

```
Java 17
Spring Boot 2.6.6
Gradle
MyBatis
MySQL 
Docker
```

- Build & Run

```
gradle clean
gradle bootJar
java -jar build/libs/*.jar --ENV=${ENV} --MYSQL_HOST=${MYSQL_HOST} --MYSQL_DBNAME=${MYSQL_MYSQL_DBNAME} --MYSQL_USERNAME=${MYSQL_USERNAME} --MYSQL_PASSWORD=${MYSQL_PASSWORD} --MYSQL_PORT=${MYSQL_PORT}
```

- Database

```
docker run -e MYSQL_USER=${MYSQL_USER} -e MYSQL_PASSWORD=${MYSQL_PASSWORD} -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} -e MYSQL_DATABASE=${MYSQL_DBNAME} -p 3306:3306 mysql:latest
```

- Git Hooks

```
sudo cp .github/hooks/prepare-commit-msg .git/hooks/prepare-commit-msg
sudo chmod 755 .git/hooks/prepare-commit-msg
```

- Api Document

```
${SERVER_HOST}/swagger-ui/index.html
```

- Testing

```
./gradlew build -x test
SPRING_PROFILES_ACTIVE=[test] ./gradlew test
```
