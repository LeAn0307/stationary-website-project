package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.dto.CartProductApiDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductRepoMybatis {
    @Select("select P.NAME,C.QUANTITY, P.PRICE FROM PRODUCT P, cart_product C WHERE C.CART_ID=#{id} AND C.PRODUCT_ID=P.ID AND C.DELETED=0 AND P.DELETED=0 ")
    @Results(value ={
            @Result(property = "name", column = "name"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "price", column = "price")
    })
    List<CartProductApiDTO> getAPI(long id);
}


