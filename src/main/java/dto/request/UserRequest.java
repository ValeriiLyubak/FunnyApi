package dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private CustomData customData;
    private String firstname;
    private String surname;
    private String email;
    private String username;
    private String plainPassword;
    private String roles;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomData {
        private Boolean isCV;
        private String salesOpenTime;
        private String salesStatus;
    }

}
