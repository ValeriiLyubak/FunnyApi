package dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Data data;

    @lombok.Data
    public static class Data {
        private Integer _id;
        private List<String> teams;
        private String surname;
        private String username;
        private String email;
        private List<String> roles;
        private List<String> positions;
        private List<String> cities;
        private List<String> companies;
        private List<String> work_history;
        private List<String> edu_history;
        private CustomData customData;
        private String cd;
        private String name;
    }

    @lombok.Data
    public static class CustomData {
        private String salesOpenTime;
        private String salesStatus;
        @JsonProperty("isCV")
        private Boolean cv;
    }
}
