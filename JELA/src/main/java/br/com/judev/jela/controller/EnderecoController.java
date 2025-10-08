package br.com.judev.jela.controller;

import br.com.judev.jela.dto.endereco.EnderecoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/consulta-cep")
public class EnderecoController {

        @GetMapping("{cep}")
        public ResponseEntity<EnderecoResponse> getEndereco(@PathVariable String cep) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            EnderecoResponse endereco = restTemplate.getForObject(url, EnderecoResponse.class);
            if (endereco == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(endereco);
        }
}
