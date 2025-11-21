package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.DataInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import br.com.mattos.simuladorinvestimento.domain.repository.TelemetriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelemetriaServiceTest {

    @Mock
    TelemetriaRepository telemetriaRepository;

    @InjectMocks
    TelemetriaService telemetriaService;

    private Telemetria t1;
    private Telemetria t2;

    @BeforeEach
    void setup() {
        t1 = new Telemetria("metodo1", 10, 100, LocalDate.of(2025, 11, 22), LocalDate.of(2025, 11, 22));
        t2 = new Telemetria("metodo2", 5, 200, LocalDate.of(2025, 11, 23), LocalDate.of(2025, 11, 23));
    }

    @Test
    void deveSalvarTelemetria() {
        LocalDate data = LocalDate.now();
        telemetriaService.salvar("fullMethod", "method", 123, data);
        verify(telemetriaRepository).salvarTelemetria("fullMethod", "method", 123, data);
    }

    @Test
    void deveRetornarListaQuandoDatasValidas() {
        when(telemetriaRepository.obterTelemetriaServicos(LocalDate.of(2025, 11, 22),
                LocalDate.of(2025, 11, 23))).thenReturn(List.of(t1, t2));

        List<Telemetria> resultado = telemetriaService.consultarTelemetriaServicos("2025-11-22", "2025-11-23");

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(t1));
        assertTrue(resultado.contains(t2));
        verify(telemetriaRepository).obterTelemetriaServicos(LocalDate.of(2025, 11, 22),
                LocalDate.of(2025, 11, 23));
    }

    @Test
    void deveUsarDatasPadraoQuandoParametrosNull() {
        LocalDate inicioEsperado = LocalDate.now().withDayOfMonth(1);
        LocalDate fimEsperado = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        when(telemetriaRepository.obterTelemetriaServicos(inicioEsperado, fimEsperado)).thenReturn(List.of(t1));

        List<Telemetria> resultado = telemetriaService.consultarTelemetriaServicos(null, null);

        assertEquals(1, resultado.size());
        verify(telemetriaRepository).obterTelemetriaServicos(inicioEsperado, fimEsperado);
    }

    @Test
    void deveLancarExcecaoQuandoDataInvalida() {
        assertThrows(DataInvalidoException.class, () ->
                telemetriaService.consultarTelemetriaServicos("2025-11-99", "2025-11-22")
        );
    }

    @Test
    void deveLancarRuntimeExceptionQuandoRepositorioFalha() {
        when(telemetriaRepository.obterTelemetriaServicos(any(), any())).thenThrow(new RuntimeException("erro DB"));

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                telemetriaService.consultarTelemetriaServicos("2025-11-22", "2025-11-23")
        );

        assertTrue(ex.getMessage().contains("Não foi possível consultar a telemetria"));
    }
}
