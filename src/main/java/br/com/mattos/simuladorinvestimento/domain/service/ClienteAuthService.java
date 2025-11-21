package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.exception.SenhaInvalidaException;
import br.com.mattos.simuladorinvestimento.domain.model.AuthResultado;
import br.com.mattos.simuladorinvestimento.domain.model.ClienteLogin;
import br.com.mattos.simuladorinvestimento.domain.repository.ClienteRepository;
import br.com.mattos.simuladorinvestimento.domain.util.HashUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Serviço responsável pelas operações de autenticação.
 */
@ApplicationScoped
public class ClienteAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteAuthService.class);

    private static final String CHAVE_FIXA = "01234567890123456789012345678901"; // 32 chars = 256 bits


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
            LOGGER.info("Algoritmo HS256, chave usada: {}",
                    System.getProperty("smallrye.jwt.sign.key", "key not found"));

            String token = gerarToken(clienteLogin);
            LOGGER.info("Token JWT: {}", token);
            LOGGER.info("Autenticação realizada com sucesso para o e-mail {}", email);

            return new AuthResultado(clienteLogin.id(), token);

        } catch (ClienteNaoEncontradoException | SenhaInvalidaException ex) {
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("Erro ao autenticar cliente com e-mail {}", email, ex);
            throw new RuntimeException("Não foi possível realizar a autenticação. Ocorreu um erro interno.",ex);
        }
    }

    /**
     * Gera token JWT simples usando HS256.
     *
     * @param clienteLogin dados do cliente
     * @return token JWT
     */
    private String gerarToken(ClienteLogin clienteLogin) {
        long validade = 1000 * 60 * 60; // 1 hora
        return Jwts.builder()
                .setSubject(clienteLogin.email())
                .claim("clienteId", clienteLogin.id())
                .claim("groups", new String[]{"USER"})
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validade))
                .signWith(SignatureAlgorithm.HS256, CHAVE_FIXA.getBytes())
                .compact();
    }

}
