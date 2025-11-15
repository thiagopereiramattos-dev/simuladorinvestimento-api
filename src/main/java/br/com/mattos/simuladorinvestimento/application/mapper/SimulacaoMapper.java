package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.*;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimulacaoMapper {

    public SimulacaoEntrada toDomain(SimularInvestimentoRequestDTO req) {
        return new SimulacaoEntrada(
                req.clienteId(),
                req.valor(),
                req.prazoMeses(),
                req.tipoProduto()
        );
    }

    public SimularInvestimentoResponseDTO toResponse(Simulacao simulacao) {

        ProdutoSimulacaoDTO produto = new ProdutoSimulacaoDTO(
                simulacao.produto().id(),
                simulacao.produto().nome(),
                simulacao.produto().tipo(),
                simulacao.produto().rentabilidade(),
                simulacao.produto().risco()
        );

        ResultadoSimulacaoDTO resultado = new ResultadoSimulacaoDTO(
                simulacao.resultado().valorFinal(),
                simulacao.resultado().rentabilidadeEfetiva(),
                simulacao.resultado().prazoMeses()
        );

        return new SimularInvestimentoResponseDTO(
                produto,
                resultado,
                simulacao.dataSimulacao().toString()
        );
    }
}
