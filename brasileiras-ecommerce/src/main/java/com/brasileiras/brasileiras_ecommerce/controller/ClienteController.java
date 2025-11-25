package com.brasileiras.brasileiras_ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brasileiras.brasileiras_ecommerce.model.Cliente;
import com.brasileiras.brasileiras_ecommerce.model.Endereco;
import com.brasileiras.brasileiras_ecommerce.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.salvar(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscar(id));
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<Endereco> adicionarEndereco(
            @PathVariable Long id,
            @RequestBody Endereco endereco) {
        return ResponseEntity.ok(clienteService.adicionarEndereco(id, endereco));
    }
}
