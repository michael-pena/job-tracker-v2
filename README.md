# job-tracker-v2

## Description

An over-engineered backend CRUD REST API for keeping track of jobs I've applied to. Endpoints are protected with JWT.

## Technologies used

- Java 21
- Maven
- Swagger UI / OpenAPI
- Spring Boot
- Spring Security with JWT with RSA keys
- PostgreSQL
- Docker
- Spring Data JPA
- Mapstruct for DTO mapping

## Features

- User Registration
- Controller endpoint authentication with JWT
- Swagger UI with JWT authentication
- CRUD operations using JPA / PostgreSQL
- Pagination and Sorting
- Custom annotations for entity validation
- Global Exception Handling for Controllers

## Requirements

- Docker
- Java 21
- Maven

## Installation and Execution

1. Clone and cd into the repository:

    ```bash
    git clone https://github.com/michael-pena/job-tracker-v2.git
    cd job-tracker-v2/
    ```

2. Start the Spring Boot application:

    ```bash
    ./mvnw clean compile package -DskipTests
    docker-compose up --build
    ```
    Application reachable on `http://localhost:8080`

## Optional:

I've generated a public and private RSA keypair and placed them in the `src/main/resources/certs` directory named `private.pem` and `public.pem`. To generate your own keypair using openssl, run the following commands and replace them.

```bash
# create rsa key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

## Default Admin account for user scope managment

On startup, the commandline runner will populate the DB with example data from a CSV and create an admin user for adding scopes to registered user accounts. The default credentials, which can be changed in the `application.properties` file, are `admin:password1`

## Using the API

Each endpoint, with the exception of `/auth/token`, `/api/v1/user/register`, `/v3/api-docs/**`, and `/swagger-ui/**` are protected by Spring Security using JWT.

Depending on the endpoint and HTTP method, each protected endpoint will require the JWT to have at least one of of the following scopes in the JWT under the claim `scopes`:
- `admin`
- `read`
- `edit`

`GET` endpoints will require the `read` or `admin` scope.

`POST, PUT, and DELETE` endpoints will require the `edit` or `admin` scope.

New users can be created using the POST `/api/v1/user/register` endpoint.

A JWT token can be obtained using the POST `/auth/token` endpoint. For protected endpoints this will be used in each request as a Bearer Token.

New scopes / authorities can be mapped to registered users, by first getting a JWT with the default admin account at the POST `/auth/token` endpoint to obtain an admin token and then using the POST `/api/v1/user/authority` endpoint to add a new scope to a user.

### Creating + Updating Application Entries / Application POST + PUT requests

When creating new applications to track in the DB, the `status` property can't be null and must be one of 3 values:
- `no response`
- `rejected`
- `interview`

If the `status` property is set to `no response` or `rejected`, then the `offer` and `accepted` properties can be set to NULL or empty strings. You can even leave them out of the request.

If `status` property is set to `interview`, the `offer` property must be set to `yes` or `no`.

if `offer` property is set to `yes`, then `accepted` must be set to `yes` or `no`.

If the values are wrong, the request will be rejected with an HTTP 400 response along with a custom error message.


## REST API Endpoints

| Method | URL                                          | Authorization| Body (JSON)                               |
|--------|----------------------------------------------|--------------|-------------------------------------------|
| POST   | `/auth/token`                                | No           | `{ "username": "admin", "password": "password1"}`                    |
| POST   | `/api/v1/user/register`                      | No           | `{ "username": "mpena", "password": "123456" }`                      |
| POST   | `/api/v1/user/authority`                     | Yes          | `{ "username": "mpena", "authority": "read" }`                       |
| POST   | `/api/v1/application`                        | Yes          | `{ "company": "netflix","position": "software engineer", "date": "1-25-25", "status": "no response"}` |
| DELETE | `/api/v1/application/{appId}`                | Yes          |                                                                      | 
| PUT    | `/api/v1/application/{appId}`                | Yes          | `{ "company": "netflix","position": "software engineer", "date": "1-25-25", "status": "interview", "offer": "yes", "accepted": "yes"}` |
| GET    | `/api/v1/application/{appId}`                | Yes          |                                                                      |
| GET    | `/api/v1/application`                        | Yes          |                                                                      |
| GET    | `/api/v1/application?page=0&size=10&sortBy=id&SortDir=asc`| Yes           |                                                        |
