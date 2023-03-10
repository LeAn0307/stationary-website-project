package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.shinhands.mu.Stationary.entity.CartProduct;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Long> {
    List<CartProduct> findAllByDeletedEquals(Long deleted);
    CartProduct findByIdEqualsAndDeletedEquals(Long id,Long deleted);
    @Procedure(name = "INSERT_CART_PRODUCT")
    public void insert_Cart_Product(@Param("CART_ID1") Long CART_ID1, @Param("PRODUCT_ID1") Long PRODUCT_ID1,@Param("QUANTITY1") int QUANTITY1);
}
