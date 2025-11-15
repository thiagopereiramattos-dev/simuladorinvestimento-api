package br.com.mattos.simuladorinvestimento.application.controller;

import br.com.mattos.simuladorinvestimento.application.dto.*;
import br.com.mattos.simuladorinvestimento.application.mapper.SimulacaoMapper;
import br.com.mattos.simuladorinvestimento.domain.service.SimulacaoService;
import jakarta.inject.Inject;
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
    public SimularInvestimentoResponseDTO simular(SimularInvestimentoRequestDTO request) {
        var dominio = mapper.toDomain(request);
        var simulacao = service.simular(dominio);
        return mapper.toResponse(simulacao);
    }
}
