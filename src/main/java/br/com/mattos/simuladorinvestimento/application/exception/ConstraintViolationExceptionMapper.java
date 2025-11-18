package br.com.mattos.simuladorinvestimento.application.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper de exceção responsável por tratar erros de validação de campos.
 *
 * <p>Captura violações de validação de anotações como {@code @NotNull}, {@code @Positive}, {@code @NotBlank}, entre outras,
 * retornando uma resposta estruturada em JSON.</p>
 * <p>A resposta possui status HTTP 400 (Bad Request) e detalhes sobre os campos inválidos,
 * representados por {@link CampoErro} dentro de {@link ApiErrorResponseDet}.</p>
 */
@Provider
public class ConstraintViolationExceptionMapper extends BaseExceptionHandler  implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOGGER.warn("Erro de validação na requisição: {}", exception.getMessage());

        List<CampoErro> detalhes = exception.getConstraintViolations()
                .stream()
                .map(v -> {
                    String campo = "";
                    for (var node : v.getPropertyPath()) {
                        campo = node.getName(); // pega o último node
                    }
                    return new CampoErro(campo, v.getMessage());
                })
                .collect(Collectors.toList());

        String mensagem = "Erros de validação";

        ApiErrorResponseDet response = new ApiErrorResponseDet(
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                mensagem,
                uriInfo.getPath(),
                detalhes
        );

        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
