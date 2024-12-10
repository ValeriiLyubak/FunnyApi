package dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {

    private Data data;

    @lombok.Data
    public static class Data {
        private Integer _id;
        private List<String> quizes;
        private List<Name> __names;
        private String name;
        private List<String> contributors;
        private List<String> decliners;
        private List<String> hints;
        private List<String> additionalQuestionsArr;
        private List<String> useCases;
        private List<String> videos;
        private List<String> facts;
        private List<String> interviews;
        private List<String> hashTags;
        private List<String> shortAnswers;
        private List<String> detailedAnswers;
        private String cd;
        private Boolean isDuplicated;
        private Integer useCasesLength;
        private Integer factsLength;
        private Integer answerProgressCount;

        @lombok.Data
        public static class Name {
            private String name;
        }
    }

}
