package com.carlos.Fintrack.service;

import com.carlos.Fintrack.model.Lancamento;
import com.carlos.Fintrack.repository.LancamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public Lancamento salvar(Lancamento lancamento) {
        if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
            throw new RuntimeException("Usuario é obrigatório");
        }
        return lancamentoRepository.save(lancamento);
    }

    public List<Lancamento> listarPorUsuario(Long usuarioId) {
        return lancamentoRepository.findByUsuarioId(usuarioId);
    }

    public void deletar(Long id) {
        lancamentoRepository.deleteById(id);
    }
}