package br.com.fiap.AiConnectSolutions.feedback;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("feedback")
@Tag(name = "Feedback")
public class FeedbackController {

    FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cadastrar feedback", description = "Cria um novo cadastro de feedback com dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Feedback cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição", content = @Content)
    })
    public Feedback create(@RequestBody @Valid Feedback feedback) {
        return service.criar(feedback);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os feedbacks cadastrados", 
        description = "Retorna um array com todos os feedbacks em formato de objeto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de feedbacks foi retornada com sucesso!"),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content),
           
    })
    public List<Feedback> index() {
        return service.buscarTodas();

    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar o feedback com o parametro id", 
        description = "Retornar os detalhes do feedback com o id informado como parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do feedback retornados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Não existe dados do feedback com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public Feedback getById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Alterar o feedback com o parametro id", 
        description = "Altera os dados do feedback especificado pelo id, utilizando as informações enviadas no corpo da requisição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Feedback cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos. Verifique o corpo da requisição.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public Feedback update(@PathVariable Long id, @RequestBody Feedback feedback) {
        return service.atualizar(id, feedback);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
        summary = "Deletar o feedback com o parametro id", 
        description = "Apaga os dados do feedback com o id especificado no parâmetro de path."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dados do feedback foram apagados com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não existe dados do feedback com o id informado.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido. É necessário autentificação.", content = @Content)
    })
    public void destroy(@PathVariable Long id) {
        service.deletar(id);

    }
}
