package br.com.judev.jela.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarClienteRequest(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank String cep,
        @Email(message = "Deve ser um email válido") String email,
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres") String senha
) {}

