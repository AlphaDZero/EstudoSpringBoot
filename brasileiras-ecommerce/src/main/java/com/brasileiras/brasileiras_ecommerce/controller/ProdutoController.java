package com.brasileiras.brasileiras_ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brasileiras.brasileiras_ecommerce.model.Produto;
import com.brasileiras.brasileiras_ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto p) {
        return produtoService.salvar(p);
    }

    @PutMapping("/{id}/estoque")
    public ResponseEntity<Void> ajustarEstoque(
            @PathVariable Long id,
            @RequestParam int quantidade) {
        produtoService.ajustarEstoque(id, quantidade);
        return ResponseEntity.ok().build();
    }
}

