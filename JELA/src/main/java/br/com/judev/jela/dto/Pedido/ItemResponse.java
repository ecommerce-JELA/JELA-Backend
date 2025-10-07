package br.com.judev.jela.dto.Pedido;

import java.math.BigDecimal;

public record ItemResponse(
        Long produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
) {}

