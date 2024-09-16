package br.com.fiap.AiConnectSolutions.lead;

import java.time.LocalDate;

import br.com.fiap.AiConnectSolutions.historicoInteresse.HistoricoInteresse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "lead")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lead {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min=3, max=200)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=10, max=11)
    private String telefone;

    @NotNull
    @PastOrPresent
    private LocalDate dtInteracao;

    @ManyToOne
    private HistoricoInteresse interesse;
}
