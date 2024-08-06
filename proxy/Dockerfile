FROM openjdk:17-slim
RUN apt-get update && apt-get install -y netcat
COPY target/receiver-0.0.1-SNAPSHOT.jar /receiver.jar
COPY wait-for-it2.sh /wait-for-it2.sh
RUN chmod +x /wait-for-it2.sh
ENTRYPOINT ["/wait-for-it2.sh", "heartbeat:8080", "--", "java", "-jar", "/receiver.jar"]
