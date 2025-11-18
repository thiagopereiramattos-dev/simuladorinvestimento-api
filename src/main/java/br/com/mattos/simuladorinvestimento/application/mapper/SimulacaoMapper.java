package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.*;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.ZoneId;
import java.util.List;

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

    public SimularInvestimentoResponseDTO toResponse(SimulacaoInvestimento simulacao) {

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

        // Converte UTC para horário de São Paulo na API
        String dataSimulacaoZoneSP = simulacao.dataSimulacao()
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toLocalDateTime()
                .toString();

        return new SimularInvestimentoResponseDTO(
                produto,
                resultado,
                dataSimulacaoZoneSP
        );
    }

    public SimulacaoListResponseDTO toListResponse(SimulacaoInvestimento simulacao) {
        return new SimulacaoListResponseDTO(
                simulacao.idSimulacao(),
                simulacao.clientId(),
                simulacao.produto().nome(),
                simulacao.resultado().valorFinal() / (1 + simulacao.resultado().rentabilidadeEfetiva()),
                simulacao.resultado().valorFinal(),
                simulacao.resultado().prazoMeses(),
                simulacao.dataSimulacao()
                        .atZone(ZoneId.of("America/Sao_Paulo"))
                        .toLocalDateTime()
                        .toString()
        );
    }

    public List<SimulacaoListResponseDTO> toListResponseList(List<SimulacaoInvestimento> simulacoes) {
        return simulacoes.stream()
                .map(this::toListResponse)
                .toList();
    }

}
