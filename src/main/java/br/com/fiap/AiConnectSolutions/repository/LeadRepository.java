package br.com.fiap.AiConnectSolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.AiConnectSolutions.model.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long>{
    
}
