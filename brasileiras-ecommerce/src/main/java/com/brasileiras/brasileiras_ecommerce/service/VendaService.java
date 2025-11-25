package com.brasileiras.brasileiras_ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.brasileiras.brasileiras_ecommerce.model.ItemVenda;
import com.brasileiras.brasileiras_ecommerce.model.Produto;
import com.brasileiras.brasileiras_ecommerce.model.Venda;
import com.brasileiras.brasileiras_ecommerce.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoService produtoService;
    private final EntregaService entregaService;

    public VendaService(VendaRepository vendaRepository, ProdutoService produtoService, EntregaService entregaService) {
        this.vendaRepository = vendaRepository;
        this.produtoService = produtoService;
        this.entregaService = entregaService;
    }

    @Transactional
    public Venda criarVenda(Venda venda) {
        BigDecimal total = BigDecimal.ZERO;

        for (ItemVenda item : venda.getItens()) {
            Produto p = produtoService.buscar(item.getProduto().getId());
            produtoService.debitarEstoque(p.getId(), item.getQuantidade());

            item.setPrecoUnitario(p.getValorVenda());
            total = total.add(p.getValorVenda().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }

        venda.setValorTotal(total);
        venda.setData(LocalDateTime.now());

        Venda salva = vendaRepository.save(venda);

        entregaService.criarEntregaParaVenda(salva);

        return salva;
    }

    public Venda buscar(Long id) {
        return vendaRepository.findById(id).orElseThrow();
    }
}
