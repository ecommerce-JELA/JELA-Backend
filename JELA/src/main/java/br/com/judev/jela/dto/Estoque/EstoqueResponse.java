package br.com.judev.jela.dto.Estoque;

import java.time.LocalDate;

public record EstoqueResponse(
        Integer idEstoque,
        Integer qtdAtual,
        Integer qtdMinima,
        Integer qtdMaxima,
        LocalDate dataAtualizacao,
        String nomeProduto
) {}
