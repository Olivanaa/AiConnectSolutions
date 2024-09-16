package br.com.fiap.AiConnectSolutions.endereco.dto;

import br.com.fiap.AiConnectSolutions.endereco.Endereco;

public record EnderecoFormRequest(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String pais,
        String cep,
        String tipo
) {
    public Endereco toModel() {
        return Endereco.builder()
                .logradouro(this.logradouro)
                .numero(this.numero)
                .bairro(this.bairro)
                .cidade(this.cidade)
                .estado(this.estado)
                .pais(this.pais)
                .cep(this.cep)
                .tipo(this.tipo)
                .build();
    }
}
