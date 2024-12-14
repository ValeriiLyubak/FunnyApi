package dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequest {

    private String answerType;
    private Boolean isValid;
    private String name;
    private List<File> files;
    private List<Variation> variations;

    @lombok.Data
    @NoArgsConstructor
    public static class File {

    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Variation {

        private String name;
        private Boolean isCorrect;

    }

}
