FROM mirror.gcr.io/openjdk:25-ea-21-slim

ADD . /usr/local/auth

WORKDIR /usr/local/auth

ENV profile=dev

COPY target ./

ENTRYPOINT java -jar -Dspring.profiles.active=$profile -Dhoneycomb.beeline.write-key=${HONEYCOMB_API_KEY} restful-booker-platform-auth-*.jar

