package br.com.judev.jela.dto.Estoque;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EstoqueRequest(@NotNull @Min(0) Integer qtdAtual,
                             @NotNull @Min(0) Integer qtdMinima,
                             @NotNull @Min(0) Integer qtdMaxima,
                             @NotNull Integer idProduto
) {}
