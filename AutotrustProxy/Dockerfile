FROM openjdk:jre-alpine

COPY target/autotrustproxy-thorntail.jar /opt/autotrustproxy-thorntail.jar

EXPOSE 8080
# preferIPv4Stack is needed to keep thorntail happy
ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/autotrustproxy-thorntail.jar"]
