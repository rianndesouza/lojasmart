package br.com.faculdade.lojasmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.faculdade.lojasmart.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}