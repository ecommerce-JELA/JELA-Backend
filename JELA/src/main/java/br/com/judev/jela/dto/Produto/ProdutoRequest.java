package br.com.judev.jela.dto.Produto;

import br.com.judev.jela.entity.Estoque;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "O nome do produto é obrigatório") String nome,
        @NotBlank(message = "A descrição é obrigatória") String descricao,
        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value="0.01", message = "O valor do produto deve ser maior que 0,01") BigDecimal preco,
        @NotBlank(message = "O tamanho é obrigatório") String tamanho,
        @NotBlank(message = "A quantidade em estoque é obrigatória") Estoque estoque
        ) {
}
