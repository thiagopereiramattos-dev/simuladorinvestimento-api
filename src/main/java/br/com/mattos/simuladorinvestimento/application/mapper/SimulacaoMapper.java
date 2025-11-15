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
                simulacao.getProduto().getId(),
                simulacao.getProduto().getNome(),
                simulacao.getProduto().getTipo(),
                simulacao.getProduto().getRentabilidade(),
                simulacao.getProduto().getRisco()
        );

        ResultadoSimulacaoDTO resultado = new ResultadoSimulacaoDTO(
                simulacao.getResultado().getValorFinal(),
                simulacao.getResultado().getRentabilidadeEfetiva(),
                simulacao.getResultado().getPrazoMeses()
        );

        return new SimularInvestimentoResponseDTO(
                produto,
                resultado,
                simulacao.getDataSimulacao().toString()
        );
    }
}
