package dto.request;

import lombok.Data;
import config.ConfigManager;

@Data
public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest() {
        this.username = ConfigManager.getProperty("login.username");
        this.password = ConfigManager.getProperty("login.password");
    }
}

