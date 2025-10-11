package br.com.judev.jela.controller;

import br.com.judev.jela.dto.Categoria.CategoriaRequest;
import br.com.judev.jela.dto.Categoria.CategoriaResponse;
import br.com.judev.jela.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

 private final CategoriaService  categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> cadastrarCategoria(@RequestBody CategoriaRequest request) {
        CategoriaResponse response = categoriaService.cadastrarCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoria(
            @PathVariable Integer id,
            @RequestBody CategoriaRequest request) {

        CategoriaResponse response = categoriaService.atualizarCategoria(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCategoria(@PathVariable Integer id) {
        categoriaService.removerCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> consultarCategoria(@PathVariable Integer id) {
        CategoriaResponse response = categoriaService.consultarCategoria(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        List<CategoriaResponse> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }
}
