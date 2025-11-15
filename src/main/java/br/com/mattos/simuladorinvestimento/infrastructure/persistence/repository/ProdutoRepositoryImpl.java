package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @Override
    public Produto buscarPorTipo(String tipoProduto) {

        ProdutoEntity entity = ProdutoEntity.find("tipo", tipoProduto).firstResult();
        return new Produto(
                entity.getId(),
                entity.getNome(),
                entity.getTipo(),
                entity.getRentabilidade(),
                entity.getRisco()
        );
    }
}
