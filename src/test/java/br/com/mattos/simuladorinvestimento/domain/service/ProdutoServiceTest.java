package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
    ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodosOsProdutos() {
        Produto p1 = new Produto(1L, "Produto A", "Descrição A", 5.0, RiscoProduto.BAIXO);
        Produto p2 = new Produto(2L, "Produto B", "Descrição B", 10.0, RiscoProduto.ALTO);

        when(produtoRepository.listarTodos()).thenReturn(List.of(p1, p2));

        List<Produto> resultado = produtoService.listarProdutos();

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(p1));
        assertTrue(resultado.contains(p2));

        verify(produtoRepository).listarTodos();
    }

    @Test
    void deveListarProdutosRecomendadosPorPerfil() {
        Produto p1 = new Produto(1L, "Produto A", "Descrição A", 5.0, RiscoProduto.BAIXO);

        when(produtoRepository.listarPorRisco(RiscoProduto.BAIXO)).thenReturn(List.of(p1));

        List<Produto> resultado = produtoService.listarProdutosRecomendados("Baixo");

        assertEquals(1, resultado.size());
        assertEquals("Produto A", resultado.get(0).nome());

        verify(produtoRepository).listarPorRisco(RiscoProduto.BAIXO);
    }

    @Test
    void deveLancarExcecaoQuandoPerfilInvalido() {
        assertThrows(RuntimeException.class, () -> produtoService.listarProdutosRecomendados("Invalido"));
    }
}
