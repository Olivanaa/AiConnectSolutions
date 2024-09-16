package br.com.fiap.AiConnectSolutions.lead;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresse;
import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresseService;
import br.com.fiap.AiConnectSolutions.lead.dto.LeadFormRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Service
public class LeadService {

    LeadRepository repository;
    HistoricoInteresseService historicoInteresseService;

    public LeadService(LeadRepository repository, HistoricoInteresseService historicoInteresseService) {
        this.repository = repository;
        this.historicoInteresseService = historicoInteresseService;
    }

    @Transactional
    public Lead criar(@Valid LeadFormRequest leadForm) {
        HistoricoInteresse interesse = historicoInteresseService.criar(leadForm.interesse().toModel());
        Lead lead = leadForm.toModel(interesse);
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

    public Lead atualizar(Long id, @Valid LeadFormRequest leadForm) {
        verificarLead(id);
        Lead lead = buscarPorId(id);

        HistoricoInteresse historicoAtualizado;
        if(leadForm.interesse() != null) {
            historicoAtualizado = historicoInteresseService.atualizar(lead.getInteresse().getId(), leadForm.interesse());
        }else {
            historicoAtualizado = lead.getInteresse();
        }
        Lead leadAtualizado = leadForm.toModel(historicoAtualizado);

        leadAtualizado.setId(id);
        return repository.save(leadAtualizado);
    }

    private void verificarLead(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe lead com o id informado."));
    }
}
