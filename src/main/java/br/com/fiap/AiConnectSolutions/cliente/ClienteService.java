package br.com.fiap.AiConnectSolutions.cliente;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.fiap.AiConnectSolutions.cliente.dto.ClienteFormRequest;
import br.com.fiap.AiConnectSolutions.endereco.Endereco;
import br.com.fiap.AiConnectSolutions.endereco.EnderecoService;
import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresse;
import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Service
public class ClienteService {


    ClienteRepository repository;

    EnderecoService enderecoService;

    HistoricoInteresseService historicoInteresseService;


    public ClienteService(ClienteRepository repository, EnderecoService enderecoService, HistoricoInteresseService historicoInteresseService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.historicoInteresseService = historicoInteresseService;
    }

    @Transactional
    public Cliente criar(@Valid ClienteFormRequest clienteFormRequest) {
        Endereco end = enderecoService.criar(clienteFormRequest.endereco().toModel());
        HistoricoInteresse interesse = historicoInteresseService.criar(clienteFormRequest.interesse().toModel());

        Cliente cliente = clienteFormRequest.toModel(end, interesse);
        return repository.save(cliente);
    }

    public List<Cliente> buscarTodas() {
        return repository.findAll();
    }


    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado")
        );
    }

    @Transactional
    public Cliente atualizar(Long id, @Valid ClienteFormRequest clienteFormRequest) {
        verificarCliente(id);
        Cliente cli = buscarPorId(id);

        Endereco enderecoAtualizado;
        if (clienteFormRequest.endereco() != null) {
            enderecoAtualizado = enderecoService.atualizar(cli.getEndereco().getId(), clienteFormRequest.endereco());
        } else {
            enderecoAtualizado = cli.getEndereco();
        }

        HistoricoInteresse historicoAtualizado;
        if (clienteFormRequest.interesse() != null) {
            historicoAtualizado = historicoInteresseService.atualizar(cli.getInteresse().getId(), clienteFormRequest.interesse());
        } else {
            historicoAtualizado = cli.getInteresse();
        }

        Cliente clienteAtualizado = clienteFormRequest.toModel(enderecoAtualizado, historicoAtualizado);
        clienteAtualizado.setId(id);

        clienteAtualizado.setId(id);

        return repository.save(clienteAtualizado);
    }

    public void remover(Long id) {
        verificarCliente(id);
        repository.deleteById(id);
    }

    private void verificarCliente(Long id) {
        repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Não existe cliente com o id informado."));
    }

    
}