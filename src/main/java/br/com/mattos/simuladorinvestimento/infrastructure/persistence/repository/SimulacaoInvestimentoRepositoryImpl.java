package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.domain.repository.SimulacaoInvestimentoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper.SimulacaoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SimulacaoInvestimentoRepositoryImpl implements SimulacaoInvestimentoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulacaoInvestimentoRepositoryImpl.class);

    @Inject
    SimulacaoPanacheRepository panacheRepo;

    @Inject
    SimulacaoMapper mapper;

    @Transactional
    @Override
    public void salvar(SimulacaoInvestimento simulacao) {

        LOGGER.debug("Salvando simulação para clienteId={} e produto={}", simulacao.clientId(), simulacao.produto().nome());
        SimulacaoInvestimentoEntity entity = mapper.toEntity(simulacao);
        panacheRepo.persist(entity);
        LOGGER.info("Simulação salva com sucesso: id={}", entity.getId());
    }
}

