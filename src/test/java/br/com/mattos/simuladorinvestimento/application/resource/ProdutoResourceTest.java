package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.ProdutoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.ProdutoMapper;
import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.exception.PerfilInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.service.ProdutoService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class ProdutoResourceTest {

    @InjectMock
    ProdutoService produtoService;

    @InjectMock
    ProdutoMapper mapper;

    // =========================================================================
    // TESTE: listar todos produtos - SUCESSO (200)
    // =========================================================================
    @Test
    void deveListarProdutosComSucesso() {

        List<Produto> produtos = List.of(
                new Produto(1L, "CDB", "Renda Fixa", 10.5, RiscoProduto.BAIXO),
                new Produto(2L, "Tesouro Selic", "Renda Fixa", 12.0, RiscoProduto.BAIXO)
        );

        List<ProdutoResponseDTO> resposta = List.of(
                new ProdutoResponseDTO(1L, "CDB", "Renda Fixa", 10.5, "Baixo"),
                new ProdutoResponseDTO(2L, "Tesouro Selic", "Renda Fixa", 12.0, "Baixo")
        );

        when(produtoService.listarProdutos()).thenReturn(produtos);
        when(mapper.toResponseList(produtos)).thenReturn(resposta);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/produtos")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(2))
                .body("[0].id", equalTo(1))
                .body("[0].nome", equalTo("CDB"))
                .body("[0].risco", equalTo("Baixo"))
                .body("[1].nome", equalTo("Tesouro Selic"));

        verify(produtoService).listarProdutos();
        verify(mapper).toResponseList(produtos);
    }

    // =========================================================================
    // TESTE: listar recomendados - SUCESSO (200)
    // =========================================================================
    @Test
    void deveListarProdutosRecomendadosComSucesso() {

        List<Produto> produtos = List.of(
                new Produto(3L, "Ações XPTO", "Renda Variável", 25.0, RiscoProduto.ALTO)
        );

        List<ProdutoResponseDTO> resposta = List.of(
                new ProdutoResponseDTO(3L, "Ações XPTO", "Renda Variável", 25.0, "Alto")
        );

        when(produtoService.listarProdutosRecomendados("ALTO")).thenReturn(produtos);
        when(mapper.toResponseList(produtos)).thenReturn(resposta);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/produtos-recomendados/ALTO")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(1))
                .body("[0].nome", equalTo("Ações XPTO"))
                .body("[0].risco", equalTo("Alto"));

        verify(produtoService).listarProdutosRecomendados("ALTO");
        verify(mapper).toResponseList(produtos);
    }

    // =========================================================================
    // TESTE: perfil inválido → PerfilInvalidoException → 400
    // =========================================================================
    @Test
    void deveRetornar400QuandoPerfilInvalido() {

        when(produtoService.listarProdutosRecomendados("INVALIDO"))
                .thenThrow(new PerfilInvalidoException("Perfil inválido"));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/produtos-recomendados/INVALIDO")
                .then()
                .statusCode(400);

        verify(produtoService).listarProdutosRecomendados("INVALIDO");
    }

    // =========================================================================
    // TESTE: erro interno → RuntimeException → 500
    // =========================================================================
    @Test
    void deveRetornar500QuandoErroInternoAoListarProdutos() {

        when(produtoService.listarProdutos()).thenThrow(new RuntimeException("Erro interno"));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/produtos")
                .then()
                .statusCode(500);

        verify(produtoService).listarProdutos();
    }
}
