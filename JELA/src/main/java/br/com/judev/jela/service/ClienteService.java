package br.com.judev.jela.service;

import br.com.judev.jela.dto.cliente.*;
import br.com.judev.jela.dto.endereco.EnderecoResponse;
import br.com.judev.jela.entity.Cliente;
import br.com.judev.jela.Repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final RestTemplate  restTemplate;


    public ClienteService(ClienteRepository clienteRepository, RestTemplate restTemplate) {
        this.clienteRepository = clienteRepository;
        this.restTemplate = restTemplate;
    }

    public ClienteResponse cadastrarCliente(RegisterClienteRequest request) {
        String url = "https://viacep.com.br/ws/" + request.cep() + "/json/";
        EnderecoResponse enderecoResponse = restTemplate.getForObject(url, EnderecoResponse.class);

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
                enderecoResponse
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

}
