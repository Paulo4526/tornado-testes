package auth.com.usuario.Model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class DanosTestes {
    @Expose(serialize = false)
    private Long id;
    @Expose
    private Long idTornado;
    @Expose
    private String dano;
    @Expose
    private String local;

}
