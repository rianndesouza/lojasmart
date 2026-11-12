package br.com.faculdade.lojasmart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.faculdade.lojasmart.model.Cliente;
import br.com.faculdade.lojasmart.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        if (cliente.getId() != null || cliente.getNome() == null || cliente.getNome().isBlank()
                || cliente.getClienteDesde() == null) {
            return ResponseEntity.badRequest().build();
        }
        Cliente salvo = repository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        var resultado = repository.findById(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resultado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
