FROM openjdk:8-jdk-alpine AS TEMP_BUILD_IMAGE
MAINTAINER Oluwatobi Adenekan <oluwatobi.t.adenekan@gmail.com,adenekanio@dlabs.cloud>
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew gradle.properties $APP_HOME/
#COPY gradle $APP_HOME/gradle
#RUN ./gradlew build
COPY build/libs/developertest-0.0.1.jar $APP_HOME/build/libs/developertest-0.0.1.jar
#RUN ./gradlew build
FROM openjdk:8-jdk-alpine
ENV ARTIFACT_NAME=developertest-0.0.1.jar
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD ["java","-jar", "developertest-0.0.1.jar"]