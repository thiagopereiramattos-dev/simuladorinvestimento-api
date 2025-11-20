package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Cliente;
import br.com.mattos.simuladorinvestimento.domain.model.ClienteLogin;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.repository.ClienteRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper.ClienteMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

/**
 * Implementação de {@link ClienteRepository} utilizando {@link ClientePanacheRepository}.
 * <p>
 * Responsável por operações de persistência e consulta de clientes, convertendo entre:
 * <ul>
 *     <li>{@link ClienteEntity} (entidade)</li>
 *     <li>{@link Cliente} (domínio)</li>
 *     <li>{@link ClientePerfilRisco} para consultas de perfil de risco</li>
 * </ul>
 * </p>
 */
@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository {

    @Inject
    ClientePanacheRepository panacheRepo;

    @Inject
    ClienteMapper mapper;

    /**
     * Busca um cliente pelo seu ID.
     *
     * @param idCliente ID do cliente no domínio
     * @return {@link Optional} com o {@link Cliente} correspondente, ou {@code Optional.empty()} se não encontrado
     */
    @Override
    public Optional<Cliente> buscarPorId(Long idCliente) {
        return panacheRepo.findByIdOptional((idCliente))
                .map(mapper::toDomain);
    }

    /**
     * Lista todos os clientes cadastrados.
     *
     * @return lista de {@link Cliente} no domínio, pode estar vazia
     */
    @Override
    public List<Cliente> listarTodos() {
        return panacheRepo.listAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    /**
     * Busca o perfil de risco de um cliente pelo ID.
     *
     * @param idCliente ID do cliente no domínio
     * @return {@link Optional} com {@link ClientePerfilRisco} contendo nome, pontuação e descrição do perfil,
     *         ou {@code Optional.empty()} se o cliente não existir
     */
    @Override
    public Optional<ClientePerfilRisco> buscarPerfilRiscoPorId(Long idCliente) {
        return panacheRepo.findByIdOptional(idCliente)
                .map(mapper::toClientePerfilRisco);
    }

    @Override
    public Optional<ClienteLogin> findByEmail(String email) {
        return panacheRepo.find("email", email)
                .firstResultOptional()
                .map(mapper::toClienteLogin);

    }
}
