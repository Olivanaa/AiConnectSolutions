package br.com.fiap.AiConnectSolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.AiConnectSolutions.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

    
} 
