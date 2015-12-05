# lab01-udemy-microservices-spring-boot
Lab01 udemy course Microservices with Spring Cloud

Result of the lab exercise. 
The project needs a running mySQL instance on port 3306 (localhost:3306) with root password set to root (root:root).
Either install mySQL locally or use docker to run an instance:

`docker run --name test-mysql -p 3306:3306  -e MYSQL_ROOT_PASSWORD=root -d mysql:latest`

_LAB-01_

Run the application for lab01 with:

`mvn spring-boot:run`

and call with curl or a browser the url http://localhost:8080/teams

_LAB-04_

Run three applications with

`java -jar -Dspring.profiles.active=lab-04-inst-01 target/lab01-udemy-microservices-spring-boot-0.0.1-SNAPSHOT.jar`

`java -jar -Dspring.profiles.active=lab-04-inst-02 target/lab01-udemy-microservices-spring-boot-0.0.1-SNAPSHOT.jar`

`java -jar -Dspring.profiles.active=lab-04-inst-03 target/lab01-udemy-microservices-spring-boot-0.0.1-SNAPSHOT.jar`

Select one of the three application from the Eureka server and call
`http://your-host-name:the-port/aggregate`

The result should be: `Content from lab-04-inst-02 - Content from lab-04-inst-03 - Content from lab-04-inst-01`. The order depends on the 
time the service registered at Eureka.