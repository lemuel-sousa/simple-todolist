FROM gradle:7.6-jdk17 as builder

WORKDIR /app

RUN mkdir /tmp/cache

COPY . ./

RUN gradle dependencies -g /tmp/cache

RUN gradle assemble -g /tmp/cache --no-daemon

# -----------------------------------------------------------------------------

FROM amazoncorretto:17-alpine3.17 as runner

WORKDIR /app

COPY --from=builder /app/build/libs/simple-todolist*.jar ./

EXPOSE 5100

ARG PVERSION=0.0.1-SNAPSHOT

ENV PVERSION=${PVERSION}

ENV SPRING_PROFILES_ACTIVE=development

CMD [ "sh", "-c", "java -jar simple-todolist-$PVERSION.jar"]
