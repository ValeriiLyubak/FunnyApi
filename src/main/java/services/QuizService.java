package services;

import dto.request.QuizRequest;
import dto.request.UserRequest;
import dto.response.UserResponse;
import io.restassured.response.Response;
import specifications.RequestSpecificationFactory;
import specifications.ResponseSpecificationFactory;

import java.io.File;

import static io.restassured.RestAssured.given;

public class QuizService {

    public Response addQuizAndGetResponse(QuizRequest quizRequest, String token, File jsonSchema) {
        return given()
                .header("Authorization", token)
                .spec(RequestSpecificationFactory.getRequestSpecJson())
                .body(quizRequest)
                .when()
                .post("/api/quiz")
                .then()
                .spec(ResponseSpecificationFactory.responseSpecOk200(jsonSchema))
                .extract()
                .response();
    }

//    public UserResponse parseResponse(Response response) {
//        return response.as(UserResponse.class);
//    }
}
