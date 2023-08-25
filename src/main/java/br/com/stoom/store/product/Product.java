package br.com.stoom.store.product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoria;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Brand marca;

    private BigDecimal preco;
    private int quantidade;
    private Timestamp dtCadastro;
    private boolean ativo = true;

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public Product() {
        this.dtCadastro = Timestamp.from(Instant.now());
    }

    public Product(long id, String nome, Category categoria, String descricao, Brand marca, BigDecimal preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dtCadastro = Timestamp.from(Instant.now());
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

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Brand getMarca() {
        return marca;
    }

    public void setMarca(Brand marca) {
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

}

