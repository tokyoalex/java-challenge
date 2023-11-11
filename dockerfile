FROM eclipse-temurin:8
VOLUME /tmp
COPY ApiDemoApplication.jar ApiDemoApplication.jar
ENTRYPOINT ["java","-jar","/ApiDemoApplication.jar"]