# multistage docker

FROM openjdk:23-jdk-oracle AS builder

ARG COMPILE_DIR=/compiledir

WORKDIR ${COMPILE_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true

# Not needed after creating second stage
# ENTRYPOINT java -jar target/day18_msdocker-0.0.1-SNAPSHOT.jar

FROM openjdk:23-jdk-oracle

ARG WORK_DIR=/app

WORKDIR ${WORK_DIR}

# from builder [dir for file in builder] [new filename for this stage]
COPY --from=builder /compiledir/target/paf_b5_assessment-0.0.1-SNAPSHOT.jar pafassessment.jar

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

SHELL [ "/bin/sh", "-c" ]

ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar pafassessment.jar