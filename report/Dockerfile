FROM mirror.gcr.io/openjdk:25-ea-21-slim

ADD . /usr/local/report

WORKDIR /usr/local/report

COPY target ./

ENV roomDomain=rbp-room
ENV bookingDomain=rbp-booking
ENV profile=dev

ENTRYPOINT java -jar -Dspring.profiles.active=$profile target/restful-booker-platform-report-*.jar -D
