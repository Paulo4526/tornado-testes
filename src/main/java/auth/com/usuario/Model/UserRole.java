package auth.com.usuario.Model;

import java.util.Arrays;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role= role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole fromRole(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.role.equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Role inválido: " + role));
    }
}
