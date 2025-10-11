package br.com.judev.jela.controller;

import br.com.judev.jela.Repository.ClienteRepository;
import br.com.judev.jela.dto.cliente.*;
import br.com.judev.jela.entity.Cliente;
import br.com.judev.jela.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteRequest request) {
        try {
            ClienteResponse response = clienteService.cadastrarCliente(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao cadastrar cliente.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logarCliente(@Valid @RequestBody LoginRequest request) {
        try {
            LoginResponse response = clienteService.logarCliente(request);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao realizar login.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Integer id, @Valid @RequestBody AtualizarClienteRequest request) {
        try {
            AtualizarClienteResponse response = clienteService.atualizarCliente(request, id);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar cliente.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarClientePorId(@PathVariable Integer id) {
        try {
            ClienteResponse response = clienteService.encontrarClientePorId(id);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar cliente.");
        }
    }
}
