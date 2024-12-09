package services.auth;

import dto.request.UserRequest;
import dto.response.UserResponse;
import io.restassured.response.Response;
import specifications.RequestSpecificationFactory;
import specifications.ResponseSpecificationFactory;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UserService {

    public Response addUser(UserRequest userRequest, String token, File jsonSchema) {
        return given()
                .header("Authorization", token)
                .spec(RequestSpecificationFactory.getRequestSpecJson())
                .body(userRequest)
                .when()
                .post("/api/user-auth1")
                .then()
                .spec(ResponseSpecificationFactory.responseSpecOk200(jsonSchema))
                .extract()
                .response();
    }

    public UserResponse parseResponse(Response response) {
        return response.as(UserResponse.class);
    }
}

