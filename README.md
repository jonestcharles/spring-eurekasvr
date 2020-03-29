This project is a 3 microservice application consisting of a Spring REST API asset-service, a Spring Cloud Config Server confsvr, and a postgresql database.
The Asset Service in this project supports all basic CRUD operations for Asset objects using Spring Data JPA to persist to the database microservice. The database is initialize with the schema.sql file.
The database is a basic postgresql database image deployed with Docker.
The confsvr microservice sets up configuration settings allowing the microservices to communicate and configuring multiple user profiles for use with the application (dev, prod, and default). The configuration files for asset-service profiles are hosted in a GitHub repository, with credentials provided in the config application.yml file.
The application is deployed with Docker.
To run:
Build with "mvn clean package docker:build" from the root directory
Run with "docker-compose -f docker/common/docker-compose.yml up" from the root directory.
To shutdown:
Ctrl+c from terminal
