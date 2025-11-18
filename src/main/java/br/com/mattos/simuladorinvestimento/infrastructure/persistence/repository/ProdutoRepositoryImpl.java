package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper.ProdutoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @Inject
    ProdutoPanacheRepository panacheRepo;

    @Inject
    ProdutoMapper mapper;

    @Override
    public Optional<Produto> buscarPorTipo(String tipoProduto) {
        return panacheRepo.findByTipoNome(tipoProduto)
                .map(mapper::toDomain);
    }

    @Override
    public List<Produto> listarTodos() {
        return panacheRepo.listAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
