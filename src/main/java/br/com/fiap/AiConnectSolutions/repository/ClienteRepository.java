package br.com.fiap.AiConnectSolutions.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.AiConnectSolutions.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    
}
