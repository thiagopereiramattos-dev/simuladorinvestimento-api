package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo_produto")
public class TipoProdutoEntity extends PanacheEntity {

    @Column(name = "nome", unique = true, nullable = false)
    public String nome;

    @Column(name = "descricao")
    public String descricao;
}