package br.com.judev.jela.controller;

import br.com.judev.jela.dto.endereco.EnderecoResponse;
import br.com.judev.jela.service.ViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/consulta-cep")
public class EnderecoController {

    private final ViaCepService  viaCepService;

    public EnderecoController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> getEndereco(@PathVariable String cep) {
        try {
            EnderecoResponse endereco = viaCepService.buscarEnderecoPorCep(cep);
            return ResponseEntity.ok(endereco);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao consultar o CEP.");
        }
    }
}
