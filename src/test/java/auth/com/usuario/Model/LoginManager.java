package auth.com.usuario.Model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class LoginManager {
    @Expose
    private String email;
    @Expose
    private String senha;
}
