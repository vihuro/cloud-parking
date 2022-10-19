package one.digitalInnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalInnovation.parking.dto.ParkingCreateDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTestIT {

    @LocalServerPort
    private int randoPort;

    @BeforeEach
    public void serUpTest(){
        System.out.println(randoPort);
        RestAssured.port = randoPort;

    }

    @Test
    void whenFindAllCheckResult() {
        RestAssured.given()
                .get("/parking/findAll")
                .then()
                .statusCode(200)
                .extract().response().body().prettyPrint();
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        var dto = new ParkingCreateDto();
        dto.setColor("Amarelo");
        dto.setLicense("WRT-5555");
        dto.setModel("Brasilia");
        dto.setState("BA");


        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .post("/parking")
                .then()
                .statusCode(201)
                .body("license", Matchers.equalTo("WRT-5555"))
                .body("color", Matchers.equalTo("Amarelo"))
                .extract().response().body().prettyPrint();
    }
}