package br.com.stoom.store.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CustomDto {

    private long id;
    private String nome;
    private String descricao;
    private BrandDto marca;
    private BigDecimal preco;
    private int quantidade;
    private Timestamp dtCadastro;
    private boolean ativo;

    public CustomDto(
            long id, String nome, String descricao, BrandDto marca,
            BigDecimal preco, int quantidade, Timestamp dtCadastro, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dtCadastro = dtCadastro;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BrandDto getMarca() {
        return marca;
    }

    public void setMarca(BrandDto marca) {
        this.marca = marca;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Timestamp getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Timestamp dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

