package br.com.fiap.AiConnectSolutions.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.AiConnectSolutions.model.Cliente;
import br.com.fiap.AiConnectSolutions.repository.ClienteRepository;
import br.com.fiap.AiConnectSolutions.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    // public ClienteController(ClienteService service) {
    //     this.service = service;
    // }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cadastrar cliente", description = "Cria um novo cadastro de cliente com dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição", content = @Content)
    })
    public Cliente create(@RequestBody @Valid Cliente cliente) {
        return repository.save(cliente);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os clientes cadastrados", 
        description = "Retorna um array com todos os clientes em formato de objeto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes foi retornada com sucesso!"),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content),
           
    })
    public List<Cliente> index() {
        return repository.findAll();

    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar o cliente com o parametro id", 
        description = "Retornar os detalhes do cliente com o id informado como parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do cliente retornados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Não existe dados do cliente com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public ResponseEntity<Cliente> show(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Alterar o cliente com o parametro id", 
        description = "Altera os dados do cliente especificado pelo id, utilizando as informações enviadas no corpo da requisição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        verificarCliente(id);
        cliente.setId(id);
        return repository.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
        summary = "Deletar o cliente com o parametro id", 
        description = "Apaga os dados do cliente com o id especificado no parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do cliente foram apagados com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não existe dados do cliente com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public void destroy(@PathVariable Long id) {
        verificarCliente(id);
        repository.deleteById(id);


    }

    private void verificarCliente(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Não existe cliente com o id informado"));
    }

}
