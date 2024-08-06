FROM openjdk:17-slim

RUN apt-get update && apt-get install -y netcat
COPY target/sender-0.0.1-SNAPSHOT.jar sender.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
CMD ["/wait-for-it.sh", "receiver1:8080,receiver2:8080,receiver3:8080,proxy:8080", "java", "-jar", "/sender.jar"]


