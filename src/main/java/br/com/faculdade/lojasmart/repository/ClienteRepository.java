package br.com.faculdade.lojasmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.faculdade.lojasmart.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}