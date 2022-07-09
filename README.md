# Spring Boot Caching

## Start a Docker container for MySQL
```shell
docker run -d -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=product-inventory --name mysqldb -p 3307:3306 mysql:8.0
```
To stop the docker container, run the command `docker stop mysqldb && docker container prune -f`

## Install Maven dependencies
```shell
mvn install
```

## Run the project
```shell
mvn spring-boot:run
```

## Endpoints
|
| Endpoint | Method | Parameters |
|          |        |            |

