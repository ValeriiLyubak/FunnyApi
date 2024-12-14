package tests;

import dto.request.QuizRequest;
import dto.response.QuizResponse;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import services.QuizService;
import utils.DatabaseHelper;
import utils.TestDataGenerator;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuizTest extends BaseTest {

    private final QuizService quizService = new QuizService();

    @Test
    public void addQuizTest() {
        File jsonSchema = new File("src/test/resources/json-schema/quiz-response-schema.json");
        logger.debug("Используется схема JSON для ответа из файла: {}", jsonSchema.getAbsolutePath());

        List<QuizRequest.Variation> variations = TestDataGenerator.getVariationList();
        List<QuizRequest.File> files = List.of();
        QuizRequest quizRequest = new QuizRequest("quiz", true, "testQuiz", files, variations);
        logger.info("Создан запрос для добавления квиза: {}", quizRequest);

        String currentToken = getToken();
        logger.info("Получен токен авторизации: {}", currentToken);

        var response = quizService.addQuizAndGetResponse(quizRequest, currentToken, jsonSchema);
        logger.info("Получен ответ на запрос добавления квиза.");
        logger.debug("Ответ от сервера: {}", response.asPrettyString());

        QuizResponse quizResponse = response.as(QuizResponse.class);
        logger.info("Ответ успешно разобран в объект QuizResponse.");

        Document foundQuiz = DatabaseHelper.getQuizFromDatabase(quizResponse);
        logger.info("Поиск квиза в базе данных по объекту QuizResponse: {}", quizResponse);

        assertThat(foundQuiz)
                .as("Квиз должен быть найден в базе данных")
                .isNotNull();
        logger.info("Квиз успешно найден в базе данных.");

        assertThat(foundQuiz.getString("name"))
                .as("Имя квиза в базе данных должно совпадать с ожидаемым значением")
                .isEqualTo(quizResponse.getData().getName());
        logger.info("Имя квиза в базе данных совпадает с ожидаемым значением.");
    }
}


