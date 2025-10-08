package br.com.judev.jela.dto.Pedido;

import br.com.judev.jela.entity.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Set;

public record PedidoResponse(
        Integer id,
        StatusPedido status,
        LocalDateTime data,
        Integer clienteId,
        Set<ItemResponse> itens
) {}
