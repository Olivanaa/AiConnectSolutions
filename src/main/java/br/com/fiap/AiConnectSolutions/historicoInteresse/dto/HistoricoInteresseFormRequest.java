package br.com.fiap.AiConnectSolutions.historicoInteresse.dto;

import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresse;

import java.time.LocalDate;

public record HistoricoInteresseFormRequest(
        String interesse,
        LocalDate dtInteracao
) {
    public HistoricoInteresse toModel(){
        return HistoricoInteresse.builder()
                .interesse(interesse)
                .dtInteracao(dtInteracao)
                .build();
    }
}
