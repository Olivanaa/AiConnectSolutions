package br.com.fiap.AiConnectSolutions.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.AiConnectSolutions.model.Feedback;
import br.com.fiap.AiConnectSolutions.repository.FeedbackRepository;
import jakarta.validation.Valid;

@Service
public class FeedbackService {

    FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Feedback criar(@Valid Feedback feedback) {
        return repository.save(feedback);
    }

    public List<Feedback> buscarTodas() {
        return repository.findAll();
    }

    public Feedback buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Feedback não encontrado"));
    }

    public void deletar(Long id) {
        verificarFeedBack(id);
        repository.deleteById(id);
    }

    public Feedback atualizar(Long id, Feedback feedback) {
        verificarFeedBack(id);
        feedback.setId(id);
        return repository.save(feedback);
    }

    private void verificarFeedBack(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe feedback com o id informado."));
    }
}
