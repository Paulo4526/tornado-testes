package auth.com.usuario.Model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Tornadotestes {
    @Expose
    private Long id;
    @Expose
    private String nome;
    @Expose
    private String classificacao;
    @Expose
    private String local;
    @Expose
    private String data;
}
