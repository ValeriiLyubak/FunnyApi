package tests;

import dto.request.UserRequest;
import dto.response.UserResponse;
import io.restassured.response.Response;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import services.auth.UserService;
import utils.DatabaseHelper;
import utils.TestDataGenerator;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAddTest extends BaseTest {

    private final UserService userService = new UserService();
    @Test
    public void userAddTest() {
        UserRequest userRequest = TestDataGenerator.generateTestUsers().get(0);
        logger.info("Создан запрос для добавления пользователя: {}", userRequest);

        File jsonSchema = new File("src/test/resources/json-schema/user-response-schema.json");
        logger.debug("Используется схема JSON для ответа из файла: {}", jsonSchema.getAbsolutePath());

        String currentToken = getToken();
        logger.info("Получен токен авторизации: {}", currentToken);

        Response response = userService.addUser(userRequest, currentToken, jsonSchema);
        logger.info("Получен ответ на запрос добавления пользователя.");

        logger.debug("Ответ от сервера: {}", response.asPrettyString());

        // Преобразуем ответ в объект UserResponse
        UserResponse userResponse = userService.parseResponse(response);
        logger.info("Ответ успешно разобран в объект UserResponse.");

        Document foundUser = DatabaseHelper.getUserFromDatabase(userResponse);
        logger.info("Поиск пользователя с именем {} в базе данных.", userResponse.getData().getUsername());

        assertThat(foundUser)
                .as("Пользователь должен быть найден в базе данных")
                .isNotNull();
        logger.info("Пользователь успешно найден в базе данных.");

        assertThat(foundUser.getString("username"))
                .as("Имя пользователя в базе данных должно совпадать с ожидаемым значением")
                .isEqualTo(userResponse.getData().getUsername());
        logger.info("Имя пользователя в базе данных совпадает с ожидаемым значением.");
    }
}
