package br.com.fiap.AiConnectSolutions.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.AiConnectSolutions.model.HistoricoInteresse;
import br.com.fiap.AiConnectSolutions.repository.HistoricoInteresseRepository;
import jakarta.validation.Valid;

@Service
public class HistoricoInteresseService {

    HistoricoInteresseRepository repository;

    public HistoricoInteresseService(HistoricoInteresseRepository repository) {
        this.repository = repository;
    }

    public HistoricoInteresse criar(@Valid HistoricoInteresse historico) {
        return repository.save(historico);
    }

    public List<HistoricoInteresse> buscarTodas() {
        return repository.findAll();
    }

    public HistoricoInteresse buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Histórico não encontrado"));
    }

    public void deletar(Long id) {
        verificarHistorico(id);
        repository.deleteById(id);
    }

    public HistoricoInteresse atualizar(Long id, HistoricoInteresse historico) {
        verificarHistorico(id);
        historico.setId(id);
        return repository.save(historico);
    }

    private void verificarHistorico(Long id) {
        repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Não existe histórico de interesse com o id informado."));
    }

}
