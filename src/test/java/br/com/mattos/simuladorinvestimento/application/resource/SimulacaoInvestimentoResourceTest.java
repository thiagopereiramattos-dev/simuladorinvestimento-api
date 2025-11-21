package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.*;
import br.com.mattos.simuladorinvestimento.application.mapper.SimulacaoMapper;
import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import br.com.mattos.simuladorinvestimento.domain.service.SimulacaoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimulacaoInvestimentoResourceTest {

    @InjectMocks
    SimulacaoInvestimentoResource resource;

    @Mock
    SimulacaoService service;

    @Mock
    SimulacaoMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // =============================================================
    //  POST /simular-investimento
    // =============================================================
    @Test
    void deveSimularInvestimentoComSucesso() {
        // Request enviado no endpoint
        SimularInvestimentoRequestDTO request =
                new SimularInvestimentoRequestDTO(1L, 1000.0, 12, "CDB");

        // Entrada convertida pelo mapper
        SimulacaoEntrada entrada =
                new SimulacaoEntrada(1L, 1000.0, 12, "CDB");

        // Modelos de domínio simulados retornados pelo service
        Produto produto = new Produto(
                1L,
                "Teste Produto",
                "CDB",
                0.10,
                RiscoProduto.ALTO
        );

        ResultadoSimulacao resultado =
                new ResultadoSimulacao(1000.0, 1100.0, 0.10, 12);

        SimulacaoInvestimento simulacao =
                new SimulacaoInvestimento(1L, produto, resultado);

        // DTO final retornado pelo mapper
        SimularInvestimentoResponseDTO responseDTO =
                new SimularInvestimentoResponseDTO(
                        new ProdutoSimulacaoResponseDTO(
                                1L,
                                "Produto SImulacao",
                                "CDB",
                                7.57,
                                "Alto"
                        ),
                        new ResultadoSimulacaoResponseDTO(
                                "1000.0",
                                1100.0,
                                12
                        ),
                        "2025-01-10"
                );

        when(mapper.toDomain(request)).thenReturn(entrada);
        when(service.simular(entrada)).thenReturn(simulacao);
        when(mapper.toResponse(simulacao)).thenReturn(responseDTO);

        // Execução do endpoint
        SimularInvestimentoResponseDTO result = resource.simular(request);

        // Validações ajustadas
        assertNotNull(result);
        assertEquals("Produto SImulacao", result.produtoValidado().nome());
        assertEquals("CDB", result.produtoValidado().tipo());
        assertEquals("2025-01-10", result.dataSimulacao());

        verify(service).simular(entrada);
        verify(mapper).toResponse(simulacao);
    }

    // =============================================================
    //  GET /simulacoes
    // =============================================================
    @Test
    void deveListarSimulacoes() {
        Produto produto = new Produto(1L, "Produto Teste 2", "CDB", 0.1, RiscoProduto.BAIXO);
        ResultadoSimulacao resultado = new ResultadoSimulacao(1000.0, 1100.0, 0.1, 12);
        SimulacaoInvestimento s = new SimulacaoInvestimento(1L, produto, resultado);

        List<SimulacaoListResponseDTO> dtoList = List.of(
                new SimulacaoListResponseDTO(1L, 1L, "CDB", "1000,00", "1100,00", 12, "2025-01-15")
        );

        when(service.listarSimulacoes()).thenReturn(List.of(s));
        when(mapper.toListResponseList(List.of(s))).thenReturn(dtoList);

        List<SimulacaoListResponseDTO> result = resource.listar();

        assertEquals(1, result.size());
        assertEquals("CDB", result.get(0).produto());
    }

    // =============================================================
    //  GET /simulacoes/por-produto-dia
    // =============================================================
    @Test
    void deveListarSimulacoesPorProdutoEDia() {

        ResultadoConsultaSimulacaoPorDia r =
                new ResultadoConsultaSimulacaoPorDia("CDB", LocalDate.parse("2025-01-10"), 5L, 5000.0);

        SimulacaoPorProdutoDiaResponseDTO dto =
                new SimulacaoPorProdutoDiaResponseDTO("CDB", LocalDate.parse("2025-01-10"), 5L, "5000,00");

        when(service.listarSimulacoesPorProdutoEDia()).thenReturn(List.of(r));
        when(mapper.toResponsePorProdutoDia(r)).thenReturn(dto);

        List<SimulacaoPorProdutoDiaResponseDTO> result = resource.listarPorProdutoEDia();

        assertEquals(1, result.size());
        assertEquals("CDB", result.get(0).produto());
        assertEquals(5L, result.get(0).quantidadeSimulacoes());
    }

    // =============================================================
    //  GET /investimentos/{clienteId}
    // =============================================================
    @Test
    void deveListarSimulacoesPorCliente() {
        Produto produto = new Produto(1L, "Teste2", "CDB", 9.10, RiscoProduto.ALTO);
        ResultadoSimulacao resultado = new ResultadoSimulacao(2000.0, 2200.0, 0.10, 12);

        SimulacaoInvestimento s = new SimulacaoInvestimento(1L, produto, resultado);

        SimulacaoInvestimentoClienteDTO dto =
                new SimulacaoInvestimentoClienteDTO(1L, "CDB", "2000,00", 0.10, "2025-01-10");

        when(service.listarSimulacoesPorCliente(1L)).thenReturn(List.of(s));
        when(mapper.toResponseSimulacaoClienteList(List.of(s))).thenReturn(List.of(dto));

        List<SimulacaoInvestimentoClienteDTO> result = resource.listarSimulacoesPorIdCliente(1L);

        assertEquals(1, result.size());
        assertEquals("CDB", result.get(0).tipo());
        assertEquals("2000,00", result.get(0).valor());
    }
}
