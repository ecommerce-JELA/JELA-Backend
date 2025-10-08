package br.com.judev.jela.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @Email(message = "Deve ser um email válido")
        @NotBlank(message = "O email não pode estar em branco")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha
) {}

