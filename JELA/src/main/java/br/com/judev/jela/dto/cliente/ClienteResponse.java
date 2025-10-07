package br.com.judev.jela.dto.cliente;

import br.com.judev.jela.dto.endereco.EnderecoResponse;

public record ClienteResponse(
       String nome,
       String cpf,
       String email,
       String senha,
       String telefone,
       EnderecoResponse endereco
) {
}
