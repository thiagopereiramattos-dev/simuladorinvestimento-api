package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.PeriodoTelemetriaDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.ServicoTelemetriaDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.TelemetriaResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.TelemetriaResponseMapper;
import br.com.mattos.simuladorinvestimento.domain.exception.DataInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import br.com.mattos.simuladorinvestimento.domain.service.TelemetriaService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@QuarkusTest
class TelemetriaResourceTest {

    @InjectMock
    TelemetriaService telemetriaService;

    @InjectMock
    TelemetriaResponseMapper mapper;

    @Test
    void deveRetornarTelemetriaComSucesso() {

        String inicio = "2024-01-01";
        String fim = "2024-01-31";

        List<Telemetria> telemetrias = List.of(
                new Telemetria("ServicoA", 10, 200,
                        LocalDate.parse(inicio), LocalDate.parse(fim))
        );

        TelemetriaResponseDTO resposta = new TelemetriaResponseDTO(
                List.of(new ServicoTelemetriaDTO("ServicoA", 10, 200)),
                new PeriodoTelemetriaDTO(inicio, fim)
        );

        when(telemetriaService.consultarTelemetriaServicos(inicio, fim)).thenReturn(telemetrias);
        when(mapper.toResponse(telemetrias, inicio, fim)).thenReturn(resposta);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/telemetria?inicio=2024-01-01&fim=2024-01-31")
                .then()
                .statusCode(200)
                .body("servicos[0].nome", equalTo("ServicoA"))
                .body("servicos[0].quantidadeChamadas", equalTo(10))
                .body("servicos[0].mediaTempoRespostaMs", equalTo(200))
                .body("periodo.inicio", equalTo("2024-01-01"))
                .body("periodo.fim", equalTo("2024-01-31"));

        verify(telemetriaService).consultarTelemetriaServicos(inicio, fim);
        verify(mapper).toResponse(telemetrias, inicio, fim);
    }

    @Test
    void deveRetornar400QuandoDataInvalida() {

        // SERVICE lança DataInvalidoException
        when(telemetriaService.consultarTelemetriaServicos("abc", "2024-01-31"))
                .thenThrow(new DataInvalidoException());

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/telemetria?inicio=abc&fim=2024-01-31")
                .then()
                .statusCode(400);

        verify(telemetriaService).consultarTelemetriaServicos("abc", "2024-01-31");
    }
}
