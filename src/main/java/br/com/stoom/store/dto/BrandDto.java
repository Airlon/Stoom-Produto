package br.com.stoom.store.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class BrandDto implements Serializable  {

    private long id;
    private String nome;
    private boolean ativo = true;
    private Timestamp dtCadastro;

    public BrandDto(String nome) {
        this.nome = nome;
    }

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

    public BrandDto() {
        this.dtCadastro = Timestamp.from(Instant.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandDto that = (BrandDto) o;
        return id == that.id && ativo == that.ativo && Objects.equals(nome, that.nome) && Objects.equals(dtCadastro, that.dtCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, ativo, dtCadastro);
    }

}