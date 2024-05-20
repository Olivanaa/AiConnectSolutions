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

import br.com.fiap.AiConnectSolutions.model.Endereco;
import br.com.fiap.AiConnectSolutions.repository.EnderecoRepository;
import br.com.fiap.AiConnectSolutions.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("endereco")
@Tag(name = "Endereço")
public class EnderecoController {

    @Autowired
    EnderecoRepository repository;

    // public EnderecoController(EnderecoService service) {
    //     this.service = service;
    // }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cadastrar endereço", description = "Cria um novo cadastro de endereço com dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição", content = @Content)
    })
    public Endereco create(@RequestBody @Valid Endereco endereco) { 
        return repository.save(endereco);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os endereços cadastrados", 
        description = "Retorna um array com todos os endereços em formato de objeto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços foi retornada com sucesso!"),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content),
           
    })
    public List<Endereco> index() {
        return repository.findAll();

    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar o endereço com o parametro id", 
        description = "Retornar os detalhes do endereço com o id informado como parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do endereço retornados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Não existe dados do endereço com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public ResponseEntity<Endereco> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Alterar o endereço com o parametro id", 
        description = "Altera os dados do endereço especificado pelo id, utilizando as informações enviadas no corpo da requisição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public Endereco update(@PathVariable Long id, @RequestBody Endereco endereco) {
        verificarEndereco(id);
        endereco.setId(id);
        return repository.save(endereco);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
        summary = "Deletar o endereço com o parametro id", 
        description = "Apaga os dados do endereço com o id especificado no parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do endereço foram apagados com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não existe dados do endereço com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public void destroy(@PathVariable Long id){
        verificarEndereco(id);
        repository.deleteById(id);

    }

    private void verificarEndereco(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Não existe endereço com o id informado"));
    }
}
