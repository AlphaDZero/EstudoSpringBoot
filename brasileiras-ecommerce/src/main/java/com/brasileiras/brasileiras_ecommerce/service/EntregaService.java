package com.brasileiras.brasileiras_ecommerce.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.brasileiras.brasileiras_ecommerce.model.Entrega;
import com.brasileiras.brasileiras_ecommerce.model.Venda;
import com.brasileiras.brasileiras_ecommerce.repository.EntregaRepository;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public Entrega criarEntregaParaVenda(Venda venda) {
        Entrega e = new Entrega();
        e.setVenda(venda);
        e.setStatus("PENDENTE");
        e.setTentativas(0);
        e.setUltimaAtualizacao(LocalDateTime.now());
        return entregaRepository.save(e);
    }

    public Entrega buscar(Long id) {
        return entregaRepository.findById(id).orElseThrow();
    }

    public Entrega registrarTentativa(Long id, boolean sucesso) {
        Entrega e = buscar(id);

        if (sucesso) {
            e.setStatus("ENTREGUE");
        } else {
            e.setTentativas(e.getTentativas() + 1);
            if (e.getTentativas() >= 3) {
                e.setStatus("DISPONIVEL_RETIRADA");
            } else {
                e.setStatus("RETORNO");
            }
        }

        e.setUltimaAtualizacao(LocalDateTime.now());
        return entregaRepository.save(e);
    }

    public Entrega finalizarEntrega(Long id) {
        Entrega e = buscar(id);
        e.setStatus("ENTREGUE");
        e.setUltimaAtualizacao(LocalDateTime.now());
        return entregaRepository.save(e);
    }
}

