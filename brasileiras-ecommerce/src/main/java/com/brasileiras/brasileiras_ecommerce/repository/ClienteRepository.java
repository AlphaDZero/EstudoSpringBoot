package com.brasileiras.brasileiras_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brasileiras.brasileiras_ecommerce.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
