package com.brasileiras.brasileiras_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brasileiras.brasileiras_ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}

