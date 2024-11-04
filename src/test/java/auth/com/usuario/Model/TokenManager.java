package auth.com.usuario.Model;

import lombok.Data;

@Data
public class TokenManager {

    private static TokenManager instance;
    private String token;

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

}
