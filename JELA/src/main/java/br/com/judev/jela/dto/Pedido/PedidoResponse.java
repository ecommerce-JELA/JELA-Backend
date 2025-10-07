package br.com.judev.jela.dto.Pedido;

import br.com.judev.jela.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PedidoResponse(
         Integer id,
         String status,
         Integer clienteId,
         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
         LocalDateTime data,
         Cliente cliente

) {

public record PedidoResponse(String message) {

}
