package com.brasileiras.brasileiras_ecommerce.service;

import org.springframework.stereotype.Service;
import com.brasileiras.brasileiras_ecommerce.model.Cliente;
import com.brasileiras.brasileiras_ecommerce.model.Endereco;
import com.brasileiras.brasileiras_ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente c) {
        return clienteRepository.save(c);
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    public Endereco adicionarEndereco(Long clienteId, Endereco endereco) {
        Cliente cliente = buscar(clienteId);
        endereco.setCliente(cliente);
        cliente.getEnderecos().add(endereco);
        clienteRepository.save(cliente);
        return endereco;
    }
}

