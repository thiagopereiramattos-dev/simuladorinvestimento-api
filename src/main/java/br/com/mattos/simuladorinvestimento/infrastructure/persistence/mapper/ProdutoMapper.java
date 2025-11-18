package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.TipoProdutoEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProdutoMapper {

    public Produto toDomain(ProdutoEntity entity) {

        if (entity == null) {
            return null;
        }

        String tipo = Optional.ofNullable(entity.getTipo())
                .map(TipoProdutoEntity::getNome)
                .orElseThrow(() -> new ProdutoInvalidoException("Tipo do produto não definido"));

        RiscoProduto riscoEnum = entity.getRisco();
        if (riscoEnum == null) {
            throw new ProdutoInvalidoException("Risco do produto não definido");
        }

        return new Produto(
                entity.getId(),
                entity.getNome(),
                tipo,
                entity.getRentabilidade(),
                riscoEnum.getDescricao()
        );
    }


    public ProdutoEntity toEntity(Produto produto) {

        if (produto == null) {
            return null;
        }

        var entity = new ProdutoEntity();

        entity.setId(produto.id());
        entity.setNome(produto.nome());

        var tipoProdutoEntity = new TipoProdutoEntity();
        tipoProdutoEntity.setNome(produto.tipo());
        entity.setTipo(tipoProdutoEntity);

        try {
            entity.setRisco(RiscoProduto.valueOf(produto.risco().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new ProdutoInvalidoException("Risco inválido: " + produto.risco());
        }

        entity.setRentabilidade(produto.rentabilidade());
        return entity;
    }
}
