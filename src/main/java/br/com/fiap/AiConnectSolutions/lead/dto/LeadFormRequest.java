package br.com.fiap.AiConnectSolutions.lead.dto;

import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresse;
import br.com.fiap.AiConnectSolutions.historicoInteresse.dto.HistoricoInteresseFormRequest;
import br.com.fiap.AiConnectSolutions.lead.Lead;

import java.time.LocalDate;

public record LeadFormRequest(
        String nome,
        String email,
        String telefone,
        LocalDate dtInteracao,
        HistoricoInteresseFormRequest interesse
) {
    public Lead toModel(HistoricoInteresse interesse){
        return Lead.builder()
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .dtInteracao(dtInteracao)
                .interesse(interesse)
                .build();
    }
}
