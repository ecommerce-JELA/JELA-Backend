package br.com.judev.jela.dto.Pedido;

import jakarta.validation.constraints.NotNull;

public record ItemRequest(@NotNull Integer produtoId,
                          @NotNull Integer quantidade) {
}
