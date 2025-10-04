package br.com.judev.jela.entity;

import br.com.judev.jela.entity.enums.StatusPedido;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)  // salva como texto no banco (ex: "PENDENTE")
    private StatusPedido status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id" , nullable = false)
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Cliente getcLiente() {
        return cliente;
    }

    public void setcLiente(Cliente cliente) {
        this.cliente = cliente;
    }
}
