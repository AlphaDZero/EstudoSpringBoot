package com.brasileiras.brasileiras_ecommerce.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.brasileiras.brasileiras_ecommerce.model.Produto;
import com.brasileiras.brasileiras_ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto p) {
        return produtoRepository.save(p);
    }

    public Produto buscar(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    public void ajustarEstoque(Long id, int novoEstoque) {
        Produto p = buscar(id);
        p.setEstoque(novoEstoque);
        produtoRepository.save(p);
    }

    public void debitarEstoque(Long id, int qtd) {
        Produto p = buscar(id);
        if (p.getEstoque() < qtd) {
            throw new RuntimeException("Estoque insuficiente");
        }
        p.setEstoque(p.getEstoque() - qtd);
        produtoRepository.save(p);
    }
}

