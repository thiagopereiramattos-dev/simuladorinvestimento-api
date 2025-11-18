package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.*;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.ZoneId;
import java.util.List;

/**
 * Mapper responsável por converter entre os objetos de domínio (domain) e os DTOs da API.
 * Inclui mapeamento de SimulacaoInvestimento e Produto.
 */
@ApplicationScoped
public class SimulacaoMapper {

    /**
     * Converte o DTO de requisição para o modelo de domínio.
     *
     * @param req DTO da requisição de simulação de investimento
     * @return Objeto de domínio SimulacaoEntrada
     */
    public SimulacaoEntrada toDomain(SimularInvestimentoRequestDTO req) {
        return new SimulacaoEntrada(
                req.clienteId(),
                req.valor(),
                req.prazoMeses(),
                req.tipoProduto()
        );
    }

    /**
     * Converte um objeto de domínio {@link SimulacaoInvestimento} em um DTO de resposta {@link SimularInvestimentoResponseDTO}.
     *
     * @param simulacao Objeto de domínio SimulacaoInvestimento
     * @return DTO SimularInvestimentoResponseDTO
     */
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

    /**
     * Converte um objeto de domínio {@link SimulacaoInvestimento} em um DTO de resposta {@link SimulacaoListResponseDTO}.
     *
     * @param simulacao Objeto de domínio SimulacaoInvestimento
     * @return DTO de SimulacaoListResponseDTO
     */
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

    /**
     * Converte uma lista de objetos de domínio Lista de {@link SimulacaoInvestimento}para uma lista de DTOs de lista de simulações {@link SimulacaoListResponseDTO}..
     *
     * @param simulacoes Lista de objetos SimulacaoInvestimento
     * @return Lista de DTOs SimulacaoListResponseDTO
     */
    public List<SimulacaoListResponseDTO> toListResponseList(List<SimulacaoInvestimento> simulacoes) {
        return simulacoes.stream()
                .map(this::toListResponse)
                .toList();
    }

}
