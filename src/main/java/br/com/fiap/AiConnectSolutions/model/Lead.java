package br.com.fiap.AiConnectSolutions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
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

    @NotBlank
    private String interesse;

    // @ManyToOne
    // private Cliente cliente;

    // private Feedback feedback;
}
