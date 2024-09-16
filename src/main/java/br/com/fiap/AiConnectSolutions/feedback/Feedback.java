package br.com.fiap.AiConnectSolutions.feedback;

import java.time.LocalDate;

import br.com.fiap.AiConnectSolutions.cliente.Cliente;
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
@Table(name = "feedback")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String avaliacao;

    @Size(min=5, max=300)
    private String comentario;

    @NotNull
    @PastOrPresent
    private LocalDate dtFeedBack;

    @ManyToOne
    private Cliente cliente;

    
}

