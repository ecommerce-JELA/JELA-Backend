package br.com.judev.jela.controller;

import br.com.judev.jela.Repository.ClienteRepository;
import br.com.judev.jela.dto.cliente.RegisterClienteRequest;
import br.com.judev.jela.entity.Cliente;
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
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid
                                               RegisterClienteRequest registerClienteRequest) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(registerClienteRequest, cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Object>> getClientes(@PathVariable(value = "id")Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body((List<Object>) cliente.get());
        }

    }
}
