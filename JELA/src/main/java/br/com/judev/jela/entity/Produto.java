package br.com.judev.jela.entity;

import br.com.judev.jela.entity.enums.Tamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @PastOrPresent(message = "A data de cadastro não pode ser futura")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tamanho é obrigatório")
    @Column(nullable = false)
    private Tamanho tamanho;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
    private Estoque estoque;

    public Produto() {}

    public Produto(String nome, String descricao, BigDecimal preco, Tamanho tamanho, Estoque estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tamanho = tamanho;
        this.estoque = estoque;
        this.dataCadastro = LocalDate.now();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    public Tamanho getTamanho() { return tamanho; }
    public void setTamanho(Tamanho tamanho) { this.tamanho = tamanho; }
    public Estoque getEstoque() { return estoque; }
    public void setEstoque(Estoque estoque) {
        this.estoque = this.estoque;
    if(this.estoque.getProduto() != this){
        this.estoque.setProduto(this);
       }
    }
}
