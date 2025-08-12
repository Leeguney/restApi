# 1. 기본 JDK 이미지 설정
FROM openjdk:17-jdk-slim AS build

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 프로젝트 소스 복사
COPY . .

# 변경 (테스트 제외)
RUN ./gradlew clean build --no-daemon -x test

# 5. 실행 환경을 위한 JDK 이미지 설정
FROM openjdk:17-jdk-slim

# 6. 위에서 빌드한 JAR 복사
COPY --from=build /app/build/libs/restApi.jar app.jar

# 7. 컨테이너에서 실행될 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]

# 8. 컨테이너 포트 설정
EXPOSE 8080
