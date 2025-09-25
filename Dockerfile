FROM eclipse-temurin:21
COPY "bundle/uk.ac.york.ci.corvus/target/ci-corvus-0.0.1-SNAPSHOT.jar" app.jar 
ENTRYPOINT ["java","-jar","/app.jar"]