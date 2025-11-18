package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.ProdutoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.ProdutoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Recurso REST responsável por gerenciar operações relacionadas aos produtos.
 * Expõe os endpoints de produtos.
 */
@Path("/produtos")
@Consumes("application/json")
@Produces("application/json")
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
    public List<ProdutoResponseDTO> listar() {
        LOGGER.info("Requisição recebida: listar todos produtos");
        List<Produto> produtos = produtoService.listarTodos();
        LOGGER.info("Retornando {} produtos", produtos.size());
        return mapper.toResponseList(produtos);
    }
}
