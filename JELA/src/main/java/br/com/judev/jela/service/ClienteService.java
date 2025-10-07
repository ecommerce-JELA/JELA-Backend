package br.com.judev.jela.service;

import br.com.judev.jela.Repository.ClienteRepository;
import br.com.judev.jela.dto.cliente.RegisterClienteRequest;
import br.com.judev.jela.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
         this.clienteRepository = clienteRepository;
    }
    public void salvarCliente(RegisterClienteRequest clienteRequest) {
        Cliente cliente = new Cliente(
                clienteRequest.nome(),
                clienteRequest.cpf(),
                clienteRequest.email(),
                clienteRequest.senha(),
                clienteRequest.telefone(),
                clienteRequest.cep()
        );
        clienteRepository.save(cliente);
    }

    public Cliente getCliente(int id) {
        return clienteRepository.findById(id).orElse(null);
    }
}
