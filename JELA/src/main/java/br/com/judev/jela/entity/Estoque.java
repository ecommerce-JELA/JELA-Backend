package br.com.judev.jela.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstoque;

    @Column(nullable = false)
    private Integer qtdAtual;

    @Column(nullable = false)
    private Integer qtdMinima;

    @Column(nullable = false)
    private Integer qtdMaxima;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate dataAtualizacao = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false, unique = true)
    private Produto produto;

    public Estoque() {}

    public Estoque(Integer qtdAtual, Integer qtdMinima, Integer qtdMaxima, Produto produto) {
        this.qtdAtual = qtdAtual;
        this.qtdMinima = qtdMinima;
        this.qtdMaxima = qtdMaxima;
        this.produto = produto;
        this.dataAtualizacao = LocalDate.now();
    }

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public Integer getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(Integer qtdAtual) {
        this.qtdAtual = qtdAtual;
        this.dataAtualizacao = LocalDate.now();
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
        this.dataAtualizacao = LocalDate.now();
    }

    public Integer getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(Integer qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
        this.dataAtualizacao = LocalDate.now();
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
