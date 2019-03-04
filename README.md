# SpringIsHereEnterprise
## This repo is going to be an Enterprise project using spring.

Need to install the following tools (don't forget to set maven and spring-boot paths):
```
apache-maven
spring-boot
eclipse
eclipse STS plugin (ver. 4.0)
```

To test the basic project do the following:
```
// Using eclipse with installed tools
Run the project as Spring Boot App
GOTO: localhost:8080
Fun :-) (HelloWorld will be shown)

// Using command line
cd SpringIsHereEnterprise
mvn clean install
cd target
java -jar WebShopSpringIsHere-0.0.1.jar
localhost:8080
Fun :-)
```

### What is currently working (funny but right now working the not working part):
* http://localhost:8080 lists two dummy project
* http://localhost:8080/nameofnotexistingpage : handled 404 error
* http://localhost:8080/product/-1 throws id not found exception using GeneralException
* http://localhost:8080/db a h2 database will be shown
