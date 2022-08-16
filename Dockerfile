FROM openjdk:8-alpine

WORKDIR /app/speedhome/

ADD propertymanagement-1.0.0.jar /app/speedhome/propertymanagement-1.0.0.jar

ENTRYPOINT [ "java", "-jar", "propertymanagement-1.0.0.jar" ]