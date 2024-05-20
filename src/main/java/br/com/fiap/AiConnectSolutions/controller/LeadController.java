package br.com.fiap.AiConnectSolutions.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.AiConnectSolutions.model.Lead;
import br.com.fiap.AiConnectSolutions.service.LeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("lead")
@Tag(name = "Lead")
public class LeadController {

    LeadService service;

    public LeadController(LeadService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cadastrar lead", description = "Cria um novo cadastro de lead com dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lead cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição", content = @Content)
    })
    public Lead create(@RequestBody @Valid Lead feedback) {
        return service.criar(feedback);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos as leads cadastradas", 
        description = "Retorna um array com todas as leads em formato de objeto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de leads foi retornada com sucesso!"),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content), 
           
    })
    public List<Lead> index() {
        return service.buscarTodas();

    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar a lead com o parametro id", 
        description = "Retornar os detalhes da lead com o id informado como parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados da lead retornados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Não existe dados da lead com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public Lead getById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Alterar a lead com o parametro id", 
        description = "Altera os dados da lead especificado pelo id, utilizando as informações enviadas no corpo da requisição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lead cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public Lead update(@PathVariable Long id, @RequestBody Lead lead) {

        return service.atualizar(id, lead);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
        summary = "Deletar a lead com o parametro id", 
        description = "Apaga os dados da lead com o id especificado no parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados da lead foram apagados com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não existe dados da lead com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public void destroy(@PathVariable Long id) {
        service.deletar(id);
    }
}
