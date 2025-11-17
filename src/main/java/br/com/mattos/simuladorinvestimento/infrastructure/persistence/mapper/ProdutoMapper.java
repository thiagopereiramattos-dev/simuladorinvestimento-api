package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.RiscoEntity;
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

        String risco = Optional.ofNullable(entity.getRisco())
                .map(RiscoEntity::getNivel)
                .orElseThrow(() -> new ProdutoInvalidoException("Risco do produto não definido"));

        return new Produto(
                entity.getId(),
                entity.getNome(),
                tipo,
                entity.getRentabilidade(),
                risco
        );
    }

    public ProdutoEntity toEntity(Produto produto) {

        if (produto == null) {
            return null;
        }

        TipoProdutoEntity tipoProdutoEntity = new TipoProdutoEntity();;
        tipoProdutoEntity.setNome(produto.tipo());

        RiscoEntity riscoEntity = new RiscoEntity();;
        riscoEntity.setNivel(produto.risco());

        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(produto.id());
        entity.setNome(produto.nome());
        entity.setTipo(tipoProdutoEntity);
        entity.setRisco(riscoEntity);
        entity.setRentabilidade(produto.rentabilidade());

        return entity;
    }
}
