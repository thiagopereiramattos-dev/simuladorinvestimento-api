package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper.ProdutoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @Inject
    ProdutoMapper mapper;

    @Override
    public Produto buscarPorTipo(String tipoProduto) {
        ProdutoEntity entity = ProdutoEntity.find("tipo.nome", tipoProduto).firstResult();

        if (entity == null) {
            throw new IllegalArgumentException("Produto do tipo '" + tipoProduto + "' não encontrado.");
        }

        return mapper.toDomain(entity);
    }

    @Override
    public List<Produto> listarTodos() {
        return ProdutoEntity.findAll().<ProdutoEntity>list()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

//    @Override
//    public List<Produto> listarTodos() {
//        return ProdutoEntity.listAll().stream()
//                .map(mapper::toDomain)
//                .collect(Collectors.toList());
//    }
}
