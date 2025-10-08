package br.com.judev.jela.dto.Produto;

import br.com.judev.jela.entity.Estoque;
import br.com.judev.jela.entity.enums.Tamanho;
import java.math.BigDecimal;

public record ProdutoResponse(
        String nome,
        String descricao,
        BigDecimal preco,
        Tamanho tamanho,
        Estoque estoque
) {
}
