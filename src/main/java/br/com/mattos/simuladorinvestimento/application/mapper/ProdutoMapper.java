package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.response.ProdutoResponseDTO;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

/**
 * Mapper responsável por converter objetos de domínio {@link Produto} para os DTOs de resposta da API.
 */
@ApplicationScoped
public class ProdutoMapper {

    /**
     * Converte um objeto de domínio {@link Produto} em um DTO de resposta {@link ProdutoResponseDTO}.
     *
     * @param p Objeto de domínio Produto
     * @return DTO ProdutoResponseDTO correspondente
     */
    public ProdutoResponseDTO toResponse(Produto p) {
        return new ProdutoResponseDTO(
                p.id(),
                p.nome(),
                p.tipo(),
                p.rentabilidade(),
                p.risco()
        );
    }


    /**
     * Converte uma lista de objetos de domínio {@link Produto} em uma lista de DTOs de resposta {@link ProdutoResponseDTO}.
     *
     * @param produtos Lista de objetos Produto
     * @return Lista de DTOs ProdutoResponseDTO
     */
    public List<ProdutoResponseDTO> toResponseList(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toResponse)
                .toList();
    }
}
