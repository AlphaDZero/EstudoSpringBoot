package com.brasileiras.brasileiras_ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brasileiras.brasileiras_ecommerce.model.Venda;
import com.brasileiras.brasileiras_ecommerce.service.VendaService;

@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Venda> criar(@RequestBody Venda v) {
        return ResponseEntity.ok(vendaService.criarVenda(v));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscar(id));
    }
}

