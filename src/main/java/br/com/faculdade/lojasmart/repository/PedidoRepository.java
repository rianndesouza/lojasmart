package br.com.faculdade.lojasmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.faculdade.lojasmart.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}