package br.com.stoom.store.product;


import br.com.stoom.store.dto.CategoryDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(name = "dt_cadastro")
    private Timestamp dtCadastro;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Timestamp getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Timestamp dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Category() {
        this.dtCadastro = Timestamp.from(Instant.now());
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ativo=" + ativo +
                ", dtCadastro=" + dtCadastro +
                '}';
    }

    public CategoryDto toDto() {
        return new CategoryDto(this.getNome());
    }
}
