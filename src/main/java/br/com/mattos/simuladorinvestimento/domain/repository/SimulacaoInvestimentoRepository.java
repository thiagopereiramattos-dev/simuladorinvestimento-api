package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;

public interface SimulacaoInvestimentoRepository {

    void salvar(SimulacaoInvestimento simulacao);
}
