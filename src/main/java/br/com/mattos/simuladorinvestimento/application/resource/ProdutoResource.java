package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.ProdutoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.ProdutoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Recurso REST responsável por gerenciar operações relacionadas aos produtos.
 * Expõe os endpoints de produtos.
 */
@Path("/")
@Consumes("application/json")
@Produces("application/json")
@Tag(name = "Produto", description = "Endpoints relacionados a Produto")
public class ProdutoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoResource.class);

    @Inject
    ProdutoService produtoService;

    @Inject
    ProdutoMapper mapper;

    /**
     * Endpoint para listar todos os produtos disponíveis.
     * @return Lista de {@link ProdutoResponseDTO} representando os produtos.
     */
    @GET
    @Path("/produtos")
    @Operation(summary = "Listar Produtos", description = "Lista Todos os Produtos existentes na aplicação")
    public List<ProdutoResponseDTO> listar() {
        LOGGER.info("Requisição recebida: listar todos produtos");
        List<Produto> produtos = produtoService.listarProdutos();
        LOGGER.info("Retornando {} produtos", produtos.size());
        return mapper.toResponseList(produtos);
    }

    @GET
    @Path("/produtos-recomendados/{perfil}")
    @Operation(summary = "Listar Produtos Recomendados", description = "Lista Todos os Produtos recomendados para o Perfil(nome Perfil) informado")
    public List<ProdutoResponseDTO> listarProdutosRecomendados(
            @Parameter(description = "Perfil do cliente: Baixo, Médio ou Alto", required = true)
            @PathParam("perfil") String perfil) {
        LOGGER.info("Requisição recebida: produtos recomendados para o perfil {}", perfil);
        List<Produto> produtos = produtoService.listarProdutosRecomendados(perfil);
        LOGGER.info("Retornando {} produtos recomendados", produtos.size());
        return mapper.toResponseList(produtos);
    }
}
