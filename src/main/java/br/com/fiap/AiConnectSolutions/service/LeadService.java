package br.com.fiap.AiConnectSolutions.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.AiConnectSolutions.model.Lead;
import br.com.fiap.AiConnectSolutions.repository.LeadRepository;
import jakarta.validation.Valid;

@Service
public class LeadService {

    LeadRepository repository;

    public LeadService(LeadRepository repository) {
        this.repository = repository;
    }

    public Lead criar(@Valid Lead lead) {
        return repository.save(lead);
    }

    public List<Lead> buscarTodas() {
        return repository.findAll();
    }

    public Lead buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Lead não encontrado"));
    }

    public void deletar(Long id) {
        verificarLead(id);
        repository.deleteById(id);
    }

    public Lead atualizar(Long id, Lead lead) {
        verificarLead(id);
        lead.setId(id);
        return repository.save(lead);
    }

    private void verificarLead(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe lead com o id informado."));
    }
}
