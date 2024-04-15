package br.com.fiap.AiConnectSolutions.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min=3, max= 200)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=10, max=11)
    private String telefone;

    @NotNull
    @Past
    private LocalDate dtaNascimento;

    @NotNull
    @PastOrPresent
    private LocalDate dtaCadastro;

    @NotBlank
    @Size(min=3, max=50)
    private String segmentoMercado;

    @NotNull
    @PastOrPresent
    private LocalDate dtaUltimaInteracao; 

    @NotBlank
    @Size(min=3, max=500)
    private String interesses;
    
    // private HistoricoInteresse historico;

    // @OneToMany
    // private List<Endereco> enderecos;
    
}
