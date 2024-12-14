package dto.response;

import dto.request.QuizRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponse {

    private Data data;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private Integer _id;
        private List<String> correctAnswers;
        private List<QuizRequest.File> files;
        private List<String> names;
        private List<String> audioNames;
        private String answerType;
        private Boolean isValid;
        private String name;
        private List<QuizRequest.Variation> variations;
        private List<String> correctCodeVariations;
        private Integer user;
        private String cd;
        private Boolean isAudio;
        private Boolean isCode;
    }

}
