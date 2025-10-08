package br.com.judev.jela.dto.cliente;

import br.com.judev.jela.dto.endereco.EnderecoResponse;
import br.com.judev.jela.entity.Cliente;

public record ClienteResponse(
       String nome,
       String cpf,
       String email,
       String senha,
       String telefone,
       EnderecoResponse endereco
) {

}
