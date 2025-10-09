package br.com.judev.jela.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank(message = "O nome não pode estar em branco") String nome,
        @NotBlank(message = "O CPF não pode estar em branco") String cpf,
        @Email(message = "Deve ser um email valido") String email,
        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres") String senha,
        @NotBlank(message = "O telefone não pode estar em branco") String telefone,
        @NotNull (message = "CEP deve ser confirmado")String cep
) {
}
