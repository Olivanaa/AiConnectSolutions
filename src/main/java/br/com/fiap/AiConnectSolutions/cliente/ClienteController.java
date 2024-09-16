package br.com.fiap.AiConnectSolutions.cliente;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import br.com.fiap.AiConnectSolutions.cliente.dto.ClienteFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @Autowired
    ClienteService clienteService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cadastrar cliente", description = "Cria um novo cadastro de cliente com dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição", content = @Content)
    })
    public ResponseEntity<Cliente> create(@RequestBody @Valid ClienteFormRequest clienteFormRequest, UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.criar(clienteFormRequest);
        var uri = uriBuilder.path("cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
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
        return clienteService.buscarTodas();

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
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
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
    public Cliente update(@PathVariable Long id, @RequestBody ClienteFormRequest cliente) {
        return clienteService.atualizar(id, cliente);
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
        clienteService.remover(id);


    }


}
