package com.brasileiras.brasileiras_ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brasileiras.brasileiras_ecommerce.model.Entrega;
import com.brasileiras.brasileiras_ecommerce.service.EntregaService;

@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.buscar(id));
    }

    @PutMapping("/{id}/tentativa")
    public ResponseEntity<Entrega> registrarTentativa(
            @PathVariable Long id,
            @RequestParam boolean sucesso) {
        return ResponseEntity.ok(entregaService.registrarTentativa(id, sucesso));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Entrega> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.finalizarEntrega(id));
    }
}

