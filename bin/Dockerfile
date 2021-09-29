FROM adoptopenjdk/openjdk14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mq-project.jar
ENTRYPOINT ["java","-jar","/mq-project.jar"]