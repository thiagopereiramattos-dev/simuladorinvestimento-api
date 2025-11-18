package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimulacaoListResponseDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimularInvestimentoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.SimulacaoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoEntrada;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.domain.service.SimulacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/simular-investimento")
@Consumes("application/json")
@Produces("application/json")
public class SimulacaoInvestimentoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulacaoInvestimentoResource.class);

    @Inject
    SimulacaoService simulacaoService;

    @Inject
    SimulacaoMapper mapper;

    @POST
    public SimularInvestimentoResponseDTO simular(@Valid SimularInvestimentoRequestDTO request) {
        SimulacaoEntrada dominio = mapper.toDomain(request);
        SimulacaoInvestimento simulacao = simulacaoService.simular(dominio);
        return mapper.toResponse(simulacao);
    }

    @GET
    public List<SimulacaoListResponseDTO> listar() {

        LOGGER.info("Requisição recebida: listar todos produtos");
        List<SimulacaoInvestimento> simulacoes = simulacaoService.listarSimulacoes();
        LOGGER.info("Retornando {} simulacoes", simulacoes.size());
        return mapper.toListResponseList(simulacoes);
    }
}
