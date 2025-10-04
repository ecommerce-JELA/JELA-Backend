package br.com.judev.jela.entity.enums;

public enum StatusPedido {
    PENDENTE,        // Pedido criado, aguardando pagamento
    PROCESSANDO,     // Pagamento confirmado, separando estoque
    ENVIADO,         // Pedido já saiu para entrega
    ENTREGUE,        // Pedido entregue ao cliente
    CANCELADO;       // Pedido cancelado
}
