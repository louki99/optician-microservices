FROM openjdk:17
MAINTAINER DIGIUP <contact@digiup.ma>
ENV REFRESHED_AT 2022-07-21

ADD application/lib/commons-module.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/commons-module.jar"]

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh wait-for-it.sh
RUN bash -c 'chmod +x wait-for-it.sh'