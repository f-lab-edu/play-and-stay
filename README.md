# Play and Stay



## 프로젝트 세팅 
- Implementation
```
Java 17
Spring Boot 2.6.6
Gradle
MyBatis
MySQL 
```

- Build & Run 
```
gradle clean
gradle bootJar
java -jar build/libs/*.jar
```

- Database
```
docker run -e MYSQL_USER=${MYSQL_USER} -e MYSQL_PASSWORD=${MYSQL_PASSWORD} -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} -e MYSQL_DATABASE=${MYSQL_DBNAME} -p 3306:3306 mysql:latest
```

- Git hooks 
```
sudo cp .github/hooks/prepare-commit-msg .git/hooks/prepare-commit-msg
sudo chmod 755 .git/hooks/prepare-commit-msg
```


- Api document
```
${SERVER_HOST}/swagger-ui.index.html 
```