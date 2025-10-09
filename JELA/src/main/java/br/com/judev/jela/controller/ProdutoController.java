package br.com.judev.jela.controller;

import br.com.judev.jela.dto.Produto.ProdutoResponse;
import br.com.judev.jela.entity.Produto;
import br.com.judev.jela.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/produto")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid
                                                    Produto produto){
        var novoProduto = new Produto();
        BeanUtils.copyProperties(produto, novoProduto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<ProdutoResponse> getProduto(@RequestParam(value = "id") Integer id){
        Produto produto = produtoService.buscarPorId(id);
        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ProdutoResponse produtoResponse = new ProdutoResponse(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getTamanho(),
                produto.getEstoque()
        );
        return ResponseEntity.status(HttpStatus.OK).body(produtoResponse);
    }

    @DeleteMapping
    public ResponseEntity<ProdutoResponse> deletarProduto(@RequestParam(value = "id") Integer id){
        Produto produto = produtoService.buscarPorId(id);
        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}