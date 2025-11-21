package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import br.com.mattos.simuladorinvestimento.domain.repository.ClienteRepository;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.domain.repository.SimulacaoInvestimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulacaoServiceTest {

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    SimulacaoInvestimentoRepository simulacaoInvestimentoRepository;

    @InjectMocks
    SimulacaoService simulacaoService;

    private SimulacaoEntrada entrada;
    private Produto produto;
    private Cliente cliente;

    @BeforeEach
    void setup() {
        entrada = new SimulacaoEntrada(1L, 1000.0, 12, "RendaFixa");
        produto = new Produto(1L, "RendaFixa", "RendaFixa", 0.05, RiscoProduto.BAIXO);
        cliente = new Cliente(1L, "Thiago", "12345678900", "thiago@email.com", "CONSERVADOR");
    }

    @Test
    void deveSimularInvestimentoComSucesso() {
        when(produtoRepository.buscarPorTipo("RendaFixa")).thenReturn(Optional.of(produto));
        when(clienteRepository.buscarPorId(1L)).thenReturn(Optional.of(cliente));

        SimulacaoInvestimento resultado = simulacaoService.simular(entrada);

        assertNotNull(resultado);
        assertEquals(entrada.clienteId(), resultado.clientId());
        assertEquals(produto, resultado.produto());
        assertEquals(1050.0, resultado.resultado().valorFinal()); // 1000 * 1.05

        verify(simulacaoInvestimentoRepository).salvar(resultado);
    }

    @Test
    void deveLancarProdutoNaoEncontradoException() {
        when(produtoRepository.buscarPorTipo("RendaFixa")).thenReturn(Optional.empty());

        ProdutoNaoEncontradoException exception = assertThrows(ProdutoNaoEncontradoException.class, () ->
                simulacaoService.simular(entrada)
        );

        assertTrue(exception.getMessage().contains("RendaFixa"));
        verify(simulacaoInvestimentoRepository, never()).salvar(any());
    }

    @Test
    void deveLancarClienteNaoEncontradoException() {
        when(produtoRepository.buscarPorTipo("RendaFixa")).thenReturn(Optional.of(produto));
        when(clienteRepository.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () ->
                simulacaoService.simular(entrada)
        );

        verify(simulacaoInvestimentoRepository, never()).salvar(any());
    }

    @Test
    void deveListarTodasSimulacoes() {
        SimulacaoInvestimento simulacao1 = new SimulacaoInvestimento(1L, produto, new ResultadoSimulacao(1000.0, 1050.0, 0.05, 12));
        SimulacaoInvestimento simulacao2 = new SimulacaoInvestimento(2L, produto, new ResultadoSimulacao(2000.0, 2100.0, 0.05, 12));

        when(simulacaoInvestimentoRepository.listar()).thenReturn(List.of(simulacao1, simulacao2));

        List<SimulacaoInvestimento> resultados = simulacaoService.listarSimulacoes();

        assertEquals(2, resultados.size());
        assertTrue(resultados.contains(simulacao1));
        assertTrue(resultados.contains(simulacao2));
    }

    @Test
    void deveListarSimulacoesPorCliente() {
        SimulacaoInvestimento simulacao = new SimulacaoInvestimento(1L, produto, new ResultadoSimulacao(1000.0, 1050.0, 0.05, 12));
        when(simulacaoInvestimentoRepository.listarPorCliente(1L)).thenReturn(List.of(simulacao));

        List<SimulacaoInvestimento> resultados = simulacaoService.listarSimulacoesPorCliente(1L);

        assertEquals(1, resultados.size());
        assertEquals(1L, resultados.get(0).clientId());
    }

    @Test
    void deveListarSimulacoesPorProdutoEDia() {
        ResultadoConsultaSimulacaoPorDia resultadoDia = new ResultadoConsultaSimulacaoPorDia("RendaFixa", LocalDate.now(), 1L, 1050.0);
        when(simulacaoInvestimentoRepository.listarPorProdutoEDia()).thenReturn(List.of(resultadoDia));

        List<ResultadoConsultaSimulacaoPorDia> resultados = simulacaoService.listarSimulacoesPorProdutoEDia();

        assertEquals(1, resultados.size());
        assertEquals("RendaFixa", resultados.get(0).produto());
        assertEquals(1, resultados.get(0).quantidadeSimulacoes());
        assertEquals(1050.0, resultados.get(0).mediaValorFinal());
    }
}
