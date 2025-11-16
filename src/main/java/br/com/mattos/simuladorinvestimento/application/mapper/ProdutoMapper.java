package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.response.ProdutoResponseDTO;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProdutoMapper {

    public ProdutoResponseDTO toResponse(Produto p) {
        return new ProdutoResponseDTO(
                p.id(),
                p.nome(),
                p.tipo(),
                p.rentabilidade(),
                p.risco()
        );
    }


    public List<ProdutoResponseDTO> toResponseList(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toResponse)
                .toList();
    }
}
