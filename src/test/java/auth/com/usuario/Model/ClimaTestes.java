package auth.com.usuario.Model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClimaTestes {
    @Expose(serialize = false)
    private Long id;
    @Expose
    private Long idTornado;
    @Expose
    private String clima;
    @Expose
    private Integer temperatura;
    @Expose
    private String data;
    @Expose
    private String local;
}
