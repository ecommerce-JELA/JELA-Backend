package br.com.judev.jela.entity;

import br.com.judev.jela.entity.enums.TipoPagamento;
import br.com.judev.jela.entity.enums.StatusPagamento;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "tb_pagamento")
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;

    private String metodo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    public Pagamento() {}

    public Pagamento(BigDecimal valor, StatusPagamento status, String metodo, TipoPagamento tipoPagamento, LocalDate dataPagamento) {
        this.valor = valor;
        this.status = status;
        this.metodo = metodo;
        this.tipoPagamento = tipoPagamento;
        this.dataPagamento = dataPagamento;
    }

    public Long getId() { return id; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public StatusPagamento getStatus() { return status; }
    public void setStatus(StatusPagamento status) { this.status = status; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(TipoPagamento tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
}
