package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimulacaoMapper {

    @Inject
    ProdutoMapper produtoMapper; // Injetando o mapper de Produto

    /**
     * Converte SimulacaoInvestimento do domínio em SimulacaoInvestimentoEntity
     * @param simulacao objeto de domínio
     * @return entidade de persistência pronta para salvar
     */
    public SimulacaoInvestimentoEntity toEntity(SimulacaoInvestimento simulacao) {

        SimulacaoInvestimentoEntity entity = new SimulacaoInvestimentoEntity();

        var produtoEntity = produtoMapper.toEntity(simulacao.produto());
        entity.setProduto(produtoEntity);

        ClienteEntity cliente = new ClienteEntity();;
        if (simulacao.clientId() != null) {
            cliente.setId(simulacao.clientId());
        }
        entity.setCliente(cliente);

        double valorFinal = simulacao.resultado().valorFinal();
        double rentabilidade = simulacao.resultado().rentabilidadeEfetiva();
        entity.setValorInicial(valorFinal / (1 + rentabilidade));
        entity.setValorFinal(valorFinal);
        entity.setRentabilidade(rentabilidade);
        entity.setPrazo(simulacao.resultado().prazoMeses());
        entity.setDataSimulacao(simulacao.dataSimulacao());
        entity.setDataSimulacaoTexto(simulacao.dataSimulacao().toString());


        return entity;
    }
}
