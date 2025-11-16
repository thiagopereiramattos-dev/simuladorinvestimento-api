package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.ProdutoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.ProdutoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.List;

@Path("/produtos")
@Consumes("application/json")
@Produces("application/json")
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @Inject
    ProdutoMapper mapper;

    @GET
    public List<ProdutoResponseDTO> listar() {
        List<Produto> produtos = produtoService.listarTodos();
        return mapper.toResponseList(produtos);
    }
}
