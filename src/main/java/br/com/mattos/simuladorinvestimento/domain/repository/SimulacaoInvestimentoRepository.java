package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;

import java.util.List;

/** Interface para persistência e consulta de simulações de investimento. */
public interface SimulacaoInvestimentoRepository {

    /**
     * Salva uma simulação de investimento.
     * @param simulacao simulação de investimento a ser salva
     */
    void salvar(SimulacaoInvestimento simulacao);

    /** Lista todas as simulações registradas. */
    List<SimulacaoInvestimento> listar();

}
