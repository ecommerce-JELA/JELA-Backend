package br.com.judev.jela.entity;

import br.com.judev.jela.entity.enums.Tamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "tb_produto")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 200, message = "A descrição não pode passar de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade deve ser maior que 0")
    @Column(nullable = false)
    private Integer quantidade;

    @PastOrPresent(message = "A data de cadastro não pode ser futura")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tamanho é obrigatório")
    @Column(nullable = false)
    private Tamanho tamanho;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Produto() {}

    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataCadastro = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome do produto é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome do produto é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "A descrição é obrigatória") @Size(max = 200, message = "A descrição não pode passar de 200 caracteres") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "A descrição é obrigatória") @Size(max = 200, message = "A descrição não pode passar de 200 caracteres") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O preço é obrigatório") @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0") BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(@NotNull(message = "O preço é obrigatório") @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0") BigDecimal preco) {
        this.preco = preco;
    }

    public @NotNull(message = "A quantidade é obrigatória") @Min(value = 0, message = "A quantidade deve ser maior que 0") Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@NotNull(message = "A quantidade é obrigatória") @Min(value = 0, message = "A quantidade deve ser maior que 0") Integer quantidade) {
        this.quantidade = quantidade;
    }

    public @PastOrPresent(message = "A data de cadastro não pode ser futura") LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(@PastOrPresent(message = "A data de cadastro não pode ser futura") LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public @NotNull(message = "O tamanho é obrigatório") Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(@NotNull(message = "O tamanho é obrigatório") Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
