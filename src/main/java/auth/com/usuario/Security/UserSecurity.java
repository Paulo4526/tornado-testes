package auth.com.usuario.Security;

import auth.com.usuario.Token.Verificartoken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class UserSecurity {
    @Autowired
    private Verificartoken verificarToken;


    @Bean
    public SecurityFilterChain filtrarSeguranca(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autorize -> autorize
                        //Autorização para o Usuario
                        .requestMatchers(HttpMethod.POST, "/auth/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/usuario/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/usuario/listarTodos").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/auth/usuario/atualizar").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/auth/usuario/{id}").permitAll()
                        //Autorização para o Tornado
                        .requestMatchers(HttpMethod.POST, "/tornado/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/tornado/atualizar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tornado/listarTodos").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/tornado/excluir/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tornado/buscarPorNome/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tornado/buscarEntreData/**").permitAll()
                        //Autorização para o Clima
                        .requestMatchers(HttpMethod.POST, "/clima/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/clima/atualizar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/clima/listarTodos").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/clima/excluir/{id}").permitAll()
                        //Autorização para o Dano
                        .requestMatchers(HttpMethod.POST, "/dano/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/dano/atualizar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dano/listarTodos").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/dano/excluir/{id}").permitAll()

                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                        verificarToken, UsernamePasswordAuthenticationFilter.class
                ).build();
    }

    @Bean
    //Aqui usamos a validação do nosso login passados pela classe AuthController e fará a autenticação da nossa requisilção HTTP
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


    //PasswordEncoder já cria a criptografia da nossa aplicação criptografando a senha que o usuario passar.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
