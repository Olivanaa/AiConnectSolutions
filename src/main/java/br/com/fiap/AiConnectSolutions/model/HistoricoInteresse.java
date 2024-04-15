package br.com.fiap.AiConnectSolutions.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class HistoricoInteresse {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min=5, max=500)
    private String interesse;

    @NotBlank
    @PastOrPresent
    private LocalDate dtHistorico;

    // private Lead lead;

    // private Feedback feedback;


    
}