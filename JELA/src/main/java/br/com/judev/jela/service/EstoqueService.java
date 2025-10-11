package br.com.judev.jela.service;

import br.com.judev.jela.Repository.EstoqueRepository;
import br.com.judev.jela.Repository.ProdutoRepository;
import br.com.judev.jela.dto.Estoque.EstoqueRequest;
import br.com.judev.jela.dto.Estoque.EstoqueResponse;
import br.com.judev.jela.entity.Estoque;
import br.com.judev.jela.entity.Produto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public EstoqueResponse consultarEstoque(Integer idEstoque) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado."));

        return new EstoqueResponse(
                estoque.getIdEstoque(),
                estoque.getQtdAtual(),
                estoque.getQtdMinima(),
                estoque.getQtdMaxima(),
                estoque.getDataAtualizacao(),
                estoque.getProduto().getNome()
        );
    }

    public EstoqueResponse atualizarEstoque(Integer idEstoque, EstoqueRequest request) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado."));

        Produto produto = produtoRepository.findById(request.idProduto())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

        estoque.setQtdAtual(request.qtdAtual());
        estoque.setQtdMinima(request.qtdMinima());
        estoque.setQtdMaxima(request.qtdMaxima());
        estoque.setProduto(produto);
        estoque.setProduto(produto);

        estoqueRepository.save(estoque);

        return new EstoqueResponse(
                estoque.getIdEstoque(),
                estoque.getQtdAtual(),
                estoque.getQtdMinima(),
                estoque.getQtdMaxima(),
                LocalDate.now(),
                produto.getNome()
        );
    }
}
