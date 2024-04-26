package com.piachimov.productservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @LocalServerPort
    private int port;

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void createProductSuccess() {
        String requestBody = """
                {
                    "name": "Product 1",
                    "description": "Description 1",
                    "price": 100
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Product 1"))
                .body("description", Matchers.equalTo("Description 1"))
                .body("id", Matchers.notNullValue())
                .body("description", Matchers.equalTo("Description 1"))
                .body("price", Matchers.equalTo(100));
    }

}
