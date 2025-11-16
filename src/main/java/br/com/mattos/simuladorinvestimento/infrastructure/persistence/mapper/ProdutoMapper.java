package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProdutoMapper {

    public Produto toDomain(ProdutoEntity entity) {

        if (entity == null) {
            return null;
        }

        String tipoNome = Optional.ofNullable(entity.tipo)
                .map(t -> t.nome)
                .orElseThrow(() -> new IllegalStateException("Tipo do produto não definido"));

        String risco = Optional.ofNullable(entity.risco)
                .map(r -> r.nivel)
                .orElseThrow(() -> new IllegalStateException("Risco do produto não definido"));

        return new Produto(
                entity.id,
                entity.nome,
                tipoNome,
                entity.rentabilidade,
                risco
        );
    }
}
