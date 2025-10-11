package br.com.judev.jela.controller;

import br.com.judev.jela.dto.Estoque.EstoqueRequest;
import br.com.judev.jela.dto.Estoque.EstoqueResponse;
import br.com.judev.jela.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> consultarEstoque(@PathVariable Integer id) {
        EstoqueResponse response = estoqueService.consultarEstoque(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponse> atualizarEstoque(
            @PathVariable Integer id,
            @RequestBody @Valid EstoqueRequest request
    ) {
        EstoqueResponse response = estoqueService.atualizarEstoque(id, request);
        return ResponseEntity.ok(response);
    }
}
