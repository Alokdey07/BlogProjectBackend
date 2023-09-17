
README.md
Spring Boot Blog Backend

This is a Spring Boot backend for a blog application. It is built using Java and provides a REST API for managing blog posts and comments.

Features:

Create, read, update, and delete blog posts
Create, read, update, and delete comments on blog posts
Authentication and authorization using JWT tokens
Pagination and sorting of blog posts and comments
Requirements:

Java 17+
Maven
Getting started:

Clone the repository:
git clone [https://github.com/your-username/spring-boot-blog-backend.git](https://github.com/Alokdey07/BlogProjectBackend/tree/main)
Build the project:
mvn clean install
Start the server:
mvn spring-boot:run
Usage:

The REST API for the blog backend is exposed at the following endpoint:

http://localhost:8080/api/blogs
The following table lists the available endpoints and their methods:

## API Endpoints

| Endpoint                        | Method | Description                                   |
| ------------------------------- | ------ | --------------------------------------------- |
| `/blogs`                        | GET    | Get all blog posts                           |
| `/blogs/{id}`                   | GET    | Get a single blog post by ID                 |
| `/blogs`                        | POST   | Create a new blog post                       |
| `/blogs/{id}`                   | PUT    | Update an existing blog post                 |
| `/blogs/{id}`                   | DELETE | Delete a blog post                           |
| `/blogs/{id}/comments`          | GET    | Get all comments on a blog post              |
| `/blogs/{id}/comments`          | POST   | Create a new comment on a blog post          |
| `/blogs/{id}/comments/{commentId}` | GET    | Get a single comment on a blog post by ID   |
| `/blogs/{id}/comments/{commentId}` | PUT    | Update an existing comment on a blog post   |
| `/blogs/{id}/comments/{commentId}` | DELETE | Delete a comment on a blog post             |

Authentication:

To authenticate with the blog backend, you need to send a JWT token in the Authorization header of your requests. You can generate a JWT token using the /auth/login endpoint.

Example:

POST /auth/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "username": "admin",
  "password": "password"
}
Response:

HTTP/1.1 200 OK
Content-Type: application/json

{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}

Once you have a JWT token, you can use it to authenticate with the blog backend by sending it in the Authorization header of your requests.

Example:

GET /blogs HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

Testing:

The blog backend has a suite of unit and integration tests that can be run using the following command:

mvn test
Deployment:

The blog backend can be deployed to any Java application server. To deploy the blog backend as a JAR file, you can use the following command:

mvn package
This will create a JAR file in the target directory. You can then deploy the JAR file to your application server.

Contributing:

If you would like to contribute to the blog backend, please feel free to fork the repository and create a pull request.
