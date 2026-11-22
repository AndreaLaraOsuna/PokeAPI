package lara.carmen.PokeAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Correo no válido")
    @NotBlank(message ="Correo obligatorio")
    private String mail;

    @NotBlank(message = "contraseña obligatoria")
    private String password;

}
