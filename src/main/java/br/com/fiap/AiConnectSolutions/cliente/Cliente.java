package br.com.fiap.AiConnectSolutions.cliente;
import java.time.LocalDate;

import br.com.fiap.AiConnectSolutions.endereco.Endereco;
import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "cliente")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    private Endereco endereco;

    @ManyToOne
    private HistoricoInteresse interesse;
    
}
