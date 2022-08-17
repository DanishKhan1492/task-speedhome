FROM adoptopenjdk:11.0.11_9-jre-openj9-0.26.0

WORKDIR /app/speedhome/

ADD ./target/propertymanagement-1.0.0.jar /app/speedhome/propertymanagement-1.0.0.jar

ENTRYPOINT [ "java", "-jar", "propertymanagement-1.0.0.jar" ]