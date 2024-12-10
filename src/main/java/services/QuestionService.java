package services;

import dto.request.QuestionRequest;
import io.restassured.response.Response;
import specifications.RequestSpecificationFactory;
import specifications.ResponseSpecificationFactory;

import java.io.File;
import static io.restassured.RestAssured.given;


public class QuestionService {
    public Response addQuestionandGetResponse(String token, QuestionRequest request, File jsonSchema) {
        return given()
                .spec(RequestSpecificationFactory.getRequestSpecJson())
                .header("Authorization", token)
                .body(request)
                .when()
                .post("/api/theme-question")
                .then()
                .spec(ResponseSpecificationFactory.responseSpecOk200(jsonSchema))
                .extract()
                .response();
    }
}
