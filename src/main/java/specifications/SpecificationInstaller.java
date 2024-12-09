package specifications;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationInstaller {

    public static void installSpecifications(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void clearSpecifications() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
