package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper.ProdutoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoRepositoryImpl.class);

    @Inject
    ProdutoMapper mapper;

    @Override
    public Optional<Produto> buscarPorTipo(String tipoProduto) {
        LOGGER.debug("Buscando produto pelo tipo: {}", tipoProduto);
        ProdutoEntity entity =
                ProdutoEntity.find("tipo.nome", tipoProduto).firstResult();

        return Optional.ofNullable(mapper.toDomain(entity));
    }


    @Override
    public List<Produto> listarTodos() {
        LOGGER.debug("Buscando todos produtos no banco");
        return ProdutoEntity.findAll().<ProdutoEntity>list()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
