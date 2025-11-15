package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

//@Entity
//public class ProdutoEntity extends PanacheEntity {

public class ProdutoEntity{

        public String nome;
    public String tipo;
    public Double rentabilidade;
    public String risco;
}
