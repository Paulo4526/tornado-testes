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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class UserSecurity {
    @Autowired
    private Verificartoken verificarToken;

    private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
            "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
            "/api/test/**", "/authenticate" };


    @Bean
    public SecurityFilterChain filtrarSeguranca(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autorize -> autorize
                        //Autorização para o Usuario
                        .requestMatchers(HttpMethod.POST, "/auth/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/usuario/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/usuario/listarTodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/auth/usuario/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/auth/usuario/{id}").hasRole("ADMIN")
                        //Autorização para o Tornado
                        .requestMatchers(HttpMethod.POST, "/tornado/cadastrar").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/tornado/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tornado/listarTodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/tornado/excluir/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tornado/buscarPorNome/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/tornado/buscarEntreData/**").hasAnyRole("ADMIN", "USER")
                        //Autorização para o Clima
                        .requestMatchers(HttpMethod.POST, "/clima/cadastrar").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/clima/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/clima/listarTodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/clima/excluir/{id}").hasRole("ADMIN")
                        //Autorização para o Dano
                        .requestMatchers(HttpMethod.POST, "/dano/cadastrar").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/dano/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/dano/listarTodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/dano/excluir/{id}").hasRole("ADMIN")
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                        verificarToken, UsernamePasswordAuthenticationFilter.class
                ).build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // ajuste conforme necessário
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
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
