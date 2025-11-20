package br.com.mattos.simuladorinvestimento.application.exception;

import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.exception.DomainException;
import br.com.mattos.simuladorinvestimento.domain.exception.TipoProdutoInvalidoException;
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
 * <p>Este mapper captura todas as exceções que estendem {@link DomainException} e
 * converte em uma resposta HTTP 400 (Bad Request) com a mensagem da exceção.</p>
 *
 * <p>As respostas de erro seguem o padrão {@link ApiErrorResponse} definido na aplicação.</p>
 *
 * <p>Qualquer outra exceção que não seja do tipo {@link DomainException} não será capturada por este mapper.</p>
 */
@Provider
public class DomainExceptionMapper extends BaseExceptionHandler implements ExceptionMapper<DomainException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(DomainException exception) {
        LOGGER.info("Exceção de domínio: {}", exception.getMessage());

        return buildResponse(
                Response.Status.BAD_REQUEST,
                exception.getMessage(),
                uriInfo
        );
    }

}
