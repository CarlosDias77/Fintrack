package com.carlos.Fintrack.controller;

import com.carlos.Fintrack.dto.ResumoFinanceiro;
import com.carlos.Fintrack.model.Lancamento;
import com.carlos.Fintrack.service.LancamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public Lancamento criar(@RequestBody Lancamento lancamento) {
        return lancamentoService.salvar(lancamento);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Lancamento> listarPorUsuario(@PathVariable Long usuarioId) {
        return lancamentoService.listarPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        lancamentoService.deletar(id);
    }

    @GetMapping("/resumo/{usuarioId}")
    public ResumoFinanceiro resumo(@PathVariable Long usuarioId) {
        return lancamentoService.gerarResumo(usuarioId);
    }
}
