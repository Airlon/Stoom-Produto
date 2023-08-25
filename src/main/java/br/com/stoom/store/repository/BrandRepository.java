package br.com.stoom.store.repository;

import br.com.stoom.store.product.Brand;
import br.com.stoom.store.product.Category;
import br.com.stoom.store.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository  extends JpaRepository<Brand, Long> {
    List<Brand> findByAtivo(boolean ativo);
    List<Brand> findByNome(String nome);
}
