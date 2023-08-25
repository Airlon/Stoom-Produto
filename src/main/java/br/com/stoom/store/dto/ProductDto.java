package br.com.stoom.store.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDto implements Serializable {
    private long id;
    private String nome;
    private CategoryDto categoria;
    private String descricao;
    private BrandDto marca;
    private BigDecimal preco;
    private int quantidade;
    private Timestamp dtCadastro;
    private boolean ativo = true;

    private List<ProductDto> productList;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

   public ProductDto() {
        this.dtCadastro = Timestamp.from(Instant.now());
        this.productList = new ArrayList<>();
    }

    public void addProduct(ProductDto product) {
        productList.add(product);
    }

    public List<ProductDto> getProductList() {
        return productList;
    }

   public ProductDto(List<ProductDto> productList) {
        this.dtCadastro = Timestamp.from(Instant.now());
        this.productList = productList;
    }

    public void setProductList(List<ProductDto> productList) {
        this.productList = productList;
    }

    public ProductDto(
            long id, String nome, CategoryDto categoria, String descricao, BrandDto marca,
            BigDecimal preco, int quantidade) {
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

    public CategoryDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryDto categoria) {
        this.categoria = categoria;
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

    public void setDtCadastro(java.sql.Timestamp dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id == that.id && quantidade == that.quantidade && Objects.equals(nome, that.nome) &&
                Objects.equals(categoria, that.categoria) && Objects.equals(descricao, that.descricao) &&
                Objects.equals(marca, that.marca) && Objects.equals(preco, that.preco) &&
                Objects.equals(dtCadastro, that.dtCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, categoria, descricao, marca, preco, quantidade, dtCadastro);
    }

}