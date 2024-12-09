package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ResponseSpecificationFactory {

    public static ResponseSpecification responseSpecOk200(File jsonSchema) {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody(matchesJsonSchema(jsonSchema))
                .build();
    }
}

