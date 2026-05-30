package com.carlos.Fintrack.repository;

import com.carlos.Fintrack.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByUsuarioId(Long usuarioId);
}
