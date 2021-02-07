FROM openjdk:jre-alpine

COPY target/autotrustservice-thorntail.jar /opt/autotrustservice-thorntail.jar

EXPOSE 8080
# preferIPv4Stack is needed to keep thorntail happy
ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/autotrustservice-thorntail.jar"]
