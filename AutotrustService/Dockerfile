FROM openjdk:8u282-jre

#RUN apk add --update \
#    curl \
#    openjdk8=8.275.01-r0 \
# && echo "securerandom.source=file:/dev/urandom" >> /usr/lib/jvm/default-jvm/jre/lib/security/java.security

COPY target/autotrustservice-thorntail.jar /opt/autotrustservice-thorntail.jar

EXPOSE 8080

# preferIPv4Stack is needed to keep thorntail happy
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dsecurerandom.source=file:/dev/./urandom", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/autotrustservice-thorntail.jar", "-Doracle.jdbc.fanEnabled=false"]
