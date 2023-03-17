package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Cart;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
      List<Cart> findAllByDeletedEquals(Long deleted);
     Cart findByIdEqualsAndDeletedEquals(Long cartId,Long deleted);
    Cart findByUserIdEqualsAndAndDeletedEquals(Long cartId,Long deleted);

}
