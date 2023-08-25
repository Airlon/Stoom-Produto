package br.com.stoom.store.service;

import br.com.stoom.store.dto.BrandDto;
import br.com.stoom.store.dto.CategoryDto;
import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.product.Brand;
import br.com.stoom.store.product.Category;
import br.com.stoom.store.product.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConverterService {

    public static Map<String, Object> convertBrand(Product product) {
        LinkedHashMap<String, Object> customDto = new LinkedHashMap<>();
        customDto.put("id", product.getId());
        customDto.put("nome", product.getNome());
        customDto.put("descricao", product.getDescricao());

        LinkedHashMap<String, String> marcaMap = new LinkedHashMap<>();
        marcaMap.put("nome", product.getMarca().getNome());
        customDto.put("marca", marcaMap);

        customDto.put("preco", product.getPreco());
        customDto.put("quantidade", product.getQuantidade());
        customDto.put("dtCadastro", product.getDtCadastro());
        customDto.put("ativo", product.isAtivo());

        return customDto;
    }

    public static Map<String, Object> convertCategory(Product product) {
        LinkedHashMap<String, Object> customDto = new LinkedHashMap<>();
        customDto.put("id", product.getId());
        customDto.put("nome", product.getNome());
        customDto.put("descricao", product.getDescricao());

        LinkedHashMap<String, String> CategoriaMap = new LinkedHashMap<>();
        CategoriaMap.put("nome", product.getCategoria().getNome());
        customDto.put("categoria", CategoriaMap);

        customDto.put("preco", product.getPreco());
        customDto.put("quantidade", product.getQuantidade());
        customDto.put("dtCadastro", product.getDtCadastro());
        customDto.put("ativo", product.isAtivo());

        return customDto;
    }

    public static ProductDto convert(Product product) {
        ProductDto result = new ProductDto();
        result.setId(product.getId());
        result.setNome(product.getNome());
        result.setDescricao(product.getDescricao());
        result.setPreco(product.getPreco());
        result.setQuantidade(product.getQuantidade());
        result.setDtCadastro(product.getDtCadastro());
        result.setAtivo(product.isAtivo());

        Category category = product.getCategoria();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setNome(category.getNome());
        result.setCategoria(categoryDto);

        Brand brand = product.getMarca();
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setNome(brand.getNome());
        result.setMarca(brandDto);

        return result;
    }
}
