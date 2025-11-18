package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimulacaoListResponseDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimularInvestimentoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.SimulacaoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoEntrada;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.domain.service.SimulacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class SimulacaoInvestimentoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulacaoInvestimentoResource.class);

    @Inject
    SimulacaoService simulacaoService;

    @Inject
    SimulacaoMapper mapper;

    @POST
    @Path("/simular-investimento")
    public SimularInvestimentoResponseDTO simular(@Valid SimularInvestimentoRequestDTO request) {
        LOGGER.info("Requisição recebida: simular investimento");
        SimulacaoEntrada dominio = mapper.toDomain(request);
        SimulacaoInvestimento simulacao = simulacaoService.simular(dominio);
        LOGGER.info("Simulação realizada com sucesso: clienteId={} produto={} valorFinal={}",
                simulacao.clientId(),
                simulacao.produto().nome(),
                simulacao.resultado().valorFinal()
        );
        return mapper.toResponse(simulacao);
    }

    @GET
    @Path("/simulacoes")
    public List<SimulacaoListResponseDTO> listar() {
        LOGGER.info("Requisição recebida: listar simulações");
        List<SimulacaoInvestimento> simulacoes = simulacaoService.listarSimulacoes();
        LOGGER.info("Retornando {} simulações", simulacoes.size());
        return mapper.toListResponseList(simulacoes);
    }
}
