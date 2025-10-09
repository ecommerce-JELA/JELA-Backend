package br.com.judev.jela.controller;

import br.com.judev.jela.Repository.ProdutoRepository;
import br.com.judev.jela.dto.Produto.ProdutoResponse;
import br.com.judev.jela.entity.Produto;
import br.com.judev.jela.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/produto")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid Produto produto){
        var novoProduto = new Produto();
        BeanUtils.copyProperties(produto, novoProduto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        List<ProdutoResponse> produtos = produtoService.listarProdutos();

        if (produtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping
    public void deletarProduto(@PathVariable Integer id) {
        produtoService.removerProduto(id);
    }
}