package services;

import dto.request.AuthRequest;
import io.restassured.response.Response;
import specifications.ResponseSpecificationFactory;
import specifications.RequestSpecificationFactory;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AuthService {

    public Response loginAndGetResponse(AuthRequest request, File jsonSchema) {
        return given()
                .spec(RequestSpecificationFactory.getRequestSpecJson())
                .body(request)
                .when()
                .post("/api/auth/login")
                .then()
                .spec(ResponseSpecificationFactory.responseSpecOk200(jsonSchema))
                .extract()
                .response();
    }
}

