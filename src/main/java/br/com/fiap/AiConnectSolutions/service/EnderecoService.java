package br.com.fiap.AiConnectSolutions.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.AiConnectSolutions.model.Endereco;
import br.com.fiap.AiConnectSolutions.repository.EnderecoRepository;
import jakarta.validation.Valid;

@Service
public class EnderecoService {

    EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }   

    public Endereco criar(@Valid Endereco end) {
        return repository.save(end);
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado")
        );
    }

}