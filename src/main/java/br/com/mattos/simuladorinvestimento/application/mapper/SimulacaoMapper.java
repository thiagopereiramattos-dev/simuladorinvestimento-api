package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.ProdutoSimulacaoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.ResultadoSimulacaoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimularInvestimentoResponseDTO;
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

        ProdutoSimulacaoResponseDTO produto = new ProdutoSimulacaoResponseDTO(
                simulacao.produto().id(),
                simulacao.produto().nome(),
                simulacao.produto().tipo(),
                simulacao.produto().rentabilidade(),
                simulacao.produto().risco()
        );

        ResultadoSimulacaoResponseDTO resultado = new ResultadoSimulacaoResponseDTO(
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
