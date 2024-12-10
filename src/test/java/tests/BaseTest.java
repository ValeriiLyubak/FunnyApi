package tests;

import dto.request.AuthRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AuthService;

import java.io.File;

public abstract class BaseTest {
    protected static AuthService authService;
    protected static String token;

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    public static void setup() {
        authService = new AuthService();
        if (token == null || token.isEmpty()) {
            token = getToken();
        }
    }

    public static String getToken() {
        if (token == null || token.isEmpty()) {
            AuthRequest authRequest = new AuthRequest();
            Response response = authService.loginAndGetResponse(authRequest, new File("src/test/resources/json-schema/auth-response-schema.json"));
            token = response.jsonPath().getString("token");
        }
        return token;
    }
}


