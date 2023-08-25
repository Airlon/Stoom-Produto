package br.com.stoom.store.repository;

import br.com.stoom.store.product.Category;
import br.com.stoom.store.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoriaNomeAndAtivo(String categoryName, boolean ativo);
    List<Product> findByAtivo(boolean ativo);
    List<Product> findByMarcaNomeAndAtivo(String brandName, boolean ativo);

}
