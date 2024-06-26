package com.leo.teste.api.repository;

import com.leo.teste.api.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


}