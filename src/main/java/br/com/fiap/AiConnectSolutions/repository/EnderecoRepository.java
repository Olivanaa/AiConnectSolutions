package br.com.fiap.AiConnectSolutions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.AiConnectSolutions.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

    List<Endereco> findByCep(String cep);
} 
