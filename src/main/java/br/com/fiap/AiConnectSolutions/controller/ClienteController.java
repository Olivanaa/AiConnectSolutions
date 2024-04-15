package br.com.fiap.AiConnectSolutions.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.AiConnectSolutions.model.Cliente;
import br.com.fiap.AiConnectSolutions.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente create(@RequestBody @Valid Cliente cliente) { //, @Valid Enderecos enderecos
        return service.criar(cliente);
    }

    @GetMapping
    public List<Cliente> index() {
        return service.buscarTodas();

    }

    @GetMapping("{id}")
    public Cliente getById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

}
