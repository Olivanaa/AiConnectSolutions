package br.com.fiap.AiConnectSolutions.service;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.AiConnectSolutions.model.Cliente;
import br.com.fiap.AiConnectSolutions.repository.ClienteRepository;
import jakarta.validation.Valid;

@Service
public class ClienteService {

    ClienteRepository repository;
    EnderecoService enderecoService;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }   

    public Cliente criar(@Valid Cliente cliente) { //, Enderecos end
        // end.setCliente(cliente); 
        // enderecoService.criar(end); 
        // cliente.getEndereco().add(end); 
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
    
}