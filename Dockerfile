# ===========================
# STAGE 1 — Build com Maven
# ===========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /build

# Clona o seu repositório da branch develop
RUN git clone --branch develop https://github.com/thiagopereiramattos-dev/simuladorinvestimento-api.git .

# Apenas para debug
RUN echo "=== LISTANDO ARQUIVOS CLONADOS ===" && ls -R /build

# Build do Quarkus (gera quarkus-app)
RUN mvn clean package -DskipTests

# Debug: ver se o fast-jar foi gerado
RUN echo "=== LISTANDO TARGET ===" && ls -R /build/target

# ===========================
# STAGE 2 — Runtime
# ===========================
FROM eclipse-temurin:21-jre

WORKDIR /opt/app

# CRIA a pasta do banco SQLite
RUN mkdir -p /opt/app/data

# Copia o app (fast-jar)
COPY --from=build /build/target/quarkus-app/ ./quarkus-app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "quarkus-app/quarkus-run.jar"]
