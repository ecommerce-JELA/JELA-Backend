package br.com.judev.jela.service;

import br.com.judev.jela.dto.endereco.EnderecoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class ViaCepService {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";


    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        EnderecoResponse endereco = restTemplate.getForObject(VIA_CEP_URL, EnderecoResponse.class, cep);

        if (endereco == null || endereco.cep() == null) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado.");
        }
        return endereco;
    }
}
