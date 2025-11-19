package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.model.Cliente;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.PerfilRiscoEntity;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Mapper responsável por converter entre entidades JPA e objetos do domínio.
 * <p>
 * Esta classe oferece métodos para:
 * <ul>
 *     <li>Transformar {@link ClienteEntity} em {@link Cliente} (domínio).</li>
 *     <li>Transformar {@link Cliente} em {@link ClienteEntity} para persistência.</li>
 *     <li>Extrair informações de perfil de risco do cliente em {@link ClientePerfilRisco}.</li>
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

//    /**
//     * Converte um objeto de domínio {@link Cliente} em entidade {@link ClienteEntity}.
//     * <p>
//     * Observação: o {@link PerfilRiscoEntity} deve ser configurado separadamente,
//     * geralmente via serviço ou repositório, para manter a consistência do banco.
//     * </p>
//     *
//     * @param cliente objeto de domínio, pode ser {@code null}
//     * @return entidade JPA correspondente, ou {@code null} se o objeto de domínio for {@code null}
//     */
//    public ClienteEntity toEntity(Cliente cliente) {
//        if (cliente == null) return null;
//
//        ClienteEntity entity = new ClienteEntity();
//        entity.setId(cliente.id() != null ? cliente.id().intValue() : null);
//        entity.setNome(cliente.nome());
//        entity.setCpf(cliente.cpf());
//        entity.setEmail(cliente.email());
//        // converter o perfilRisco do String para enum
////        if (cliente.perfilRisco() != null) {
////            entity.setPerfilRisco(Enum.valueOf(
////                    br.com.mattos.simuladorinvestimento.domain.enums.PerfilRiscoCliente.class,
////                    cliente.perfilRisco().toUpperCase()
////            ));
////        }
//        // perfilRisco deve ser setado via PerfilRiscoEntity em outro momento
//        return entity;
//    }


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
}
