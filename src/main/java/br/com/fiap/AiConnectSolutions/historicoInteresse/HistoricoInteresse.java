package br.com.fiap.AiConnectSolutions.historicoInteresse;

import java.time.LocalDate;

import jakarta.persistence.*;
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
@Table(name = "historico_interesse")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoInteresse {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min=5, max=500)
    private String interesse;

    @NotNull
    @PastOrPresent
    private LocalDate dtInteracao;

    
}