package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ClienteRepository;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações de cliente.
 */
@ApplicationScoped
public class ClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    @Inject
    ClienteRepository clienteRepository;

    /**
     * Consulta o perfil de risco de um cliente pelo seu ID.
     *
     * @param clientId ID do cliente
     * @return {@link ClientePerfilRisco} com informações de nome, pontuação e descrição do perfil
     * @throws RuntimeException caso ocorra algum erro durante a consulta
     */
    public ClientePerfilRisco consultarPerfilRiscoCliente(Long clientId) {
        LOGGER.debug("Iniciando consulta do perfil de risco do cliente ID: {}", clientId);

        try {
            Optional<ClientePerfilRisco> perfilOpt = clienteRepository.buscarPerfilRiscoPorId(clientId);

            if (perfilOpt.isPresent()) {
                LOGGER.debug("Perfil de risco encontrado para o cliente ID: {}", clientId);
                return perfilOpt.get();
            } else {
                LOGGER.warn("Nenhum perfil de risco encontrado para o cliente ID: {}", clientId);
                throw new ClienteNaoEncontradoException(clientId.toString());
            }
        } catch (ClienteNaoEncontradoException exception) {
            throw exception;
        } catch (Exception ex) {
            LOGGER.error("Erro ao buscar perfil de risco para o cliente ID: {}", clientId, ex);
            throw new RuntimeException( "Não foi possível consultar o perfil de risco do cliente. Ocorreu um erro interno.", ex);
        }
    }
}
