package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.service.ClienteService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class ClienteResourceTest {

    @InjectMock
    ClienteService clienteService;

    @Test
    void deveRetornarPerfilDeRiscoComSucesso() {

        // Arrange
        ClientePerfilRisco perfil =
                new ClientePerfilRisco(1L, "Conservador", 80, "Perfil seguro");

        when(clienteService.consultarPerfilRiscoCliente(1L)).thenReturn(perfil);

        // Act + Assert
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/perfil-risco/1")
                .then()
                .statusCode(200)
                .body("clienteId", equalTo(1))              // campos do DTO correto
                .body("nomePerfil", equalTo("Conservador"))
                .body("pontuacao", equalTo(80))
                .body("descricao", equalTo("Perfil seguro"));

        verify(clienteService).consultarPerfilRiscoCliente(1L);
    }

    @Test
    void deveRetornar400QuandoPerfilNaoEncontrado() {

        // Arrange — usando exceção de domínio (correto)
        when(clienteService.consultarPerfilRiscoCliente(2L))
                .thenThrow(new ClienteNaoEncontradoException("2"));

        // Act + Assert — DomainExceptionMapper → 400
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/perfil-risco/2")
                .then()
                .statusCode(400)
                .body("status", equalTo(400))
                .body("error", equalTo("Bad Request"));

        verify(clienteService).consultarPerfilRiscoCliente(2L);
    }
}
