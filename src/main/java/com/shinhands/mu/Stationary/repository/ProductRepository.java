package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.entity.Account;
import com.shinhands.mu.Stationary.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByDeletedEquals(Long deleted);

    Product findByIdEqualsAndDeletedEquals(Long id, Long deleted);


    @Query(name = "find_all_product", nativeQuery = true)
    List<ProductDTO> findAllProducts();

    @Query(name = "find_one_product", nativeQuery = true)
    ProductDTO findProductById(long id);
}
