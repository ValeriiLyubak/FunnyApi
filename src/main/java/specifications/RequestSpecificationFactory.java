package specifications;



import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationFactory {

    public static RequestSpecification getRequestSpecJson() {
        String baseUrl = ConfigManager.getProperty("base.url");
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();
    }
}


