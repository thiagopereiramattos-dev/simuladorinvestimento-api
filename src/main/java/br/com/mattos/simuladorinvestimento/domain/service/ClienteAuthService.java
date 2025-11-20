package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.exception.SenhaInvalidaException;
import br.com.mattos.simuladorinvestimento.domain.model.AuthResultado;
import br.com.mattos.simuladorinvestimento.domain.model.ClienteLogin;
import br.com.mattos.simuladorinvestimento.domain.repository.ClienteRepository;
import br.com.mattos.simuladorinvestimento.domain.util.HashUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serviço responsável pelas operações de autenticação.
 */
@ApplicationScoped
public class ClienteAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteAuthService.class);

    @Inject
    ClienteRepository clienteRepository;

    /**
     * Autentica o cliente com base em e-mail e senha e retorna o id e o token JWT.
     *
     * @param email e-mail informado no login
     * @param senhaDigitada senha informada no login
     * @return {@link AuthResultado} contendo id e token JWT
     */
    public AuthResultado autenticar(String email, String senhaDigitada) {

        LOGGER.debug("Iniciando autenticação do cliente com e-mail {}", email);

        try {
            ClienteLogin clienteLogin = clienteRepository.findByEmail(email)
                    .orElseThrow(() -> new ClienteNaoEncontradoException(
                            "Não existe cliente com esse email: " + email
                    ));

            // GERAR HASH DA SENHA DIGITADA
            String hashDigitado = HashUtil.sha256(senhaDigitada);

            LOGGER.info("Senha digitada: [{}]", senhaDigitada);
            LOGGER.info("Hash digitado SHA-256: {}", hashDigitado);
            LOGGER.info("Hash armazenado no banco: {}", clienteLogin.senhaHash());

            LOGGER.warn("Caracteres da senhaDigitada:");
            for (char c : senhaDigitada.toCharArray()) {
                LOGGER.warn("char='{}' code={}", c, (int) c);
            }
            LOGGER.warn("Tamanho da senhaDigitada: {}", senhaDigitada.length());

            boolean senhaValida = hashDigitado.equalsIgnoreCase(clienteLogin.senhaHash());

            LOGGER.debug("Resultado da comparação SHA-256: {}", senhaValida);

            if (!senhaValida) {
                LOGGER.warn("Senha inválida para o e-mail {}", email);
                throw new SenhaInvalidaException();
            }

            LOGGER.debug("Gerando token JWT para cliente {}", clienteLogin.id());

            String token = Jwt.issuer("simulador-api")
                    .upn(clienteLogin.email())
                    .claim(Claims.sub.name(), clienteLogin.email())
                    .claim("clienteId", clienteLogin.id())
                    .sign();

            LOGGER.info("Autenticação realizada com sucesso para o e-mail {}", email);

            return new AuthResultado(clienteLogin.id(), token);

        } catch (ClienteNaoEncontradoException | SenhaInvalidaException ex) {
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("Erro ao autenticar cliente com e-mail {}", email, ex);
            throw new RuntimeException(
                    "Não foi possível realizar a autenticação. Ocorreu um erro interno.",
                    ex
            );
        }
    }

}
