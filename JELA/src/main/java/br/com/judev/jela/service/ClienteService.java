package br.com.judev.jela.service;

import br.com.judev.jela.dto.cliente.*;
import br.com.judev.jela.dto.endereco.EnderecoResponse;
import br.com.judev.jela.entity.Cliente;
import br.com.judev.jela.Repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ViaCepService viaCepService;


    public ClienteService(ClienteRepository clienteRepository, ViaCepService viaCepService) {
        this.clienteRepository = clienteRepository;
        this.viaCepService = viaCepService;
    }

    public ClienteResponse cadastrarCliente(ClienteRequest request) {
        EnderecoResponse endereco = viaCepService.buscarEnderecoPorCep(request.cep());

        if (endereco == null || endereco.cep() == null) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado.");
        }

        Cliente cliente = new Cliente(
                request.nome(),
                request.cpf(),
                request.email(),
                request.senha(),
                request.telefone(),
                request.cep()
        );

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponse(
                clienteSalvo.getNome(),
                clienteSalvo.getCpf(),
                clienteSalvo.getEmail(),
                clienteSalvo.getSenha(),
                clienteSalvo.getTelefone(),
                endereco
        );
}

    public LoginResponse logarCliente(LoginRequest request) {
        Cliente cliente = clienteRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException("Email ou senha inválidos"));

        if (!cliente.getSenha().equals(request.senha())) {
            throw new EntityNotFoundException("Email ou senha inválidos");
        }

        return new LoginResponse("Login realizado com sucesso!");
    }

    public AtualizarClienteResponse atualizarCliente(AtualizarClienteRequest request, Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        if (request.nome() != null && !request.nome().isBlank()) {
            cliente.setNome(request.nome());
        }

        if (request.email() != null && !request.email().isBlank() && !request.email().equals(cliente.getEmail())) {
            clienteRepository.findByEmail(request.email())
                    .ifPresent(c -> {
                        if (!c.getId().equals(cliente.getId())) {
                            throw new IllegalArgumentException("E-mail já está sendo usado por outro cliente.");
                        }
                    });
            cliente.setEmail(request.email());
        }

        if (request.telefone() != null && !request.telefone().isBlank()) {
            cliente.setTelefone(request.telefone());
        }

        if (request.cep() != null && !request.cep().isBlank()) {
            cliente.setCep(request.cep());
        }

        if (request.senha() != null && !request.senha().isBlank()) {
            cliente.setSenha(request.senha());
        }

        clienteRepository.save(cliente);
        return new AtualizarClienteResponse("Cliente atualizado com sucesso!");
    }

    public ClienteResponse encontrarClientePorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        EnderecoResponse endereco = viaCepService.buscarEnderecoPorCep(cliente.getCep());

        return new ClienteResponse(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getSenha(),
                cliente.getTelefone(),
                endereco
        );
    }


}
