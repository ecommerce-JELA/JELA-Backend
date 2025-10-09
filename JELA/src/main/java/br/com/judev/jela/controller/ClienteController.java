package br.com.judev.jela.controller;

import br.com.judev.jela.Repository.ClienteRepository;
import br.com.judev.jela.dto.cliente.ClienteRequest;
import br.com.judev.jela.dto.cliente.ClienteResponse;
import br.com.judev.jela.entity.Cliente;
import br.com.judev.jela.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> saveCliente(@RequestBody @Valid
                                               ClienteRequest registerClienteRequest) {
        ClienteResponse response = clienteService.cadastrarCliente(registerClienteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ClienteResponse>> getClientes(@PathVariable(value = "id")Integer id) {
        ClienteResponse cliente = clienteService.encontrarClientePorId(id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
