package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimularInvestimentoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.SimulacaoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoEntrada;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.domain.service.SimulacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

@Path("/simular-investimento")
@Consumes("application/json")
@Produces("application/json")
public class SimulacaoInvestimentoResource {

    @Inject
    SimulacaoService service;

    @Inject
    SimulacaoMapper mapper;

    @POST
    public SimularInvestimentoResponseDTO simular(@Valid SimularInvestimentoRequestDTO request) {
        SimulacaoEntrada dominio = mapper.toDomain(request);
        SimulacaoInvestimento simulacao = service.simular(dominio);
        return mapper.toResponse(simulacao);
    }
}
