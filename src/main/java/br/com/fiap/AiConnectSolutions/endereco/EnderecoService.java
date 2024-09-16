package br.com.fiap.AiConnectSolutions.endereco;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.fiap.AiConnectSolutions.endereco.dto.EnderecoFormRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.List;

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
            () -> new ResponseStatusException(NOT_FOUND, "Endereço não encontrado")
        );
    }

    public List<Endereco> buscarTodas() {
        return repository.findAll();
    }

    public Endereco atualizar(Long id, EnderecoFormRequest endereco) {
        verificarEndereco(id);
        Endereco enderecoAtualizado = endereco.toModel();
        enderecoAtualizado.setId(id);
        return repository.save(enderecoAtualizado);
    }
    public void deletar(Long id) {
        verificarEndereco(id);
        repository.deleteById(id);
    }


    private void verificarEndereco(Long id) {
        repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Não existe endereco com o id informado."));
    }

}