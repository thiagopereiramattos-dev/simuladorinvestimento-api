package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.model.Cliente;
import br.com.mattos.simuladorinvestimento.domain.model.ClienteLogin;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.PerfilRiscoEntity;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Mapper responsável por converter entre entidades JPA e objetos do domínio.
 * <p>
 * Esta classe oferece métodos para:
 * <ul>
 *     <li>Transformar {@link ClienteEntity} em {@link Cliente}.</li>
 *     <li>Transformar {@link ClienteEntity} em {@link ClientePerfilRisco}.</li>
 *     <li>Transformar {@link ClienteEntity} em {@link ClienteLogin} para autenticação.</li>
 * </ul>
 * </p>
 */
@ApplicationScoped
public class ClienteMapper {

    /**
     * Converte uma entidade {@link ClienteEntity} em objeto de domínio {@link Cliente}.
     *
     * @param entity entidade do banco de dados, pode ser {@code null}
     * @return objeto de domínio correspondente, ou {@code null} se a entidade for {@code null}
     */
    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;

        return new Cliente(
                entity.getId() != null ? entity.getId().longValue() : null,
                entity.getNome(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getPerfilRisco() != null ? entity.getPerfilRisco().getNome() : null
        );
    }

    /**
     * Converte uma entidade {@link ClienteEntity} em {@link ClientePerfilRisco}.
     * <p>
     * Retorna apenas os dados necessários do perfil de risco associado ao cliente,
     * incluindo nome do perfil, pontuação e descrição.
     * </p>
     *
     * @param entity entidade de cliente com {@link PerfilRiscoEntity} associado
     * @return record {@link ClientePerfilRisco}, ou {@code null} se cliente ou perfil forem {@code null}
     */
    public ClientePerfilRisco toClientePerfilRisco(ClienteEntity entity) {
        if (entity == null || entity.getPerfilRisco() == null) return null;

        PerfilRiscoEntity perfil = entity.getPerfilRisco();

        return new ClientePerfilRisco(
                entity.getId().longValue(),
                perfil.getNome(),
                perfil.getPontuacao(),
                perfil.getDescricao()
        );
    }

    /**
     * Converte uma entidade {@link ClienteEntity} em objeto de domínio {@link ClienteLogin}.
     * <p>
     * Este mapper é utilizado especificamente para operações de autenticação,
     * retornando apenas os campos necessários: id, email e senha hash.
     * </p>
     *
     * @param entity entidade do banco de dados, pode ser {@code null}
     * @return objeto {@link ClienteLogin}, ou {@code null} se a entidade for {@code null}
     */
    public ClienteLogin toClienteLogin(ClienteEntity entity) {
        if (entity == null) return null;

        return new ClienteLogin(
                entity.getId() != null ? entity.getId().longValue() : null,
                entity.getEmail(),
                entity.getSenhaHash()
        );
    }
}
