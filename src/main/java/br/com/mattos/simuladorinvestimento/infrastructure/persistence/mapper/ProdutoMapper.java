package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.TipoProdutoEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped

/**
 * Mapper responsável por converter entre a entidade {@link ProdutoEntity} e o objeto de domínio {@link Produto}.
 * <p>
 * Trata validações básicas, como risco e tipo do produto, lançando exceções caso valores obrigatórios não estejam definidos.
 * </p>
 */
public class ProdutoMapper {


    /**
     * Converte uma entidade {@link ProdutoEntity} em um objeto de domínio {@link Produto}.
     *
     * @param entity a entidade a ser convertida
     * @return objeto de domínio equivalente, ou {@code null} se a entidade for {@code null}
     * @throws ProdutoInvalidoException se o tipo ou risco do produto não estiver definido
     */
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


    /**
     * Converte um objeto de domínio {@link Produto} em uma entidade {@link ProdutoEntity}.
     *
     * @param produto objeto de domínio a ser convertido
     * @return entidade equivalente pronta para persistência, ou {@code null} se o objeto for {@code null}
     * @throws ProdutoInvalidoException se o risco do produto for inválido
     */
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
