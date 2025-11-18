package br.com.mattos.simuladorinvestimento.application.exception;

import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class DomainExceptionMapper extends BaseExceptionHandler implements ExceptionMapper<RuntimeException> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DomainExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(RuntimeException exception) {

        if (exception instanceof ProdutoNaoEncontradoException e) {
            LOGGER.info("Produto não encontrado: {}", e.getMessage());
            return buildResponse(Response.Status.NOT_FOUND, e.getMessage(), uriInfo);
        }

        // delega para o GlobalExceptionMapper
        return null;
    }
}
