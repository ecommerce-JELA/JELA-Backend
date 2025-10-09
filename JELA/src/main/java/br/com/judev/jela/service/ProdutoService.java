package br.com.judev.jela.service;

import br.com.judev.jela.Repository.ProdutoRepository;
import br.com.judev.jela.dto.Produto.ProdutoRequest;
import br.com.judev.jela.dto.Produto.ProdutoResponse;
import br.com.judev.jela.entity.Produto;
import br.com.judev.jela.entity.Estoque;
import br.com.judev.jela.entity.enums.Tamanho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponse salvarProduto(ProdutoRequest request) {
        Produto produtoSalvo = new Produto();
        produtoSalvo.setNome(request.nome());
        produtoSalvo.setDescricao(request.descricao());
        produtoSalvo.setPreco(request.preco());
        produtoSalvo.setTamanho(Tamanho.valueOf(request.tamanho()));
        produtoSalvo.setEstoque(request.estoque());

        Produto response = produtoRepository.save(produtoSalvo);
        return new ProdutoResponse(
                response.getNome(),
                response.getDescricao(),
                response.getPreco(),
                response.getTamanho(),
                response.getEstoque());
    }


    public List<ProdutoResponse> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> new ProdutoResponse(
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getTamanho(),
                        produto.getEstoque()
                ))
                .toList();
    }

    public ProdutoResponse atualizarEstoque(Integer id, Integer quantidade) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = produto.getEstoque();
        if (estoque == null) {
            throw new RuntimeException("Estoque não cadastrado para o produto: " + produto.getNome());
        }
        int novoEstoque = estoque.getQtdAtual() + quantidade;
        if (novoEstoque < 0 || novoEstoque > estoque.getQtdMaxima()) {
            throw new RuntimeException("Quantidade inválida. Estoque atual: " + estoque.getQtdAtual() +
                    ", Quantidade solicitada: " + quantidade +
                    ", Estoque máximo: " + estoque.getQtdMaxima());
        }
        return new ProdutoResponse(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getTamanho(),
                estoque);
    }

    public void removerProduto(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }
}