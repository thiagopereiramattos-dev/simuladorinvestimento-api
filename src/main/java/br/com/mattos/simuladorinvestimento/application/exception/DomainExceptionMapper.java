package br.com.mattos.simuladorinvestimento.application.exception;

import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper de exceções de domínio da aplicação.
 *
 * <p>Este mapper trata especificamente as exceções de negócio:
 * <ul>
 *     <li>{@link ProdutoNaoEncontradoException}: retorna HTTP 400 (Bad Request) com a mensagem da exceção.</li>
 *     <li>{@link ProdutoInvalidoException}: retorna HTTP 400 (Bad Request) com a mensagem da exceção.</li>
 * </ul>
 * </p>
 *
 * <p>Qualquer outra exceção de domínio (RuntimeException) que não seja tratada explicitamente
 * será convertida em HTTP 500 (Internal Server Error) lançando {@link WebApplicationException}.</p>
 *
 * <p>As respostas de erro seguem o padrão {@link ApiErrorResponse} definido na aplicação.</p>
 */
@Provider
public class DomainExceptionMapper extends BaseExceptionHandler implements ExceptionMapper<RuntimeException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(RuntimeException exception) {

        if (exception instanceof ProdutoNaoEncontradoException ex) {
            LOGGER.info("Produto não encontrado: {}", ex.getMessage());
            return buildResponse(Response.Status.BAD_REQUEST, ex.getMessage(), uriInfo);
        }

        if (exception instanceof ProdutoInvalidoException ex) {
            LOGGER.info("Produto invalido: {}", ex.getMessage());
            return buildResponse(Response.Status.BAD_REQUEST, ex.getMessage(), uriInfo);
        }

        // Qualquer outra exceção de domínio NÃO deve lançar Exception.
        LOGGER.error("Exceção de domínio não tratada", exception);
        return buildResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                "Erro interno ao processar domínio.",
                uriInfo
        );
    }

}
