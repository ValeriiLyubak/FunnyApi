package tests;

import dto.request.QuestionRequest;
import dto.response.QuestionResponse;
import io.restassured.response.Response;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import services.QuestionService;
import utils.DatabaseHelper;
import utils.TestDataGenerator;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest extends BaseTest {

    private final QuestionService questionService = new QuestionService();

    @Test
    public void addQuestionTest() {
        File jsonSchema = new File("src/test/resources/json-schema/question-response-schema.json");
        logger.debug("Используется схема JSON для ответа из файла: {}", jsonSchema.getAbsolutePath());

        QuestionRequest questionRequest = TestDataGenerator.getQuestion();
        logger.info("Создан запрос для добавления вопроса: {}", questionRequest);

        String currentToken = getToken();
        logger.info("Получен токен авторизации: {}", currentToken);

        Response response = questionService.addQuestionandGetResponse(currentToken, questionRequest, jsonSchema);
        logger.info("Получен ответ на запрос добавления вопроса.");

        logger.debug("Ответ от сервера: {}", response.asPrettyString());

        QuestionResponse questionResponse = response.as(QuestionResponse.class);
        logger.info("Ответ успешно разобран в объект QuestionResponse.");

        Document foundQuestion = DatabaseHelper.getQuestionFromDatabase(questionResponse);
        logger.info("Поиск вопроса в базе данных по объекту QuestionResponse: {}", questionResponse);

        assertThat(foundQuestion)
                .as("Вопрос должен быть найден в базе данных")
                .isNotNull();
        logger.info("Вопрос успешно найден в базе данных.");

        assertThat(foundQuestion.getString("name"))
                .as("Имя вопроса в базе данных должно совпадать с ожидаемым значением")
                .isEqualTo(questionResponse.getData().getName());
        logger.info("Имя вопроса в базе данных совпадает с ожидаемым значением.");
    }
}


