package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;

import java.util.List;

public interface SimulacaoInvestimentoRepository {

    void salvar(SimulacaoInvestimento simulacao);
    List<SimulacaoInvestimento> listar();

}
