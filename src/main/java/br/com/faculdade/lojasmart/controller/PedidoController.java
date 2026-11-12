package br.com.faculdade.lojasmart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.faculdade.lojasmart.model.Pedido;
import br.com.faculdade.lojasmart.repository.ClienteRepository;
import br.com.faculdade.lojasmart.repository.PedidoRepository;
import br.com.faculdade.lojasmart.repository.ProdutoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidos;
    private final ClienteRepository clientes;
    private final ProdutoRepository produtos;

    public PedidoController(
            PedidoRepository pedidos,
            ClienteRepository clientes,
            ProdutoRepository produtos) {
        this.pedidos = pedidos;
        this.clientes = clientes;
        this.produtos = produtos;
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido) {
        if (pedido.getId() != null || pedido.getClienteId() == null || pedido.getProdutoId() == null
                || pedido.getQuantidade() == null || pedido.getQuantidade() < 1
                || !clientes.existsById(pedido.getClienteId())
                || !produtos.existsById(pedido.getProdutoId())) {
            return ResponseEntity.badRequest().build();
        }

        Pedido salvo = pedidos.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        var resultado = pedidos.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!pedidos.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidos.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
