package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "risco")
public class RiscoEntity extends PanacheEntity {

    @Column(name = "nivel", unique = true, nullable = false)
    public String nivel;

    @Column(name = "descricao")
    public String descricao;
}
