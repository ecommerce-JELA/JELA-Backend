package br.com.judev.jela.dto.Pedido;

import br.com.judev.jela.entity.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record PedidoRequest(
        @NotNull Integer clienteId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime data,
        StatusPedido status,
        @NotEmpty Set<ItemRequest> itens) {
}
