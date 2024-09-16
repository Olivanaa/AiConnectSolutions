package br.com.fiap.AiConnectSolutions.endereco;


import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import br.com.fiap.AiConnectSolutions.endereco.dto.EnderecoFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@RestController
@RequestMapping("endereco")
@Tag(name = "Endereço")
public class EnderecoController {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    EnderecoService service;

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
        return service.buscarTodas();

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
        try {
            Endereco endereco = service.buscarPorId(id);
            return ResponseEntity.ok(endereco);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
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
    public Endereco update(@PathVariable Long id, @RequestBody EnderecoFormRequest endereco) {

        return service.atualizar(id, endereco);
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
        service.deletar(id);

    }

}
