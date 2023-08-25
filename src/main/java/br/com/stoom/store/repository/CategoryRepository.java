package br.com.stoom.store.repository;

import br.com.stoom.store.product.Brand;
import br.com.stoom.store.product.Category;
import br.com.stoom.store.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByAtivo(boolean ativo);
    List<Category> findByNome(String nome);
    boolean existsByNome(String nome);
}
