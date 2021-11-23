# Getting Started

### Reference Documentation


| Methods	| Urls |	Actions |
|---        |---   |  ---  |
|POST	|/api/auth/signup	|signup new account
|POST	|/api/auth/signin	|login an account
|GET	| /api/test/all	|retrieve public content
|GET	|/api/test/user	|access User’s content
|GET	| /api/test/mod	|access Moderator’s content
|GET	| /api/test/admin	|access Admin’s content

### Technology
* Java 8
* Spring Boot 2.1.8 (with Spring Security, Spring Web, Spring Data JPA)
* jjwt 0.9.1
* PostgreSQL/MySQL


## Requirements
 * Java - 1.8.x 
 * Maven - 3.x.x
 * Mysql - 5.x.x



## for runnint
Change mysql username and password as per your installation
open **src/main/resources/application.properties

change Change mysql username and password as per your installation

open src/main/resources/application.properties

change spring.datasource.username and spring.datasource.password as per your mysql installation
   spring.datasource.username and spring.datasource.password as per your mysql installation

1. add roles
   INSERT INTO roles(name) VALUES('ROLE_USER');
   INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
   INSERT INTO roles(name) VALUES('ROLE_ADMIN');
   
## TODO: list
- [ ] Swagger 
 - [ ] description 
 - [ ] statuses
 - [ ] integration testing 
- [exception handling ]


docker build -t spring-boot-websocket-chat-demo .


INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');


local swagger links
```
http://localhost:18080/custom/swagger-ui.html
```