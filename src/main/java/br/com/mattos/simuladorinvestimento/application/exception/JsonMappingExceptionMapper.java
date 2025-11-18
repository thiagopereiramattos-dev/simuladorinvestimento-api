package br.com.mattos.simuladorinvestimento.application.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@Provider
public class JsonMappingExceptionMapper extends BaseExceptionHandler
        implements ExceptionMapper<MismatchedInputException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMappingExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(MismatchedInputException exception) {


        String campo = "desconhecido";
        if (!exception.getPath().isEmpty() && exception.getPath().get(0).getFieldName() != null) {
            campo = exception.getPath().get(0).getFieldName();
        }

        CampoErro detalhe = new CampoErro(campo, "tipo de campo incorreto");
        String mensagem = "JSON inválido ou tipo de campo incorreto";

        LOGGER.warn("Erro de mapeamento JSON na requisição | Path: {} | Campo: {}",
                uriInfo.getPath(), campo);

        ApiErrorResponseDet response = new ApiErrorResponseDet(
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                mensagem,
                uriInfo.getPath(),
                Collections.singletonList(detalhe)
        );

        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
