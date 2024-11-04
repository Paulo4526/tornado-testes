package auth.com.usuario.Model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    @Expose
    private Long id;
    @Expose
    private String nome;
    @Expose
    private String email;
    @Expose
    private String senha;
    @Expose
    private UserRole user;
    @Expose
    private String data;
}
