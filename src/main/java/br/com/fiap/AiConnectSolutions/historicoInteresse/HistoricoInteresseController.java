package br.com.fiap.AiConnectSolutions.historicoInteresse;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import br.com.fiap.AiConnectSolutions.historicoInteresse.dto.HistoricoInteresseFormRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("historico")
@Tag(name = "Histórico de interesses")
public class HistoricoInteresseController {

    HistoricoInteresseService service;

    public HistoricoInteresseController(HistoricoInteresseService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cadastrar interesse", description = "Cria um novo cadastro de interesse com dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Interesse cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição", content = @Content)
    })
    public HistoricoInteresse create(@RequestBody @Valid HistoricoInteresse historico) {
        return service.criar(historico);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os interesses cadastrados", 
        description = "Retorna um array com todos os interesses em formato de objeto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de interesses foi retornada com sucesso!"),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content),
           
    })
    public List<HistoricoInteresse> index() {
        return service.buscarTodas();

    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar o interesse com o parametro id", 
        description = "Retornar os detalhes do interesse com o id informado como parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do interesse retornados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Não existe dados do interesse com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public HistoricoInteresse getById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Alterar o interesse com o parametro id", 
        description = "Altera os dados do interesse especificado pelo id, utilizando as informações enviadas no corpo da requisição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Interesse cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public HistoricoInteresse update(@PathVariable Long id, @RequestBody HistoricoInteresseFormRequest historicoInteresse) {

        return service.atualizar(id, historicoInteresse);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
        summary = "Deletar o interesse com o parametro id", 
        description = "Apaga os dados do interesse com o id especificado no parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do interesse foram apagados com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não existe dados do interesse com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public void destroy(@PathVariable Long id) {
        service.deletar(id);

    }
}
