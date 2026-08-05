package com.carlos.Fintrack;

import com.carlos.Fintrack.dto.ResumoFinanceiro;
import com.carlos.Fintrack.model.Lancamento;
import com.carlos.Fintrack.model.TipoLancamento;
import com.carlos.Fintrack.model.Usuario;
import com.carlos.Fintrack.repository.LancamentoRepository;
import com.carlos.Fintrack.service.LancamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LancamentoServiceTest {

    @Mock
    private LancamentoRepository lancamentoRepository;

    @InjectMocks
    private LancamentoService lancamentoService;

    @Test
    void deveSalvarLancamentoQuandoUsuarioValido() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Lancamento lancamento = new Lancamento();
        lancamento.setUsuario(usuario);

        when(lancamentoRepository.save(lancamento)).thenReturn(lancamento);

        Lancamento resultado = lancamentoService.salvar(lancamento);

        assertNotNull(resultado);
        verify(lancamentoRepository, times(1)).save(lancamento);
    }
    @Test
    void deveLancarExcecaoQuandoUsuarioNulo() {
        Lancamento lancamento = new Lancamento();
        lancamento.setUsuario(null);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> lancamentoService.salvar(lancamento)
        );

        assertEquals("Usuario é obrigatório", exception.getMessage());
    }
    @Test
    void deveLancarExcecaoQuandoIdNulo() {
        Usuario usuario = new Usuario();

        Lancamento lancamento = new Lancamento();
        lancamento.setUsuario(usuario);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> lancamentoService.salvar(lancamento)
        );

        assertEquals("Usuario é obrigatório", exception.getMessage());
    }
    @Test
    void deveRetornarLancamentosPorUsuario() {
        Lancamento lancamento = new Lancamento();

        List<Lancamento> lista = new ArrayList<>();
        lista.add(lancamento);

        when(lancamentoRepository.findByUsuarioId(1L)).thenReturn(lista);

        List<Lancamento> resultado = lancamentoService.listarPorUsuario(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(lancamentoRepository, times(1)).findByUsuarioId(1L);
    }
    @Test
    void deveDeletarUsuariosPorId() {
        when(lancamentoRepository.existsById(1L)).thenReturn(true);
        lancamentoService.deletar(1L);
        verify(lancamentoRepository, times(1)).deleteById(1l);
    }
    @Test
    void deveResumirLancamentosDeReceitaEDespesa() {
        Lancamento lancamentoDESPESA = new Lancamento();
        lancamentoDESPESA.setTipo(TipoLancamento.DESPESA);
        lancamentoDESPESA.setValor(100.0);
        Lancamento lancamentoRECEITA = new Lancamento();
        lancamentoRECEITA.setTipo(TipoLancamento.RECEITA);
        lancamentoRECEITA.setValor(250.0);

        List<Lancamento> lista = new ArrayList<>();
        lista.add(lancamentoDESPESA);
        lista.add(lancamentoRECEITA);

        when(lancamentoRepository.findByUsuarioId(1L)).thenReturn(lista);

        ResumoFinanceiro resultado = lancamentoService.gerarResumo(1l);

        assertEquals(100.0, resultado.totalDespesas());
        assertEquals(250.0, resultado.totalReceitas());
        assertEquals(150.0, resultado.saldo());
    }
}