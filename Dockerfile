FROM maven:3.8-jdk-11 as builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/
RUN mvn package -DskipTests


FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY --from=builder /app/target/EcoPlaning-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "/app/EcoPlaning-0.0.1-SNAPSHOT.jar"]
