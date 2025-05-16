FROM mirror.gcr.io/openjdk:25-ea-21-slim

ADD . /usr/local/room

WORKDIR /usr/local/room

COPY target ./

ENV profile=dev
ENV authDomain=rbp-auth
ENV bookingDomain=rbp-booking

ENTRYPOINT java -jar -Dspring.profiles.active=$profile target/restful-booker-platform-room-*.jar -D
