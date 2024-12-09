package tests;

import dto.request.AuthRequest;
import io.restassured.response.Response;
import org.bson.Document;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.DatabaseHelper;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {


    private static String token;

    @Test
    @Order(1)
    public void loginTest() {
        logger.info("Начинаю тестирование авторизации...");

        File jsonSchema = new File("src/test/resources/json-schema/auth-response-schema.json");
        logger.debug("Используется схема JSON из файла: {}", jsonSchema.getAbsolutePath());

        AuthRequest authRequest = new AuthRequest();
        logger.debug("Создан объект AuthRequest: {}", authRequest);

        Response response = authService.loginAndGetResponse(authRequest, jsonSchema);
        logger.info("Получен ответ от запроса авторизации.");

        token = response.jsonPath().getString("token");
        String username = response.jsonPath().getString("user.username");

        logger.info("Извлечен токен: {}", token);
        logger.info("Извлечено имя пользователя: {}", username);

        assertThat(token)
                .as("Токен не должен быть null или пустым")
                .isNotNull()
                .isNotEmpty();
        logger.info("Проверка токена прошла успешно.");

        assertThat(username)
                .as("Имя пользователя должно совпадать с ожидаемым значением")
                .isEqualTo("valery_lyubakov");
        logger.info("Проверка имени пользователя прошла успешно.");

        Document userDocument = DatabaseHelper.getUserByUsername(username);
        logger.info("Получены данные пользователя из базы данных с именем пользователя: {}", username);

        assertThat(userDocument)
                .as("Пользователь должен существовать в базе данных")
                .isNotNull();
        logger.info("Пользователь существует в базе данных.");

        assertThat(userDocument.getString("username"))
                .as("Имя пользователя в базе данных должно совпадать с ожидаемым значением")
                .isEqualTo("valery_lyubakov");
        logger.info("Имя пользователя в базе данных совпадает с ожидаемым значением.");
    }
}


