package br.com.fiap.AiConnectSolutions.cliente.dto;

import br.com.fiap.AiConnectSolutions.cliente.Cliente;
import br.com.fiap.AiConnectSolutions.endereco.Endereco;
import br.com.fiap.AiConnectSolutions.endereco.dto.EnderecoFormRequest;
import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresse;
import br.com.fiap.AiConnectSolutions.historicoInteresse.dto.HistoricoInteresseFormRequest;

import java.time.LocalDate;

public record ClienteFormRequest(
    String nome,
    String email,
    String telefone,
    LocalDate dtaCadastro,
    LocalDate dtaNascimento,
    String segmentoMercado,
    LocalDate dtaUltimaInteracao,
    EnderecoFormRequest endereco,
    HistoricoInteresseFormRequest interesse
) {
    public Cliente toModel(Endereco endereco, HistoricoInteresse interesse) {
        return Cliente.builder()
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .dtaNascimento(dtaNascimento)
                .dtaCadastro(dtaCadastro)
                .segmentoMercado(segmentoMercado)
                .dtaUltimaInteracao(dtaUltimaInteracao)
                .endereco(endereco)
                .interesse(interesse)
                .build();
    }
}
