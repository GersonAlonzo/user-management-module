# -----------------
# Etapa de Construcción (Maven)
# -----------------
    FROM maven:3.9-eclipse-temurin-17-alpine AS build
    WORKDIR /app

    COPY pom.xml .
    RUN mvn dependency:go-offline
    
    COPY src ./src
    RUN mvn clean package -DskipTests
    
    FROM eclipse-temurin:17-jre-alpine
    WORKDIR /app
    
    COPY --from=build /app/target/*.jar /app/app.jar
    
    EXPOSE 8080
    
    ENV SPRING_DATASOURCE_URL="jdbc:mysql://db:3306/sompopo?useSSL=false&allowPublicKeyRetrieval=true"
    ENV SPRING_DATASOURCE_USERNAME=root
    ENV SPRING_DATASOURCE_PASSWORD=root
    ENV SPRING_JPA_HIBERNATE_DDL_AUTO=validate
    
    CMD ["sh", "-c", "java -jar /app/app.jar"]
    